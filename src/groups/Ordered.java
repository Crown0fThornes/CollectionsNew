package groups;

/**
 * Marker & Utility interface for Groups that guarantee 
 * the maintenance of input order.
 * 
 * For example, ArraySeries and ArrayMap can guarantee that the first element 
 * will be the first element (upon retrieval, iteration, or other applicable situations)
 * indefinitely.
 * 
 * TreeMap, however, can make no such guarantee.
 * 
 * Because Ordered Groups have an order (an order with significance to the user, at least),
 * they can also be sorted. 
 * 
 * @author Lincoln Edsall, Benjamin Lampe
 *
 */
public interface Ordered {

	/**
	 * A default sorting method for any Ordered group. 
	 * It is advisable to use, if available, a given Group's own sort implementation. 
	 * 
	 * @param <T>	The type of g's element 
	 * @param <G>	Any Ordered Group
	 * @param g	A group
	 */
	public static <T extends Comparable<T>, G extends Group<T> & Ordered> void sort(G g) {
		
		ArraySeries<T> sorted = new ArraySeries<>();
		
		for (T element : g) {
			sorted.add(element); //pretend that this is properly sorted for now
		}
		
		g.clear();
	
		for (T element : sorted) g.add(element);
	}
	
}
