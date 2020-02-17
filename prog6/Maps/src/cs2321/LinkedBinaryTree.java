package cs2321;
import java.util.Iterator;

import net.datastructures.*;
	
/**Michael Romero
*Assignment 6
* This class is used to make a Binary Search Tree
* */
public class LinkedBinaryTree<E> implements BinaryTree<E>{
public int size =0;

//node class 
protected static class Node<E> implements Position<E> {
 private E element; // an element stored at this node
 private Node<E> parent; // a reference to the parent node (if any)
 private Node<E> left; // a reference to the left child (if any)
 private Node<E> right; // a reference to the right child (if any)
 /** Constructs a node with the given element and neighbors. */
 public Node(E e, Node<E> above, Node<E> leftChild, Node<E> rightChild) {
 element = e;
 parent = above;
 left = leftChild;
 right = rightChild;
 }
 // accessor methods
 public E getElement( ) { return element; }
 public Node<E> getParent( ) { return parent; }
 public Node<E> getLeft( ) { return left; }
 public Node<E> getRight( ) { return right; }
 // update methods
 public void setElement(E e) { element = e; }
 public void setParent(Node<E> parentNode) { parent = parentNode; }
 public void setLeft(Node<E> leftChild) { left = leftChild; }
 public void setRight(Node<E> rightChild) { right = rightChild; }
 } //        end of nested Node class 
//creates a new node
protected Node<E> createNode(E e, Node<E> parent,
 Node<E> left, Node<E> right) {
 return new Node<E>(e, parent, left, right);
 }

 protected Node<E> root = null;
@Override
	public Position<E> root() {
		return root;
	}
	
	public  LinkedBinaryTree( ) {
	}
	//checks if the key type is correct
	protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
		if (!(p instanceof Node))
		 throw new IllegalArgumentException("Not valid position type");
		 Node<E> node = (Node<E>) p; // safe cast
	 if (node.getParent( ) == node) // our convention for defunct node
		 throw new IllegalArgumentException("p is no longer in the tree");
		 return node;
		}
	
	@Override
	//gets parent
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		 return node.getParent( );
	}

	@Override
	//Iterator for the children of a node
	public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
		ArrayList<Position<E>> snapshot = new ArrayList<>(2); // max capacity of 2
		if (left(p) != null)
		snapshot.addLast(left(p));
		if (right(p) != null)
		snapshot.addLast(right(p));
		return snapshot;
		}
	

	@Override
	/* count only direct child of the node, not further descendant. */
	public int numChildren(Position<E> p) throws IllegalArgumentException {
		int childNum = 0;
		Node<E> node = validate(p);
		if (node.left != null) {
			childNum++;
		}
		if(node.right != null) {
			childNum++;
		}
		return childNum; 
	}

	@Override
	//checks if the node isn't a sentinel node
	public boolean isInternal(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		if(node.left !=null || node.right !=null) {
			return true;
		}
		return false;
	}

	@Override
	//checks if the entry is a sentinel node
	public boolean isExternal(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		if(node.left !=null || node.right !=null) {
			return false;
		}
		return true;
	}

	@Override
	//checks if a node is the root 
	public boolean isRoot(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		if (node.parent != null) {
			return false;
		}
		return true;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}
	//sets up the order of how the data is iterated in an inorder traversal
	private void inorderSubtree(Position<E> p, ArrayList<Position<E>> snapshot) {
		if (left(p) != null)
		inorderSubtree(left(p), snapshot);
		snapshot.addLast(p);
		if (right(p) != null)
		inorderSubtree(right(p), snapshot);
		}
	public Iterable<Position<E>> inorder( ) {
		ArrayList<Position<E>> snapshot = new ArrayList<>( );
		if (!isEmpty( ))
		inorderSubtree(root( ), snapshot); // fill the snapshot recursively
		return snapshot;
		}
	private class ElementIterator implements Iterator<E> {
		Iterator<Position<E>> posIterator = positions( ).iterator( );
		public boolean hasNext( ) { return posIterator.hasNext( ); }
		public E next( ) { return posIterator.next( ).getElement( ); } // return element!
		public void remove( ) { posIterator.remove( ); }
		}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator( ); }
	

	@Override
	public Iterable<Position<E>> positions() {
		return inorder( );
	}

	@Override
	//gets the left child
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		 return node.getLeft( );
	}

	@Override
	//gets the right child
	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		 return node.getRight( );
	}

	@Override
	//gets the other child node 
	public Position<E> sibling(Position<E> p) throws IllegalArgumentException {
		Node<E> node = validate(p);
		Node<E> Pnode = node.parent;
		if(Pnode.getLeft().equals(node)) {
			return Pnode.right;
		}else if(Pnode.getRight().equals(node)) {
			return Pnode.left;
		}
		return null;
	}
	
	/* creates a root for an empty tree, storing e as element, and returns the 
	 * position of that root. An error occurs if tree is not empty. 
	 */
	public Position<E> addRoot(E e) throws IllegalStateException {
		if (!isEmpty( )) throw new IllegalStateException("Tree is not empty");
		 root = createNode(e, null, null, null);
		 size = 1;
		 return root;
	}
	
	/* creates a new left child of Position p storing element e, return the left child's position.
	 * If p has a left child already, throw exception IllegalArgumentExeption. 
	 */
	public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		 if (parent.getLeft( ) != null)
		 throw new IllegalArgumentException("p already has a left child");
		 Node<E> child = createNode(e, parent, null, null);
		 parent.setLeft(child);
		 size++;
		 return child;
	} 

	/* creates a new right child of Position p storing element e, return the right child's position.
	 * If p has a right child already, throw exception IllegalArgumentExeption. 
	 */
	public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
		Node<E> parent = validate(p);
		 if (parent.getRight( ) != null)
		 throw new IllegalArgumentException("p already has a right child");
		 Node<E> child = createNode(e, parent, null, null);
		 parent.setRight(child);
		 size++;
		 return child;
	}
	//sets the value and key of an element
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		 Node<E> node = validate(p);
		 E temp = node.getElement( );
		 node.setElement(e);
		 return temp;
		 }
	
	/* Attach trees t1 and t2 as left and right subtrees of external Position. 
	 * if p is not external, throw IllegalArgumentExeption.
	 */
	public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) {
	Node<E> node = validate(p);
	 if (isInternal(p)) throw new IllegalArgumentException("p must be a leaf");
	 size += t1.size( ) + t2.size( );
	 if (!t1.isEmpty( )) { // attach t1 as left subtree of node
	 t1.root.setParent(node);
	 node.setLeft(t1.root);
	 t1.root = null;
	 t1.size = 0;
	 }
	 if (!t2.isEmpty( )) { // attach t2 as right subtree of node
	 t2.root.setParent(node);
	 node.setRight(t2.root); 
	 t2.root = null;
	 t2.size = 0;
	 }
	
	}
	
	//removes an element from the tree, also fixes the order
	//@Timecomplecity log n
	public E remove(Position<E> p)throws IllegalArgumentException {
		Node<E> node = validate(p);
		 if (numChildren(p) == 2)
			 throw new IllegalArgumentException("p has two children");
		 Node<E> child = (node.getLeft( ) != null ? node.getLeft( ) : node.getRight( ) );
		 if (child != null)
			 child.setParent(node.getParent( )); // child's grandparent becomes its parent
		 if (node == root)
			 root = child; // child becomes root
		 else {
		 Node<E> parent = node.getParent( );
		 if (node == parent.getLeft( ))
		 parent.setLeft(child);
		 else
		 parent.setRight(child);
		 }
		 size--;
		 E temp = node.getElement( );
		 node.setElement(null); // help garbage collection
		 node.setLeft(null); 
		 node.setRight(null);
		 node.setParent(node); // our convention for defunct node
		 return temp;
		 }
 
	 
}
