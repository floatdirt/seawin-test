package com.seawin.test.map;


public class MyMap<K, V> {
	private Entry<K, V>[] table;// 主体数组： Entry数组
	private static final int DEFAULT_INITIAL_CAPACITY = 16; // Entry数组默认长度
	private int size;// 当前大小

	@SuppressWarnings("unchecked")
	public MyMap() {
		// 默认构造一个长度为16的Entry数组
		table = new Entry[DEFAULT_INITIAL_CAPACITY];
		size = DEFAULT_INITIAL_CAPACITY;
	}

	public int getSize() {
		return size;
	}

	// 求 存放在 数组下标的位置 算法， 当然这里这个算法较简单，可以换成jdk中的源方法
	int indexFor(int h, int length) {
		return h % (length - 1);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Entry<K, V> e : table) {
			if (e != null) {
				sb.append("[").append(e.toString()).append("],");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	// 取得元素
	public V get(K key) {
		int hashCode = key.hashCode();
		int index = indexFor(hashCode, size);
		return getEntry(table[index], key);
	}

	public V getEntry(Entry<K, V> entry, K key) {
		if (entry == null) {
			return null;
		}
		if (entry.getKey().equals(key)) {
			return entry.getValue();
		}
		return getEntry(entry.next, key);
	}

	public V put(K key, V value) {
		int hashCode = key.hashCode();
		int index = indexFor(hashCode, size);
		for (Entry<K, V> e = table[index]; e != null; e = e.next) {
			if (e.getKey().hashCode() == key.hashCode()
					&& (e.getKey() == key || e.getKey().equals(key))) {
				V oldValue = e.getValue();
				e.setValue(value);
				return oldValue;
			}
		}
        /**
         * 我自己按照正常思维一开始把新增的Entry插在链表的尾端，结果费时费力，
         * 后来看代码吧Entry插在链表的头部，很方便
         */
		table[index] = new Entry<K, V>(key, value, table[index]);
		return null;
	}

	public Entry<K, V> getLastEntry(Entry<K, V> entry) {
		if (entry == null || entry.next == null) {
			return entry;
		}
		return getLastEntry(entry.next);
	}

	public static void main(String[] args) {
		MyMap<String, String> map = new MyMap<String, String>();
		for (int i = 0; i < 17; i++) {
			map.put(String.valueOf(i), String.valueOf(i));
		}
		map.put("2", "a");
		map.put("3", "b");
		map.put("4", "c");
		for (int i = 0; i < 17; i++) {
			System.out.println(map.get(String.valueOf(i)));
		}

	}

}
