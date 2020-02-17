package cs2321;

public class UnorderedPQSort<K extends Comparable<K>> extends PQSort<K> implements Sorter<K>  {
	/**Michael Romero
	*Assignment 5
	* This class is used to sort an array with type K using a priority queue sort
	* */
	//@timeComplexity n^2
	UnorderedPQ pq = new UnorderedPQ();
	//adds all of the list to a ordered queue than pulls all information out
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
