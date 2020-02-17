package cs2321;

public class InPlaceSelectionSort<K extends Comparable<K>> implements Sorter<K> {

	/**Michael Romero
	*Assignment 5
	* This class is used to sort an array with type K using selection
	* */
	//@timeComplexity n^2 as worst, best is n
	//This is because it runs n amount of times within a for loop that is n
	public void sort(K[] array) {
		DefaultComparator comp = new DefaultComparator();
		int n = array.length;
		int cur;
		for (int i=0;i <= (n-2);i++) {
			 cur = i;
			for(int j = (i+1); j<= n-1;j++) {
				if(comp.compare(array[j], array[cur]) < 0) {
					cur = j;
				}
			}
			if(cur != i) {
				swap(array,i,cur);
			}
		}
	}
	protected void swap(K[] array,int i, int j) {
		K temp = array[i]; 
		array[i] = array[j];
		array[j] = temp;
	}
 
}
 