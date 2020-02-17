package cs2321;

import net.datastructures.*;

/**
 * A class that performs the generic PQ Sort:
 *
 * @author CS2321 Instructor
 * @param <K>
 */ 
public class PQSort<K extends Comparable<K>> {
	
	/*
	 * PQSort - insert every element in array to PQ, 
	 * then call PQ's removeMin repeatedly, and overwrite the data in the array
	 */
	protected void sort(K[] kArray, PriorityQueue<K,K> pq) {
		
		int n = kArray.length;
	 for (int i=0; i <= n-1; i++) {
	 K element = kArray[i];
	 pq.insert(element, null); // element is key; null value
	 }
	 for (int i=0; i <= n-1; i++) {
	 K element = pq.removeMin( ).getKey( );
	 kArray[i] = element; // the smallest key in P is next placed in S
		 }
	}

}
