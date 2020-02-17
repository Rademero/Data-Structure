package cs2321;
/**Michael Romero
*Assignment 5
* This class is used to sort an array with type K using heap sort
* */
import net.datastructures.PriorityQueue;
//@timeComplxity nlogn
public class HeapPQSort<K extends Comparable<K>> extends PQSort<K> implements Sorter<K> {
	//adds all values to the tree and than removes resulting in n times the height of the tree
	HeapPQ pq = new HeapPQ();
	public void sort(K[] array) {
			
			int n = array.length;
		 for (int i=0; i <= n-1; i++) {
		 K element = array[i];
		 pq.insert(element, null); // element is key; null value
		 }
		 for (int i=0; i <= n-1; i++) {
		 K element = (K) pq.removeMin( ).getKey( );
		 array[i] = element; // the smallest key in P is next placed in S
			 }
		}

}  

