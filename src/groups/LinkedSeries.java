package groups;

import java.util.Iterator;
import java.util.function.Predicate;

/**
 * Doubly Linked Series.
 * 
 * The size of this adapts as the user adds or removes elements. 
 * Because nodes can simply be linked and unlinked from one another,
 * this adaptation is 1:1 with the user's requirements. 
 * 
 * @author Lincoln Edsall, Benjamin Lampe
 *
 * @param <T> the elements of this
 */
public class LinkedSeries<T> implements Group<T>, Ordered {
	
	
	static int val = 0;
	
	/**
	 * The node that preHead.previous and postTail.next points to.
	 * This clearly marks the bounds of the list.
	 */
	private final Node BOUND_MARKER = null;
	
	private class Node {
		Node next = LinkedSeries.this.BOUND_MARKER;
		Node prev = LinkedSeries.this.BOUND_MARKER;
		T e;
	}
	
	/**
	 * A "smart" node before the element nodes.
	 * Points to the first element node.
	 */
	private Node preHead;
	
	/**
	 * A "smart" node after the element nodes.
	 * Points to the last element node. 
	 */
	private Node postTail;
	
	/**
	 * The number of elements in this.
	 */
	private int size = 0;
	
	/**
	 * The user can choose to set a capacity for this using set_capacity(), which limits the size of this.
	 */
	private int max_capacity = -1;
	
	
	/**
	 * Empty Constructor
	 */
	public LinkedSeries() {
		this.preHead = new Node();
		this.postTail = new Node();
		this.preHead.next = this.postTail;
		this.postTail.prev = this.preHead;
	}
	
	/**
	 * Throws exception if {@code i} is unacceptable position for an add operation on {@this}.
	 * 
	 * @param i
	 */
	private void validateAddIndex(int i) {
		String msg = "Invalid index for LinkedSeries: " + i + "; ";
		if(i < 0) throw new IndexOutOfBoundsException(msg + "LinkedSeries cannot have negative index.");
		if(i > this.size) throw new IndexOutOfBoundsException(msg + "Index greater than size of LinkedSeries (SIZE: " + this.size + ")");
		if(i >= this.max_capacity) throw new IndexOutOfBoundsException(msg + "LinkedSeries max capacity set to " + this.max_capacity);
	}
	
	/**
	 * Throws exception if {@code i} is unacceptable position for a get/remove operation on {@this}.
	 * 
	 * @param i
	 */
	private void validateGetIndex(int i) {
		String msg = "Invalid index for LinkedSeries: " + i + "; ";
		if(i < 0) throw new IndexOutOfBoundsException(msg + "LinkedSeries cannot have negative index.");
		if(i >= this.size) throw new IndexOutOfBoundsException(msg + "Index not present in LinkedSeries (SIZE: " + this.size + ")");
	}
	
	/**
	 * Returns the node at position {@code pos}.
	 * Either iterates from head to pos or from tail to pos,
	 * depending on which trip is shorter.
	 * 
	 * This is accomplished by hopping from node to node until the desired pos is reached.
	 */
	private Node getNodeAt(int pos) {
		Node res;
		if (pos < this.size / 2) {
			res = this.preHead;
			for (int i = -1; i < pos; i++) {
				res = res.next;
			}
		} else {
			res = this.postTail;
			for (int i = size; i > pos; i--) {
				res = res.prev;
			}
		}
		return res;
	}
	
	/**
	 * Add element to the end of this
	 * @param x
	 */
	public void add(T x) {
		
	    Node toAdd = new Node();
	    toAdd.e = x;
	    
	    toAdd.next = this.postTail;
	    toAdd.prev = this.postTail.prev;
	    
	    this.postTail.prev.next = toAdd;
	    this.postTail.prev = toAdd;

	    this.size++;
	}
	
	/**
	 * Add element to given position of this
	 * @param pos
	 * @param x
	 */
	public void add(int pos, T x) {
	    validateAddIndex(pos);

	    Node after = this.getNodeAt(pos);
	    
	    Node toAdd = new Node();
	    toAdd.e = x;
        
        toAdd.next = after;
        toAdd.prev = after.prev;
        
        after.prev = toAdd;

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
		this.add(pos, x);
		
		this.size++;
	}
	
	/**
	 * Remove element from given position in this
	 * 
	 * @param pos
	 * 		Position of Element to be removed in the array.
	 */
	public T remove(int pos) {
	    validateGetIndex(pos);

        Node toRemove = this.getNodeAt(pos);

        T res = toRemove.e;
        
        toRemove.prev.next = toRemove.next;
        toRemove.next.prev = toRemove.prev;
        

	    size--;
	    
	    return res;
		
	}
	
	/**
	 * Get the size of this.
	 * 
	 * @return
	 */
	public int size() {
		return this.size;
	}
	
	public T get(int pos) {
		this.validateGetIndex(pos);
		return this.getNodeAt(pos).e;
	}
	
	@Override
	public void clear() {
		this.preHead = new Node();
		this.postTail = new Node();
		this.preHead.next = this.postTail;
		this.postTail.prev = this.preHead;
		
	}
	
	public boolean contains(T key) {
		Node current = this.preHead;
		while ((current = current.next) != this.postTail) {
			if (key.equals(current.e)) return true;
		}
		return false;
	}
	
	/**
	 * Removes all elements of this that do not pass {@code test}
	 * 
	 * @param test	predicate functional interface
	 */
	public void filter(Predicate<T> test) {
		int removed = 0;
		Node current = this.preHead;
		while ((current = current.next) != this.postTail) {
			if (!test.test(current.e)) {
				current.prev.next = current.next;
				current.next.prev = current.prev;
				removed++;
			}
		}
		this.size -= removed;
	}
	
	/**
	 * Returns a LinkedList with only the elements of this that pass {@code test}
	 * 
	 * @param test	predicate functional interface
	 */
	public LinkedSeries<T> filtered(Predicate<T> test) {
		
		LinkedSeries<T> res = new LinkedSeries<>();
		
		int removed = 0;
		Node current = this.preHead;
		while ((current = current.next) != this.postTail) {
			if (test.test(current.e)) {
				res.add(current.e);
			}
		}
		
		return res;
	}
	
	/**
	 * Returns string representing underlying array
	 * @return
	 */
	@SuppressWarnings("unused")
	private String fullArrayToString() {
		String res = "[";
		for (T cur : this) {
			if (cur == null) res += "null,";
			else res += ((T) cur).toString() + ",";
		}
		res = res.substring(0, res.length() - 1) + "]";
		return res;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new LinkedSeriesIterator();
	}
	
    private class LinkedSeriesIterator implements Iterator<T> {
        private Node nextNode = LinkedSeries.this.preHead.next;
       
        private LinkedSeriesIterator() {}

        @Override
        public boolean hasNext() {
            return this.nextNode.next != LinkedSeries.this.BOUND_MARKER;
        }

		@Override
        public T next() {
            if (!hasNext()) {
                throw new UnsupportedOperationException("No more elements in LinkedSeries.");
            }
            T element = this.nextNode.e;
            this.nextNode = this.nextNode.next;
            return element;
        }
		
    }

}

