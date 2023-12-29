package groups;

import javax.print.attribute.standard.MediaSize.Other;

public class Pair<K,V> {
	
	public K key;
	public V value;

	public Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public String toString() {
		return "(" + key + "," + value + ")";
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (!(o instanceof Pair)) return false;
		@SuppressWarnings("unchecked")
		Pair<K,V> b = (Pair<K,V>) o;
		return this.key.equals(b.key) && this.value.equals(b.value);
	}
	
}
