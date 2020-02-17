package cs2321;
import java.util.Iterator;

import net.datastructures.Position;
import net.datastructures.PositionalList;


public class DoublyLinkedList<E> implements PositionalList<E> {

	//Nested class to set up nodes
			private static class Node<E> implements Position<E> {
			private E element; // reference to the element stored at this node
			private Node<E> prev; // reference to the previous node in the list
			private Node<E> next; // reference to the subsequent node in the list
			//sets up base node
			public Node(E e, Node<E> p, Node<E> n) {
			element = e;
			prev = p;
			next = n;
				}
			//returns the element to user
			public E getElement( ) throws IllegalStateException {
			 if (next == null) // catch for default node
				 throw new IllegalStateException("Position no longer valid");
			 return element;
			 }
			//gets the previous node
			 public Node<E> getPrev( ) {
			 return prev;
			 }
			 //gets next node
			 public Node<E> getNext( ) {
			 return next;
			 }
			 //changes the value of the element
			 public void setElement(E e) {
			 element = e;
			 }
			 //sets the value of the previous element 
			 public void setPrev(Node<E> p) {
			 prev = p;
			 }
			//sets the value of the next element
			 public void setNext(Node<E> n) {
			 next = n;
			 }
		 } 
		
		//end of nested class//
		private Node<E> header; // header node
		private Node<E> trail; // trailer node
		private int size = 0;
	//sets up default list
		public DoublyLinkedList() {
			header = new Node<>(null, null, null); // create header
			trail = new Node<>(null, header, null); // create trail
			header.setNext(trail);
		}
		// used to keep the position of the nodes 
		private Node<E> validate(Position<E> p) throws IllegalArgumentException {
			 if (!(p instanceof Node)) throw new IllegalArgumentException("Invalid p");
			 Node<E> node = (Node<E>) p; // cast to node with type E
			 if (node.getNext( ) == null) // checks for empty node
			 throw new IllegalArgumentException("p is no longer in the list");
			 return node;
			 }
		//used to return position of the node
		private Position<E> position(Node<E> node) {
			 if (node == header || node == trail)
			 return null; // makes sure that the user never uses header or tail node
			 return node;
			 }
		@Override
		//gives size of list
		public int size() {
			return size;
		}

		@Override
		//returns true if empty
		public boolean isEmpty() {
			return size==0;
		}

		@Override
		//gives the value of the first element
		public Position<E> first() {
			return position(header.getNext( ));
		}

		@Override
		//gives the value of the last element
		public Position<E> last() {
			return position(trail.getPrev( ));
		}

		@Override
		//gives the value of the previous element
		public Position<E> before(Position<E> p) throws IllegalArgumentException {
			Node<E> node = validate(p);
			 return position(node.getPrev( ));
		}

		@Override
		//gives the value of the next element
		public Position<E> after(Position<E> p) throws IllegalArgumentException {
			Node<E> node = validate(p);
			 return position(node.getNext( ));
		}
		//adds a new element in between 2 elements
		private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
			 Node<E> newest = new Node<>(e, pred, succ); // create and link a new node
			 pred.setNext(newest);
			 succ.setPrev(newest);
			 size++;
			 return newest;
			 }

		@Override
		//adds new element to front of the list
		public Position<E> addFirst(E e) {
			return addBetween(e, header, header.getNext( ));
		}

		@Override
		//add new element at the end
		public Position<E> addLast(E e) {
			return addBetween(e, trail.getPrev( ), trail);
		}

		@Override
		//adds a new element before the current one
		public Position<E> addBefore(Position<E> p, E e)
				throws IllegalArgumentException {
			Node<E> node = validate(p);
			 return addBetween(e, node.getPrev( ), node);
		}

		@Override
		//adds a new element after the current one
		public Position<E> addAfter(Position<E> p, E e)
				throws IllegalArgumentException {
			Node<E> node = validate(p);
			 return addBetween(e, node, node.getNext( ));
		}

		@Override
		//Changes to position of the element
		public E set(Position<E> p, E e) throws IllegalArgumentException {
			Node<E> node = validate(p);
			 E answer = node.getElement( );
			 node.setElement(e);
			 return answer;
		}

		@Override
		//removes an element
		public E remove(Position<E> p) throws IllegalArgumentException {
			Node<E> node = validate(p);
			//shifts the nodes to fill holes in list
			 Node<E> predecessor = node.getPrev( );
			 Node<E> successor = node.getNext( );
			 predecessor.setNext(successor);
			 successor.setPrev(predecessor);
			 size--;
			 //cleans up leftover nodes
			 E answer = node.getElement( );
			 node.setElement(null);
			 node.setNext(null); 
			 node.setPrev(null);
			 return answer;
		}
		//used to create an iterator for elements within the list
			private class ElementIterator implements Iterator<E> {
				//sets up new iterator
				public Iterator<Position<E>> posIterator = new PositionIterator( );
				//checks for next
				 public boolean hasNext( ) { return posIterator.hasNext( ); }
				 //returns next element
				 public E next( ) { return posIterator.next( ).getElement( ); }
				 //removes element from itorater list
				 public void remove( ) { posIterator.remove( ); }
				 }
			//end of nested class    //
			//creates iterator based off of position
			private class PositionIterator implements Iterator<Position<E>> {
				private Position<E> cursor = first( ); // position of the next element
				private Position<E> recent = null; // position of previous element
				 //checks to see if current element has next
				public boolean hasNext( ) { return (cursor != null); }
			//Returns the next position in the iterator.
				public Position<E> next( ){
				if (cursor == null) return null; //makes sure elements exist to iterate
				 recent = cursor; 
				 cursor = after(cursor);
				 return recent;
				 }
				 //Removes the element 
				public void remove( ) throws IllegalStateException {
				 if (recent == null) throw new IllegalStateException("nothing to remove");
				 DoublyLinkedList.this.remove(recent); // remove from outer list
				 recent = null; // makes sure not to over remove
				 }
				}
		//end of nested class    //
			//sets up so the iterator has an itreable object
			 private class PositionIterable implements Iterable<Position<E>> {
				 //sets up a PositionIterator
			 public Iterator<Position<E>> iterator( ) { return new PositionIterator( ); }
			 } //------------ end of nested PositionIterable class ------------
			
			 // Returns the list as iterable 
			 public Iterable<Position<E>> positions1( ) {
			 return new PositionIterable( ); // create a new instance of the inner class
			 }

		@Override
		//makes a new ElementIterator
		public Iterator<E> iterator() {
			return new ElementIterator( );
		}

		@Override
		//makes a new PositionIterable for the Position iterator 
		public Iterable<Position<E>> positions() {
			return new PositionIterable( );
		}
		//removes the first element of the list
		public E removeFirst() throws IllegalArgumentException {
			return remove(header.next);
		}
		//removes the last element of the list
		public E removeLast() throws IllegalArgumentException {
			return remove(trail.prev);
		}
}
