package groups;

public class ArraySeries<T> {
	
	private Object[] array;
	
	private int size = 0;
	
	private final int DEFAULT_SIZE = 10;
	
	
	/**
	 * Empty Constructor
	 */
	public ArraySeries() {
		array = new Object[DEFAULT_SIZE];
	}
	
	public void add(T x) {
		if(this.size == this.array.length) {
			this.updateArray();
		}
		
		this.array[size] = x;
		size++;
	}
	
	@SuppressWarnings("unchecked")
	public void add(int pos, T x) {
		if(pos < 0) {
			throw new ArrayIndexOutOfBoundsException("ArraySeries index must be non-negative.");
		}
		
		if(pos > size) {
			throw new ArrayIndexOutOfBoundsException("Index is greater than size of ArraySeries (SIZE: " + this.size + ")");
		}
		
		this.add((T) this.array[size-1]);
		
		for(int i = size-1; i > pos; i--) {
			this.array[i] = this.array[i-1];
		}
		
		this.array[pos] = x;
	}
	
	/**
	 * 
	 * @param pos
	 * 		Position of Element to be removed in the array.
	 */
	@SuppressWarnings("unchecked")
	public T remove(int pos) {
		
		if(size == 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		if(pos >= size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		T temp = (T) this.array[pos];
		
		//TODO: Make this not shit later.
		
		for(int i = pos; i < size; i++) {
			this.array[i] = this.array[i+1];
		}
		
		size--;
		return temp;
	}
	
	private void updateArray() {
		Object[] newArray = new Object[this.array.length * 2];
		
		for(int i = 0; i < this.size; i++) {
			newArray[i] = this.array[i];
		}
		
		this.array = newArray;
	}
	
	public int size() {
		return this.size;
	}
	
	@SuppressWarnings("unchecked")
	public T get(int pos) {
		
		if(pos >= size) {
			throw new ArrayIndexOutOfBoundsException("Index is greater than ArraySeries Size.");
		}
		
		if(pos < 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		return (T) this.array[pos];
	}

}

