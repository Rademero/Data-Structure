package cs2321;

import net.datastructures.Entry;

public class InPlaceInsertionSort<K extends Comparable<K>> implements Sorter<K> {

	/**Michael Romero
	*Assignment 5
	* This class is used to sort an array with type K using insertion
	* */
	//@timeComplexity n^2 as worst, best is n
	public void sort(K[] array) {
//This is because it runs at most n amount of times within a for loop that is n
		DefaultComparator comp = new DefaultComparator();
		int n = array.length;
		int minindex ;
		K k;
		for (int i=0;i <= n-1;i++) { 
			k = array[i];
			minindex =i-1;
			while(minindex >= 0 && comp.compare(array[minindex], k) > 0) {
				array[minindex +1] = array[minindex];
				minindex--;
			}
			array[minindex+1] = k;
		}
	}  

} 
