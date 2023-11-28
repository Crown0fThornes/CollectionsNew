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
	
	/**
	 * Throws exception if {@code i} if unacceptable position for an add operation on {@this}.
	 * 
	 * @param i
	 */
	private void validateAddIndex(int i) {
		String msg = "Invalid index for ArraySeries: " + i + "; ";
		if(i < 0) throw new ArrayIndexOutOfBoundsException(msg + "ArraySeries cannot have negative index.");
		if(i > this.size) throw new ArrayIndexOutOfBoundsException(msg + "Index greater than size of ArraySeries (SIZE: " + this.size + ")");

		
	}
	
	/**
	 * Throws exception if {@code i} if unacceptable position for a remove operation on {@this}.
	 * 
	 * @param i
	 */
	private void validateRemoveIndex(int i) {
		String msg = "Invalid index for ArraySeries: " + i + "; ";

		if(i < 0) throw new ArrayIndexOutOfBoundsException(msg + "ArraySeries cannot have negative index.");
		if(i > this.size) throw new ArrayIndexOutOfBoundsException(msg + "Index not present in ArraySeries (SIZE: " + this.size + ")");

		
	}
	
	public void add(T x) {
		if(this.size == this.array.length) {
			this.upsize();
		}
		
		this.array[size] = x;
		size++;
	}
	
	@SuppressWarnings("unchecked")
	public void add(int pos, T x) {
		this.validateAddIndex(pos);
		
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
		this.validateRemoveIndex(pos);
		
		T temp = (T) this.array[pos];
		
		//TODO: Make this not shit later.
		
		for(int i = pos; i < size - 1; i++) {
			this.array[i] = this.array[i+1];
		}
		
		size--;
		
		if (this.size <= Math.sqrt(this.array.length)) this.downsize();
		
		return temp;
	}
	
	private void upsize() {
		Object[] newArray = new Object[this.array.length * 2];
		
		for(int i = 0; i < this.size; i++) {
			newArray[i] = this.array[i];
		}
		
		this.array = newArray;
	}
	
	private void downsize() {
		Object[] newArray = new Object[this.array.length / 2];
		
		for(int i = 0; i < this.size; i++) {
			newArray[i] = this.array[i];
		}
		
		this.array = newArray;
	}
	
	/**
	 * Reduce {@code this.array) to only the size needed for the present elements.
	 */
	public void shrink() {
		Object[] newArray = new Object[this.size];
		
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
	
	@SuppressWarnings({ "unchecked", "unused" })
	private String fullArrayToString() {
		String res = "[";
		for (Object cur : this.array) {
			if (cur == null) res += "null,";
			else res += ((T) cur).toString() + ",";
		}
		res = res.substring(0, res.length() - 1) + "]";
		return res;
	}

}

