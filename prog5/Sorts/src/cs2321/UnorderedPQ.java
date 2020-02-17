package cs2321;

import java.util.Comparator;

import net.datastructures.*;
/**
 * Michael Romero
 *Assignment 3
 * This class is used to make an priority queue with an Un-ordered list
 */

public class UnorderedPQ<K,V> implements PriorityQueue<K,V> {
	private DoublyLinkedList<Entry<K,V>> list = new DoublyLinkedList<>();
	public UnorderedPQ() {
		this(new DefaultComparator<>());
	}
	private Comparator<K> comp;
	public UnorderedPQ(Comparator<K> c) {
		this.comp = c;
	}
	 protected boolean checkKey(K key) throws IllegalArgumentException {
		 try { 
			 return (comp.compare(key,key) == 0); // see if key can be compared to itself 
		 } catch (ClassCastException e) {
			 throw new IllegalArgumentException("Incompatible key"); 
		 }
}
	@Override
	public int size() {
		return list.size();
	}
	 private Position<Entry<K,V>> findMin() { // only called when nonempty
		 Position<Entry<K,V>> small = list.first();
		 for (Position<Entry<K,V>> walk : list.positions())
			if (comp.compare(walk.getElement().getKey(), small.getElement().getKey()) < 0)
				 small = walk; // found an even smaller key
		 	return small; 
	 } 

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		 checkKey(key); // auxiliary key-checking method (could throw exception)
		 Entry<K,V> newest = new PQEntry<>(key, value);
		 list.addLast(newest);
		 return newest; 
	}

	@Override
	public Entry<K, V> min() {
		 if (list.isEmpty()) return null;
		 return findMin().getElement();
	}

	@Override
	public Entry<K, V> removeMin() {
		 if (list.isEmpty()) return null; 
		 return list.remove(findMin());
	}
	
	

}
