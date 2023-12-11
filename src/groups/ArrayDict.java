package groups;

import java.util.Iterator;

public class ArrayDict<K, V> implements Group<Pair<K, V>> {

	private ArraySeries<Pair<K,V>> array;
	private int size;
	
	public ArrayDict() {
		array = new ArraySeries<Pair<K,V>>();
		size = 0;
	}

	/**
	 * Adds a Key, Value pair to this
	 */
	public void add(Pair<K, V> x) {
		if (this.has(x.key)) throw new IllegalArgumentException("Duplicate Key");
		array.add(x);
		size++;
	}
	
	/**
	 * Adds a Key, Value pair to this
	 * 
	 * @param key
	 * @param value
	 */
	public void add(K key, V value) {
		if (this.has(key)) throw new IllegalArgumentException("Duplicate Key");
		Pair<K,V> x = new Pair<>(key, value);
		array.add(x);
		size++;
	}
	
	/**
	 * Removes a Key, Value pair from this
	 * @param x
	 */
	public V remove(Pair<K, V> x) {
		if (!this.has(x.key)) throw new IllegalArgumentException("No Such Key");
		for (int i = 0; i < this.size; i++) 
			if (this.array.get(i).equals(x))
				return (V) this.array.remove(i).value;
		return null;
		
	}
	
	/**
	 * Removes a Key, Value pair from this
	 * 
	 * @param key
	 * @return
	 */
	public V remove(K key) {
		if (!this.has(key)) throw new IllegalArgumentException("No Such Key");
		for (int i = 0; i < this.size; i++) 
			if (this.array.get(i).key.equals(key))
				return (V) this.array.remove(i).value;
		return null;
	}
	
	/**
	 * Returns whether a given key is in this
	 * 
	 * @param key
	 * @return
	 */
	public boolean has(K key) {
		for (int i = 0; i < this.size; i++)
			if (this.array.get(i).key.equals(key))
				return true;
		return false;
	}
	
	/**
	 * Returns the value associated with a specific key in this
	 * 
	 * @param key
	 * @return
	 */
	public V get(K key) {
		if (!this.has(key)) throw new IllegalArgumentException("No Such Key");
		for (int i = 0; i < this.size; i++)
			if (this.array.get(i).key.equals(key))
				return (V) this.array.remove(i).value;
		return null;
	}

	@Override
	public Iterator<Pair<K, V>> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	

}
