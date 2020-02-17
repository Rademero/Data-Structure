package cs2321;
/**Michael Romero
*Assignment 5
* This class is used to sort an array with type K using quick sort
* */
//@timeComplectiy nlogn is expected
public class QuickSort<E extends Comparable<E>> implements Sorter<E> {
//looks at only min and max item than moves to next sort log n amount of times
	public void sort(E[] array) {
		
   Qsort(array,0,array.length-1);
	}
	public void Qsort(E arr[], int low, int high) 
    { 
        if (low < high) 
        { 
            /* pi is partitioning index, arr[pi] is  
              now at right place */
            int pi = partition(arr, low, high); 
  
            // Recursively sort elements before 
            // partition and after partition 
            Qsort(arr, low, pi-1); 
            Qsort(arr, pi+1, high); 
        } 
    } 
	public int partition(E arr[], int low, int high)  
    { 
        E pivot = arr[high];  
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) 
        { 
            // If current element is smaller than or 
            // equal to pivot  
            if (((Comparable <E>) arr[j]).compareTo(pivot) <= 0)
            { 
                i++;  
  
                // swap arr[i] and arr[j] 
                E temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        E temp = arr[i+1]; 
        arr[i+1] = arr[high]; 
        arr[high] = temp; 
  
        return i+1; 
    } 
}
