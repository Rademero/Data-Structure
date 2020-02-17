package cs2321;
import net.datastructures.*;
public class QuickSort {
	 
    DefaultComparator C = new DefaultComparator();
    Graph<String, Integer> city;
    Vertex<String> v;
 
    public void sort(Edge<Integer>[] array, Graph<String, Integer> graph, Vertex<String> vertex) {
        city = graph;
        v = vertex;
        sort(array, 0, array.length - 1);
    }
    public void sort(Edge<Integer>[] array, int p, int r) {
        if(p < r) {
            int q = partition(array, p, r);
            sort(array, p, q - 1);
            sort(array, q + 1, r);
        }
    }
    public int partition(Edge<Integer>[] array, int p, int r){
        int i = p - 1;
        String pivot = city.opposite(v, array[r]).getElement();
        for(int j = p; j <= r - 1; j++) {
            if(C.compare(city.opposite(v, array[j]).getElement(), pivot) < 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, r, i + 1);
        return i + 1;
    }
    public void swap(Edge<Integer>[] array, int i, int j) {
        Edge<Integer> e = array[i];
        array[i] = array[j];
        array[j] = e;
    }
}
