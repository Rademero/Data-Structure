package cs2321;
/**Michael Romero
*Assignment 5
* This class is used to sort an array with type K using a priority queue sort
* */
//@timeComplexity best is n worst is n^2
public class OrderedPQSort<K extends Comparable<K>> extends PQSort<K> implements Sorter<K>  {
	//adds all of the list to a ordered queue than pulls all out, if already sorted time is reduced
	DefaultComparator comp = new DefaultComparator();
	OrderedPQ pq = new OrderedPQ();
	@Override
	public void sort(K[] array) {
		for (int i = 0; i <= array.length-1;i++) {
			pq.insert(array[i], null);
		}
		for (int i = 0; i <= array.length-1;i++) {
			array[i] = (K) pq.removeMin().getKey();
		}
	}
}
 