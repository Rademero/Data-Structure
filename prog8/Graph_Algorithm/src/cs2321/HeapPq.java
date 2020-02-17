package cs2321;
 
import java.util.Comparator;
 
import net.datastructures.*;

public class HeapPq<K, V> implements AdaptablePriorityQueue<K, V> {
	  public int parent(int i) {
	        return (i - 1) / 2;
	    }
	    public int left(int i) {
	        return 2 * i + 1;
	    }
	    public int right(int i) {
	        return 2 * i + 2;
	    }
	    private boolean hasLeft(int j) {
	        return left(j) < heap.size();
	    }
	    private boolean hasRight(int j) {
	        return right(j) < heap.size();
	    }
    private ArrayList<Entry<K, V>> heap = new ArrayList<>();
    Comparator<K> C;
 
    private class AdaptablePQEntry<K, V> extends PQEntry<K, V> {
        private int index;
 
        public AdaptablePQEntry(K key, V value, int j) {
            super(key, value);
            index = j;
        }
        public int getIndex() {
            return index;
        }
        public void setIndex(int i) {
            index = i;
        }
    }
    public HeapPq() {
        super();
        C = new DefaultComparator<K>();
    }
    public HeapPq(Comparator<K> c) {
        this.C = c;
    }
    public void upheap(int i) {
        while (i > 0) {
            int p = parent(i);
            if (C.compare(heap.get(i).getKey(), heap.get(p).getKey()) > 0) break;
            swap(i, p);
            i = p;
        }
    }
    public void downheap(int i) {
        while (hasLeft(i)) {
            int leftIndex = left(i);
            int smIn = leftIndex;
            if (hasRight(i)) {
                int rightIndex = right(i);
                if (C.compare(heap.get(leftIndex).getKey(), heap.get(rightIndex).getKey()) > 0) {
                	smIn = rightIndex;
                }
            }
            if (C.compare(heap.get(smIn).getKey(), heap.get(i).getKey()) >= 0) break;
            swap(i, smIn);
            i = smIn;
        }
    }
    public boolean isEmpty() {
        return heap.isEmpty();
    }
    public int size() {
        return heap.size();
    }
    public boolean checkKey(K key) throws IllegalArgumentException {
        try {
            return (C.compare(key, key) == 0);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Incompatible key");
        }
    }
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K, V> newest = new AdaptablePQEntry<>(key, value, heap.size());
        heap.add(size(), newest);
        upheap(heap.size() - 1);
        return newest;
    }
    public Entry<K, V> min() {
        if (heap.isEmpty()) return null;
        return heap.get(0);
    }
    public Entry<K, V> removeMin() {
        if (heap.isEmpty()) return null;
        Entry<K, V> min = heap.get(0);
        swap(0, size() - 1);
        heap.remove(size() - 1);
        downheap(0);
        downheap(0);
        return min;
    }
    public void swap(int i, int j) {
        Entry<K, V> tempi = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tempi);
        ((AdaptablePQEntry<K, V>) heap.get(i)).setIndex(i);
        ((AdaptablePQEntry<K, V>) heap.get(j)).setIndex(j);
    }
    public AdaptablePQEntry<K, V> validate(Entry<K, V> entry) {
        if (!(entry instanceof PQEntry)) throw new IllegalArgumentException("Invalid Entry");
        AdaptablePQEntry<K, V> locator = (AdaptablePQEntry<K, V>) entry;
        int j = locator.getIndex();
        if (j >= heap.size() || heap.get(j) != locator) {
            throw new IllegalArgumentException("invalid entry");
        }
        return locator;
    }
    public void bubble(int i) {
        if (i > 0 && C.compare(heap.get(i).getKey(), heap.get(parent(i)).getKey()) < 0) {
            upheap(i);
        } else downheap(i);
    }
    @Override
    public void remove(Entry<K, V> entry) throws IllegalArgumentException {
        AdaptablePQEntry<K, V> locator = validate(entry);
        int j = locator.getIndex();
        if (j == heap.size() - 1) {
            heap.remove(heap.size() - 1);
        } else {
            swap(j, heap.size() - 1);
            heap.remove(heap.size() - 1);
            bubble(j);
        }
    }
    @Override
    public void replaceKey(Entry<K, V> entry, K key) throws IllegalArgumentException {
        AdaptablePQEntry<K, V> locator = validate(entry);
        checkKey(key);
        locator.setKey(key);
        bubble(locator.getIndex());
    }
    @Override
    public void replaceValue(Entry<K, V> entry, V value) throws IllegalArgumentException {
        AdaptablePQEntry<K, V> locator = validate(entry);
        locator.setValue(value);
    }
}