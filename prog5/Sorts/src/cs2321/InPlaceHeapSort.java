package cs2321;

public class InPlaceHeapSort<K extends Comparable<K>> implements Sorter<K> {
	DefaultComparator comp = new DefaultComparator();
	/**Michael Romero
	*Assignment 5
	* This class is used to sort an array with type K using inPlace heap sort
	* */
	//@timeComplxity nlogn
	public void sort(K[] array) {
		//adds all values to the tree and than removes resulting in n times the height of the tree
		int n = array.length;
		for(int i =n/2-1; i >=0; i--) {
			maxHeap(array,n,i);
		}
		for (int i=n-1; i >=1;i--) { 
			swap(array,0,i);
			maxHeap(array,i,0);
		}
	}
	public void maxHeap(K arr[], int n, int i) {
		int largest = i; // Initialize largest as root 
	    int l = 2*i + 1; // left = 2*i + 1 
	    int r = 2*i + 2; // right = 2*i + 2 
	    if (l < n && comp.compare(arr[l], arr[largest]) > 0) 
	        largest = l; 
	    if (r < n && comp.compare(arr[r], arr[largest]) > 0) 
	        largest = r;				
	    if (i!=largest) {  
					swap(arr,i,largest);
					maxHeap(arr, n,largest);
				}
	}
	protected void swap(K[] array,int i, int j) {
		K temp = array[i]; 
		array[i] = array[j];
		array[j] = temp;
	}
	
}

