/**
 * seawin.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.seawin.test.map;

/**
 * 
 * @author lijin
 * @version $Id: MyConcurrentHashMap.java, v 0.1 2018年10月24日 下午3:00:57 lijin Exp $
 */

import java.util.concurrent.locks.ReentrantReadWriteLock;
 
/**
 * 参考代码地址 https://blog.csdn.net/helloapps/article/details/80275537
 * 
 * @author lijin
 * @version $Id: MyConcurrentHashMap.java, v 0.1 2018年10月24日 下午3:01:34 lijin Exp $
 */
public class MyConcurrentHashMap<K,V> {
 
    private Segment<K,V>[] segments;
    private int segIndexbit = 4;
    private int entIndexbit = 4;
    
    public MyConcurrentHashMap() {
        this.segments = new Segment[1<<segIndexbit];
        for(int i=0;i<segments.length;i++) {
            segments[i] = new Segment<K,V>();
        }
    }
    
    public V put(K key, V value) {
        int hashCode = key.hashCode();
        int segIndex = hashCode%segments.length;
        int entryIndex = hashCode%segments[segIndex].size();
        HashEntry<K, V> old = segments[segIndex].add(new HashEntry<K, V>(key, value), entryIndex);
        return old == null ? null:old.v;
    }
    
    
    public V get(K k) {
        int hashCode = k.hashCode();
        int segIndex = hashCode%segments.length;
        int entryIndex = hashCode%segments[segIndex].size();
        V v = segments[segIndex].get(entryIndex,k);
        return v;
    }
    
    public V remove(K k) {
        int hashCode = k.hashCode();
        int segIndex = hashCode%segments.length;
        int entryIndex = hashCode%segments[segIndex].size();
        HashEntry<K,V> target = segments[segIndex].remove(entryIndex,k);
        return target == null ? null:target.v;
    }
    
    public int size() {
        int result = 0;
        for(int i=0;i<segments.length;i++) {
            result+=segments[i].size();
        }
        return result;
    }
 
    private class Segment<K,V>{
        private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        private HashEntry<K,V>[] table = new HashEntry[1<<entIndexbit];
        private volatile int size = table.length;
        
        public int size() {
            return size;
        }
        
        public HashEntry<K,V> remove(int index, K k) {
            lock.writeLock().lock();
            try {
                HashEntry<K,V> first = table[index];
                if(first == null) return null;
                if(first.k.equals(k)) {
                    table[index] = first.next;
                }
                while(first.next!=null&&!first.next.k.equals(k)) {
                    first = first.next;
                }
                HashEntry<K,V> target = first.next; 
                if(target != null) {
                    first.next = target.next;
                }
                return target;
            }finally {
                lock.writeLock().unlock();
            }
        }
 
        public V get(int index, K k) {
            lock.readLock().lock();
            try {
                HashEntry<K,V> first = table[index];
                while(first!=null&&!first.k.equals(k)) {
                    first = first.next;
                }
                return first == null ? null:first.v;
            }finally {
                lock.readLock().unlock();
            }
        }
 
        public HashEntry<K,V> add(HashEntry<K,V> entry,int index) {
            lock.writeLock().lock();
            try {
                HashEntry<K,V> first = table[index];
                if(first == null) {
                    table[index] = entry;
                }else {
                    entry.next = first;
                    table[index] = entry;
                }
                return entry.next;
            }finally {
                lock.writeLock().unlock();
            }
        }
    }
    
    private class HashEntry<K,V>{
        private K k;
        private V v;
        private HashEntry<K,V> next;
        
        public HashEntry(K k,V v) {
            this.k = k;
            this.v = v;
        }
    }
}
 
 

