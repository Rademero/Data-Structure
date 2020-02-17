package cs2321;
/**
 * Michael Romero
 *Assignment 2
 * This class is used to make an array act like a array list
 */
import net.datastructures.Stack;

public class DLLStack<E> implements Stack<E> {
	DoublyLinkedList<E> list = new DoublyLinkedList();
	@Override
	//returns size
	public int size() {
		return list.size();
	} 

	@Override 
	//returns true if empty
	public boolean isEmpty() {
		return list.isEmpty();
	}
 
	@Override
	//adds an element to the end
	public void push(E e) { 
		list.addLast(e);
	}

	@Override
	//looks at the last element
	public E top() {
		return list.last().getElement();  
	}

	@Override
	//removes/returns the last element
	public E pop() {
		return list.removeLast();
	}

}
