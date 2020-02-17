package cs2321;

import java.util.Comparator;

import net.datastructures.*;
/**Michael Romero
*Assignment 6
* This class is used to make a Binary Search Tree
* */
public class BinarySearchTree<K extends Comparable<K>,V> extends AbstractMap<K,V> implements SortedMap<K,V> {
	private Comparator<K> c;
	//all the data will be stored in tree
	LinkedBinaryTree<Entry<K,V>> tree; 
	int size;  //the number of entries (mappings)
	
	
	 //default constructor 
	public BinarySearchTree() {
		 tree = new LinkedBinaryTree<Entry<K,V>>();
		 tree.addRoot(null);
		 c = new DefaultComparator<>();
	}
	
	
	 //Return the tree. The purpose of this method is purely for testing. 
	public LinkedBinaryTree<Entry<K,V>> getTree() {
		return tree;
	}
	//Looks for an entry from within the tree
	//@Timecomplecity log n
	private Position<Entry<K,V>> treeSearch(Position<Entry<K,V>> p, K key) {
		 if (tree.isExternal(p))
		 return p; // key not found; return the final leaf
		 int comp = c.compare(key, p.getElement( ).getKey());
		 if (comp == 0)
		 return p; // key found; return its position
		 else if (comp < 0)
		 return treeSearch(tree.left(p), key); // search left subtree
		 else
		 return treeSearch(tree.right(p), key); // search right subtree
		 }
	
	@Override
	//returns the size of the tree
	public int size(){
		return (tree.size()-1)/2;
	}
	@Override
	//gets the value of an entry based off of a key
	//@Timecomplecity log n
	public V get(K key) {
		 checkKey(key); // may throw IllegalArgumentException
		 Position<Entry<K,V>> p = treeSearch(tree.root(), key);
		 if (tree.isExternal(p)) return null; // unsuccessful search
		 return p.getElement( ).getValue( );
	}

	@Override
	//this will either make a new entry or change an entry
	//@Timecomplecity log n if updating , n otherwise
	public V put(K key, V value) {
		//This is log n because of it's key being checked than either changer or added to the list
		checkKey(key); // may throw IllegalArgumentException
		Entry<K,V> newEntry = new mapEntry<>(key, value);
		Position<Entry<K,V>> p = treeSearch(tree.root( ), key);
		if (tree.isExternal(p)) { // key is new
		expandExternal(p, newEntry);
		return null;
		} else { // replacing existing key
		V old = p.getElement( ).getValue( );
		tree.set(p, newEntry);
		return old;
		}
	}
	//creates new sentinel nodes at the leaf node
	private void expandExternal(Position<Entry<K,V>> p, Entry<K,V> entry) {
		 tree.set(p, entry); // store new entry at p
		 tree.addLeft(p, null); // add new sentinel leaves as children
		 tree.addRight(p, null);
		 }
	//checks if the key is allowed in the list
	protected boolean checkKey(K key) throws IllegalArgumentException {
		try {
			return (c.compare(key, key) == 0); // see if key can be compared to itself
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Incompatible key");
		}
	}

	@Override
	//removes an element from the tree
	//@Timecomplecity n log n
	public V remove(K key) {
		//the log n is the height of the tree than repeats n amount of time for underneath nodes
		 checkKey(key);                          // may throw IllegalArgumentException
		    Position<Entry<K,V>> p = treeSearch(tree.root(), key);
		    if (tree.isExternal(p)) {                    // key not found
		      return null;
		    } else {
		      V old = p.getElement().getValue();
		      if (tree.isInternal(tree.left(p)) && tree.isInternal(tree.right(p))) { // both children are internal
		        Position<Entry<K,V>> replacement = treeMax(tree.left(p));
		        tree.set(p, replacement.getElement());
		        p = replacement;
		      } // now p has at most one child that is an internal node
		      Position<Entry<K,V>> leaf = (tree.isExternal(tree.left(p)) ? tree.left(p) : tree.right(p));
		      Position<Entry<K,V>> sib = tree.sibling(leaf);
		      tree.remove(leaf);
		      tree.remove(p); // sib is promoted in p's place
		      return old;
		    }
	}
	//looks for the max element in the list
	//@Timecomplecity log n
	protected Position<Entry<K,V>> treeMax(Position<Entry<K,V>> p) {
		 Position<Entry<K,V>> walk = p;
		 while (tree.isInternal(walk))
		 walk = tree.right(walk);
		 return tree.parent(walk); // we want the parent of the leaf
		 }

	@Override
	//setup for iterator
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K,V>> buffer = new ArrayList<>(size( ));
		return buffer;
	}

	@Override
	//returns root
	public Entry<K, V> firstEntry() {
		if (isEmpty()) return null;
	    return treeMin(tree.root()).getElement();
	}

	@Override
	//find a leaf node an returns it
	public Entry<K, V> lastEntry() {
		if (isEmpty( )) return null;
		 return treeMax(tree.root( )).getElement( );
	}

	@Override
	public Entry<K, V> ceilingEntry(K key) {
		 checkKey(key);                              // may throw IllegalArgumentException
		    Position<Entry<K,V>> p = treeSearch(tree.root(), key);
		    if (tree.isInternal(p)) return p.getElement();   // exact match
		    while (!tree.isRoot(p)) {
		      if (p == tree.left(tree.parent(p)))
		        return tree.parent(p).getElement();          // parent has next greater key
		      else
		        p = tree.parent(p);
		    }
		    return null;                                // no such ceiling exists
	}

	@Override
	public Entry<K, V> floorEntry(K key)  {
		checkKey(key); // may throw IllegalArgumentException
		 Position<Entry<K,V>> p = treeSearch(tree.root( ), key);
		 if (tree.isInternal(p)) return p.getElement( ); // exact match
		 while (!tree.isRoot(p)) {
		 if (p == tree.right(tree.parent(p)))
		 return tree.parent(p).getElement( ); // parent has next lesser key
		 else
		 p = tree.parent(p);
		 }
		 return null;
	}

	@Override
	public Entry<K, V> lowerEntry(K key) {
		checkKey(key); // may throw IllegalArgumentException
		 Position<Entry<K,V>> p = treeSearch(tree.root( ), key);
		 if (tree.isInternal(p) && tree.isInternal(tree.left(p)))
		 return treeMax(tree.left(p)).getElement( ); // this is the predecessor to p
		 // otherwise, we had failed search, or match with no left child
		 while (!tree.isRoot(p)) {
		 if (p == tree.right(tree.parent(p)))
		 return tree.parent(p).getElement( ); // parent has next lesser key
		 else
		 p = tree.parent(p);
		 }
		 return null;
	}
	 protected Position<Entry<K,V>> treeMin(Position<Entry<K,V>> p) {
		    Position<Entry<K,V>> walk = p;
		    while (tree.isInternal(walk))
		      walk = tree.left(walk);
		    return tree.parent(walk);              // we want the parent of the leaf
		  }

	@Override
	public Entry<K, V> higherEntry(K key){
		  checkKey(key);                               // may throw IllegalArgumentException
		    Position<Entry<K,V>> p = treeSearch(tree.root(), key);
		    if (tree.isInternal(p) && tree.isInternal(tree.right(p)))
		      return treeMin(tree.right(p)).getElement();     // this is the successor to p
		    // otherwise, we had failed search, or match with no right child
		    while (!tree.isRoot(p)) {
		      if (p == tree.left(tree.parent(p)))
		        return tree.parent(p).getElement();           // parent has next lesser key
		      else
		        p = tree.parent(p);
		    }
		    return null;                                 // no such greater key exists
	}
	private void subMapRecurse(K fromKey, K toKey, Position<Entry<K,V>> p,
			 ArrayList<Entry<K,V>> buffer) {
			int i = 0;
			 if (tree.isInternal(p))
			 if (c.compare(p.getElement( ).getKey(), fromKey) < 0)
			 // p's key is less than fromKey, so any relevant entries are to the right
			 subMapRecurse(fromKey, toKey, tree.right(p), buffer);
			 else {
			 subMapRecurse(fromKey, toKey, tree.left(p), buffer); // first consider left subtree
			 if (c.compare(p.getElement( ).getKey(), toKey) < 0) { // p is within range
			 buffer.addLast(p.getElement( )); // so add it to buffer, and consider
			 i++;
			 subMapRecurse(fromKey, toKey, tree.right(p), buffer); // right subtree as well
			 }
			 }
			 }

	@Override
	public Iterable<Entry<K, V>> subMap(K fromKey, K toKey)
			throws IllegalArgumentException {
		ArrayList<Entry<K,V>> buffer = new ArrayList<>(size( ));
		 if (c.compare(fromKey, toKey) < 0) // ensure that fromKey < toKey
		 subMapRecurse(fromKey, toKey, tree.root( ), buffer);
		 return buffer;
	}

	@Override
	public boolean isEmpty() {
		return tree.isEmpty();
	}

	

}
