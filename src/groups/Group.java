package groups;

public interface Group<T> extends Iterable<T> {

	void add(T x);
	
	void clear();
	
	
}
