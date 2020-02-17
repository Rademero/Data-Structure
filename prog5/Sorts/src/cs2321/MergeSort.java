package cs2321;

import java.util.Arrays;
/**Michael Romero
*Assignment 5
* This class is used to sort an array with type K using merge sort
* */
//@timeComplectiy nlogn
public class MergeSort<E extends Comparable<E>> implements Sorter<E> {
//this is because its braking the array up to sort a tree of items n times 
	@SuppressWarnings("unchecked")
	public void sort(E[] array) {
		int n = array.length;
		
		if (n < 2)
			return; // array is trivially sorted
		
		// divide
		int mid = n / 2;
		
		E[] S1 = (E[]) new Comparable[mid]; // copy of first half
		E[] S2 = (E[]) new Comparable[n - mid];
		
		for (int i = 0; i < mid; i++) {
			S1[i] = array[i];
		}

		for (int i = 0; i < n - mid; i++) {
			S2[i] = array[mid + i];
		}
		sort(S1); // sort copy of first half
		sort(S2); // sort copy of second half
		// merge results
		merge(S1, S2, array);
	}

	public void merge(E[] S1, E[] S2, E[] S) {
		int i = 0, j = 0, k = 0;
		while ((i + j) < S.length) {
			if (j == S2.length || (i < S1.length &&  S1[i].compareTo(S2[j]) < 0)) {
				S[k] = S1[i]; // copy ith element of S1 and increment i
				i++;
				k++;
			} else {
				S[k] = S2[j]; // copy jth element of S2 and increment j
				j++;
				k++;
			}
		}
		while (i<S1.length) {
			S[k] = S1[i];
			i++;
			k++;
		}
		
		while (j<S2.length){
			S[k] = S2[j];
			j++;
			k++;
		}
	}
} 
