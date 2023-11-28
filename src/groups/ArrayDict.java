package groups;

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
		Pair<K,V> x = new Pair<>(key, value);
		array.add(x);
		size++;
	}
	
	/**
	 * Removes a Key, Value pair from this
	 * @param x
	 */
	public void remove(Pair<K, V> x) {
		for (int i = 0; i < this.size; i++) 
			if (this.array.get(i).equals(x))
				this.array.remove(i);
		
	}
	
	/**
	 * Removes a Key, Value pair from this
	 * 
	 * @param key
	 * @return
	 */
	public V remove(K key) {
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
		for (int i = 0; i < this.size; i++)
			if (this.array.get(i).key.equals(key))
				return (V) this.array.remove(i).value;
		return null;
	}
	

}
