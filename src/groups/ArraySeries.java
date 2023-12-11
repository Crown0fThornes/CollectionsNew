package groups;

import java.util.Iterator;

/**
 * Wraps the java array type as a Series.
 * 
 * The size/capacity of this adapts as the user adds or removes elements. 
 * Size refers to the number of elements in this, while capacity refers to the internal array size
 * that 
 * Once the current size fills up, the capacity is doubled. 
 * When items are removed such that ArraySeries is using excess unneeded memory (used < sqrt(size)),
 * the capacity is halved.
 * 
 * @author Lincoln Edsall, Benjamin Lampe
 *
 * @param <T> the elements of this
 */
public class ArraySeries<T> implements Group<T>, Ordered {
	
	/**
	 * Contains the elements of this.
	 */
	private Object[] array;
	
	/**
	 * The size of this, which varies from the length of {@code array}.
	 * While this may contain 10 elements added by the user and thus {@code size} = 10,
	 * the length of {@code array} may be 20 with null or irrelevant items past position 10.
	 * If, for optimization reasons or otherwise, the user wishes to reduce {@code array} 
	 * to only the necessary indices, shrink() can be used.
	 * 
	 */
	private int size = 0;
	
	/**
	 * The user can choose to set a capacity for this using set_capacity(), which limits the size of this.
	 */
	private int max_capacity = -1;
	
	private final int DEFAULT_SIZE = 10;
	
	
	/**
	 * Empty Constructor
	 */
	public ArraySeries() {
		array = new Object[DEFAULT_SIZE];
	}
	
	/**
	 * Throws exception if {@code i} is unacceptable position for an add operation on {@this}.
	 * 
	 * @param i
	 */
	private void validateAddIndex(int i) {
		String msg = "Invalid index for ArraySeries: " + i + "; ";
		if(i < 0) throw new IndexOutOfBoundsException(msg + "ArraySeries cannot have negative index.");
		if(i > this.size) throw new IndexOutOfBoundsException(msg + "Index greater than size of ArraySeries (SIZE: " + this.size + ")");
		if(i >= this.max_capacity) throw new IndexOutOfBoundsException(msg + "ArraySeries max capacity set to " + this.max_capacity);
		
	}
	
	/**
	 * Throws exception if {@code i} is unacceptable position for a get/remove operation on {@this}.
	 * 
	 * @param i
	 */
	private void validateGetIndex(int i) {
		String msg = "Invalid index for ArraySeries: " + i + "; ";
		if(i < 0) throw new IndexOutOfBoundsException(msg + "ArraySeries cannot have negative index.");
		if(i >= this.size) throw new IndexOutOfBoundsException(msg + "Index not present in ArraySeries (SIZE: " + this.size + ")");

		
	}
	
	/**
	 * Add element to the end of this
	 * @param x
	 */
	public void add(T x) {
		if(this.size == this.array.length) {
			this.upsize();
		}
		
		this.array[size] = x;
		this.size++;
	}
	
	/**
	 * Add element to given position of this
	 * @param pos
	 * @param x
	 */
	@SuppressWarnings("unchecked")
	public void add(int pos, T x) {
		this.validateAddIndex(pos);
		
		this.add((T) this.array[size-1]);
		
		for(int i = size-1; i > pos; i--) {
			this.array[i] = this.array[i-1];
		}
		
		this.array[pos] = x;
		
		this.size++;
	}
	
	/**
	 * Add element to any position, fill intermediate indices with null
	 * @param pos
	 * @param x
	 */
	public void addNoBounds(int pos, T x) {
		for (int i = 0; i < pos; i++) {
			if (i > this.size) this.add(null);
		}
		this.add(x);
	}
	
	/**
	 * Remvoe element from given position in this
	 * 
	 * @param pos
	 * 		Position of Element to be removed in the array.
	 */
	@SuppressWarnings("unchecked")
	public T remove(int pos) {
		this.validateGetIndex(pos);
		
		T temp = (T) this.array[pos];
		
		//TODO: Make this not shit later.
		
		for(int i = pos; i < size - 1; i++) {
			this.array[i] = this.array[i+1];
		}
		
		size--;
		
		if (this.size <= Math.sqrt(this.array.length)) this.downsize();
		
		return (T) temp;
	}
	
	/**
	 * Increase the size of underlying array twofold
	 */
	private void upsize() {
		Object[] newArray = new Object[this.array.length * 2];
		
		for(int i = 0; i < this.size; i++) {
			newArray[i] = this.array[i];
		}
		
		this.array = newArray;
	}
	
	/**
	 * Decrease the size of underlying array by half
	 */
	private void downsize() {
		Object[] newArray = new Object[this.array.length / 2];
		
		for(int i = 0; i < this.size; i++) {
			newArray[i] = this.array[i];
		}
		
		this.array = newArray;
	}
	
	/**
	 * Decrease size of underlying array to only the size needed for the present elements.
	 */
	public void shrink() {
		Object[] newArray = new Object[this.size];
		
		for(int i = 0; i < this.size; i++) {
			newArray[i] = this.array[i];
		}
		
		this.array = newArray;
	}
	
	/**
	 * Get the size of this
	 * 
	 * @return
	 */
	public int size() {
		return this.size;
	}
	
	@SuppressWarnings("unchecked")
	public T get(int pos) {
		
		this.validateGetIndex(pos);
		
		return (T) this.array[pos];
	}
	
	/**
	 * Returns string representing underlying array
	 * @return
	 */
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

	@Override
	public Iterator<T> iterator() {
		return new ArraySeriesIterator();
	}
	
    private class ArraySeriesIterator implements Iterator<T> {
        private int currentIndex = 0;
       
        private ArraySeriesIterator() {}

        @Override
        public boolean hasNext() {
            return this.currentIndex < ArraySeries.this.size;
        }

        @SuppressWarnings("unchecked")
		@Override
        public T next() {
            if (!hasNext()) {
                throw new UnsupportedOperationException("No more elements in ArraySeries.");
            }
            T element = (T) ArraySeries.this.array[this.currentIndex];
            this.currentIndex++;
            return element;
        }
    }

	@Override
	public void clear() {
		this.size = 0;
		this.array = new Object[0];
		
	}

}

