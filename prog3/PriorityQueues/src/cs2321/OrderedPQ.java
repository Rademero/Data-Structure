package cs2321;

import java.util.Comparator;

import net.datastructures.*;
/**
 * Michael Romero
 *Assignment 3
 * This class is used to make an priority queue with an ordered list
 */

public class OrderedPQ<K,V> implements PriorityQueue<K,V> {
	private DoublyLinkedList<Entry<K,V>> list = new DoublyLinkedList<>();
	public OrderedPQ() {
		this(new DefaultComparator<>());
	}
	protected boolean checkKey(K key) throws IllegalArgumentException {
		 try { 
			 return (comp.compare(key,key) == 0); // see if key can be compared to itself 
		 } catch (ClassCastException e) {
			 throw new IllegalArgumentException("Incompatible key"); 
		 }
}
	private Comparator<K> comp; 
	public OrderedPQ(Comparator<K> c) {
		this.comp = c;
	}
	
	@Override
	public int size() {
		return list.size(); 
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		checkKey(key); // auxiliary key-checking method (could throw exception)
		Entry<K,V> newest = new PQEntry<>(key, value);
		Position<Entry<K,V>> walk = list.last();
// walk backward, looking for smaller key
		while (walk != null && comp.compare(newest.getKey(), walk.getElement().getKey())< 0) { 
			walk = list.before(walk); 
		}
			if (walk == null) 
				list.addFirst(newest); // new key is smallest
			else
				list.addAfter(walk, newest); // newest goes after walk  
		return newest;
	}

	@Override
	public Entry<K, V> min() { 
		 if (list.isEmpty()) return null;
		 return list.first().getElement(); 
	}

	@Override
	public Entry<K, V> removeMin() {
		 if (list.isEmpty()) return null; 
		 return list.remove(list.first());
	}

}
