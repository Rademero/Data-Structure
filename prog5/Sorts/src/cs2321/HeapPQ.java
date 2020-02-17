package cs2321;

import java.util.Comparator;

import net.datastructures.*;

/**
 * Michael Romero
 *Assignment 3
 * This class is used to make an priority queue implemented by a heap 
 */
public class HeapPQ<K, V> implements AdaptablePriorityQueue<K, V> {
	protected ArrayList<Entry<K, V>> heap = new ArrayList<>();

	private Comparator<K> c;
	
	public HeapPQ() {
		super();
		c = new DefaultComparator<>();
	}

	public HeapPQ(Comparator<K> c) {
		this.c = c;
	}

	protected int parent(int j) {
		return (j - 1) / 2;
	} // truncating division

	protected int left(int j) {
		return 2 * j + 1;
	}

	protected int right(int j) {
		return 2 * j + 2;
	}

	protected boolean hasLeft(int j) {
		return left(j) < heap.size();
	}

	protected boolean hasRight(int j) {
		return right(j) < heap.size();
	}

	/**
	 * The entry should be bubbled up to its appropriate position
	 * 
	 * @param int move the entry at index j higher if necessary, to restore the heap
	 *            property
	 */

	protected void swap(int i, int j) {
		Entry<K, V> temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}

	@SuppressWarnings("unused")
	public void upheap(int j) {
		while (j > 0) {
			// continue until reaching root (or break statement)
			int p = parent(j);
			if (c.compare(heap.get(j).getKey(), heap.get(p).getKey()) >= 0)
				break;
			swap(j, p);
			j = p; // continue from the parent's location
		}
	}

	/**
	 * The entry should be bubbled down to its appropriate position
	 * 
	 * @param int move the entry at index j lower if necessary, to restore the heap
	 *            property
	 */

	public void downheap(int j) {
		while (hasLeft(j)) { // continue to bottom (or break statement)
			int leftIndex = left(j);
			int smallChildIndex = leftIndex; // although right may be smaller
			if (hasRight(j)) {
				int rightIndex = right(j);
				if (c.compare(heap.get(leftIndex).getKey(), heap.get(rightIndex).getKey()) > 0)
					smallChildIndex = rightIndex; // right child is smaller
			}
			if (c.compare(heap.get(smallChildIndex).getKey(), heap.get(j).getKey()) >= 0)
				break; // heap property has been restored
			swap(j, smallChildIndex);
			j = smallChildIndex; // continue at position of the child
		}
	}

	@Override
	public int size() {
		return heap.size();
	}

	@Override
	public boolean isEmpty() {
		return heap.isEmpty();
	}

	protected boolean checkKey(K key) throws IllegalArgumentException {
		try {
			return (c.compare(key, key) == 0); // see if key can be compared to itself
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Incompatible key");
		}
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		checkKey(key); // auxiliary key-checking method (could throw exception)
		Entry<K, V> newest = new PQEntry<>(key, value);
		heap.addLast(newest); // add to the end of the list
		upheap(heap.size() - 1); // upheap newly added entry's
		return newest;
	}

	@Override
	public Entry<K, V> min() {
		if (heap.isEmpty())
			return null;
		return heap.get(0);
	}

	@Override
	public Entry<K, V> removeMin() {
		if (heap.isEmpty())
			return null;
		Entry<K, V> answer = heap.get(0);
		swap(0, heap.size() - 1); // put minimum item at the end
		heap.remove(heap.size() - 1); // and remove it from the list;
		downheap(0); // then fix new root
		return answer;
	}

	protected void bubble(int j) {
		if (j > 0 && c.compare(heap.get(j).getKey(), heap.get(parent(j)).getKey()) < 0)
			upheap(j);
		else
			downheap(j); // although it might not need to move
	}
 
	public void remove(Entry<K,V> entry) throws IllegalArgumentException {
		checkKey(entry.getKey());
		int i;
		for(i=0;i< heap.size;i++) {
			if (heap.get(i).getKey() == entry.getKey()) break;
		}
		heap.set(i, heap.removeLast());
	bubble(i);
	} 
 
	public void replaceKey(Entry<K,V> entry, K key)
	 throws IllegalArgumentException {
	 checkKey(key); // might throw an exception
	 int i;
		for(i=0;i< heap.size;i++) {
			if (heap.get(i).getKey() == entry.getKey()) break;
		}
		Entry<K, V> newest = new PQEntry<>(key, heap.get(i).getValue());
		heap.set(i, newest);	
		bubble(i);
	 }
  
	public void replaceValue(Entry<K,V> entry, V value)
	 throws IllegalArgumentException {
		checkKey(entry.getKey()); // might throw an exception
		 int i;
			for(i=0;i< heap.size;i++) {
				if (heap.get(i).getKey() == entry.getKey()) break;
			}
			Entry<K, V> newest = new PQEntry<>(heap.get(i).getKey(), value);
			heap.set(i, newest);	
			bubble(i);
	 } 
}   
