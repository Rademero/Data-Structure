package cs2321;


import cs2321.AbstractMap.mapEntry;
import net.datastructures.Entry;
import net.datastructures.Map;

import java.util.Comparator;
import java.util.Iterator;
/**Michael Romero
*Assignment 6
* This class is used to make a UnorderedMap
* */
public class UnorderedMap<K,V> extends AbstractMap<K,V> {
	
	/* Use ArrayList or DoublyLinked list for the Underlying storage for the map of entries.
	 * Uncomment one of these two lines;
	 * private ArrayList<Entry<K,V>> table; 
	 * private DoublyLinkedList<Entry<K,V>> table;
	 */
	private ArrayList<mapEntry<K,V>> table;
	public UnorderedMap() {
		table = new ArrayList<>( );
	}
		

	@Override
	public int size() {
		return table.size;
	}

	@Override
	public boolean isEmpty() {
		return table.isEmpty();
	}
	//finds an element 
	//@Timecomplecity log n
	private int findIndex(K key) {
		 int n = table.size( );
		 for (int j=0; j < n; j++)
		 if (table.get(j).getKey( ).equals(key))
		 return j;
		 return -1; // special value denotes that key was not found
		 }

	@Override
	//gets an value from the map
	//@Timecomplecity log n
	public V get(K key) {
		int j = findIndex(key);
		if (j == -1) return null; // not found
		return table.get(j).getValue( );
	}
	//gets an element based off of key, this is a getter
	public Entry<K,V> getEntry(K key){
		int n = table.size( );
		 for (int j=0; j < n; j++)
		 if (table.get(j).getKey( ).equals(key))
		 return table.get(j);
		 return null; // special value denotes that key was not found
	}

	@Override
	//adds or changes a value from the list
	//@Timecomplecity log n if updating , n otherwise
	public V put(K key, V value) {
		int j = findIndex(key);
		if (j == -1) {
		 table.add(j+1,new mapEntry<>(key, value)); // add new entry
		 return null;
		} else // key already exists
		return table.set(j,new mapEntry<K,V>(key,value)).getValue();
	}

	@Override
	//removes an element
	//@Timecomplecity log n
	public V remove(K key) {
		int j = findIndex(key);
		int n = size( );
		if (j == -1) return null; // not found
		V answer = table.get(j).getValue( );
		if (j != n - 1)
		table.set(j, table.get(n-1)); // relocate last entry to hole created by removal
		table.remove(n-1); // remove last entry of table
		return answer;
	}

	public class EntryIterator implements Iterator<Entry<K,V>> {
		private int j=0;
		public boolean hasNext( ) { return j < table.size( ); }
		public Entry<K,V> next( ) {
		//if (j == table.size( )) throw new NoSuchElementException( );
		return table.get(j++);
		}
		public void remove( ) { throw new UnsupportedOperationException( ); }
		}
		public class EntryIterable implements Iterable<Entry<K,V>> {
		public Iterator<Entry<K,V>> iterator( ) { return new EntryIterator( ); }
	  }
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		return new EntryIterable( );
	}

}
