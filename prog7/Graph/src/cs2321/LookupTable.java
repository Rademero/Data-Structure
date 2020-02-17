package cs2321;

import java.util.Comparator;

import net.datastructures.*;
/**Michael Romero
*Assignment 6
* This class is used to make a Lookup table based off of a map
* */
public class LookupTable<K extends Comparable<K>, V> extends AbstractMap<K,V> implements SortedMap<K, V> {
	
	/* Use Sorted ArrayList for the Underlying storage for the map of entries.
	 * 
	 */
	private ArrayList<Entry<K,V>> table; 
	private Comparator<K> comp;
	
	public LookupTable(){
		table = new ArrayList<Entry<K,V>>(); 
		 comp = new DefaultComparator<>();
	}
	/** Returns the smallest index for range table[low..high] inclusive storing an entry
	 with a key greater than or equal to k (or else index high+1, by convention). */
	 private int findIndex(K key, int low, int high) {
	 if (high < low) return high + 1; // no entry qualifies
	 int mid = (low + high) / 2;
	 int comp1 = comp.compare(key, table.get(mid).getKey());
	 if (comp1 == 0)
	 return mid; // found exact match
	 else if (comp1 < 0)
	 return findIndex(key, low, mid - 1); // answer is left of mid (or possibly mid)
	 else
	 return findIndex(key, mid + 1, high); // answer is right of mid
	 }
	 private int findIndex(K key) { return findIndex(key, 0, table.size( ) - 1); }
	
	@Override
	public int size() {
		return table.size;
	}

	@Override
	public boolean isEmpty() {
		return table.isEmpty();
	}

	@Override
	//gets an element based off of it's key
	//@Timecomplecity log n
	public V get(K key) {
		int j = findIndex(key);
		if (j == size( ) || comp.compare(key, table.get(j).getKey()) != 0) return null; // no match
		return table.get(j).getValue( );
	}

	@Override
	//adds or changes an element
	//@Timecomplecity log n if updating , n otherwise
	public V put(K key, V value) {
		int j = findIndex(key);
		if (j < size( ) && comp.compare(key, table.get(j).getKey()) == 0) // match exists
		return table.set(j,new mapEntry<K,V>(key,value)).getValue(); //table.get(j).setValue(value);
		table.add(j, new mapEntry<K,V>(key,value)); // otherwise new
		return null;
	}

	@Override
	//removes an element
	//@Timecomplecity log n
	public V remove(K key) {
		int j = findIndex(key);
		if (j == size( ) || comp.compare(key, table.get(j).getKey()) != 0) return null; // no match
		return table.remove(j).getValue( );
	}
	private Entry<K,V> safeEntry(int j) {
		 if (j < 0 || j >= table.size( )) return null;
		 return table.get(j);
		 }


	@Override
	public Iterable<Entry<K, V>> entrySet() {
		return snapshot(0, null);
	}

	@Override
	public Entry<K, V> firstEntry() {
		return safeEntry(0);
	}

	@Override
	public Entry<K, V> lastEntry() {
		return safeEntry(table.size( )-1);
	}

	@Override
	public Entry<K, V> ceilingEntry(K key)  {
		return safeEntry(findIndex(key));
	}

	@Override
	public Entry<K, V> floorEntry(K key)  {
		int j = findIndex(key);
		if (j == size( ) || ! key.equals(table.get(j).getKey( )))
		j--; // look one earlier (unless we had found a perfect match)
		return safeEntry(j);
	}

	@Override
	public Entry<K, V> lowerEntry(K key) {
		return safeEntry(findIndex(key) - 1);
	}
	private Iterable<Entry<K,V>> snapshot(int startIndex, K stop) {
		 ArrayList<Entry<K,V>> buffer = new ArrayList<>( );
		 int j = startIndex;
		 while (j < table.size( ) && (stop == null || comp.compare(stop, table.get(j).getKey()) > 0))
		 buffer.addLast(table.get(j++));
		 return buffer;
		 }
 
	@Override
	public Entry<K, V> higherEntry(K key) {
		int j = findIndex(key);
		if (j < size( ) && key.equals(table.get(j).getKey( )))
		j++; // go past exact match
		return safeEntry(j);
	}
 
	@Override
	public Iterable<Entry<K, V>> subMap(K fromKey, K toKey){
		return snapshot(findIndex(fromKey), toKey);
	}


}
