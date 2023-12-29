package groups;

import java.util.Iterator;
import java.util.function.Predicate;

public abstract class AbstractSeries<T> implements Group<T>, Ordered {
	
	public AbstractSeries() {}
	
//	public abstract void add(T e);
	
//	public abstract void add(T e, int pos);
	
//	public abstract void addIgnoreSize(T e, int pos);
//	
//	public abstract T remove(int post);
//	
//	public abstract T get(T e);
//	
	public abstract int size();
//	
//	public boolean contains(T key) {
//		for (T e : this) {
//			if (e.equals(key)) return true;
//		}
//		return false;
//	}
//	
//	public abstract void filter(Predicate<T> removeIfFail);
//	
//	public abstract AbstractSeries<T> filtered(Predicate<T> rejectIfFail);
//	
//	public abstract Iterator<T> iterator();

	
    @Override
    public boolean equals(Object o) {
    	if (o == this) return true;
    	if (o == null) return false;
    	if (!(o instanceof LinkedSeries)) return false;
		LinkedSeries<?> test = (LinkedSeries<?>) o;
    	if (this.size() != test.size()) return false;
    	
    	Iterator<T> a = this.iterator();
    	Iterator<?> b = test.iterator();
    	
    	while (a.hasNext()) {
    		if (!a.next().equals(b.next())) 
    			return false;
    	}
    	
    	return true;
    	
    }
	
	
	
}
