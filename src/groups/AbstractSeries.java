package groups;

public abstract class AbstractSeries<T> implements Group<T>, Ordered {

	public AbstractSeries() {}
	
	public abstract void add(T e);
	
	abstract void add(T e, int pos);
	
}
