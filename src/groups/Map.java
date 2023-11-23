package groups;

public class Map<K, V> implements Group<Pair<K, V>> {

	private Object[] array;
	private int size;
	
	public Map() {
		array = new Object[10];
		size = 0;
	}

	@Override
	public void add(Pair<K, V> x) {
		array[size] = x;
		size++;
	}
	
	public void add(K key, V value) {
		Pair<K,V> x = new Pair<>(key, value);
		array[size] = x;
		size++;
	}
	

}
