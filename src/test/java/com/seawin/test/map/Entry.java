package com.seawin.test.map;

public class Entry<K, V> {
	final K key;
	V value;
	Entry<K, V> next;

	public Entry(K key, V value, Entry<K, V> next) {
		this.key = key;
		this.value = value;
		this.next = next;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	public final V setValue(V newVal) {
		V oldVal = value;
		value = newVal;
		return oldVal;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Entry)) {
			return false;
		}
		@SuppressWarnings("rawtypes")
		Entry e = (Entry) obj;
		Object k1 = getKey();
		Object k2 = e.getKey();
		if (k1 == k2 || (k1 != null && k1.equals(k2))) { // key 相同 ，比 value
			Object v1 = getValue();
			Object v2 = e.getValue();
			if (v1 == v2 || (v1 != null && v1.equals(v2)))
				return true;
		}
		return false;
	}

	public int hashCode() {
		return (key == null ? 0 : key.hashCode())
				^ (value == null ? 0 : value.hashCode());
	}

	@Override
	public String toString() {
		return "key=" + getKey() + ",value=" + getValue();
	}

}
