package cs2321;

import java.util.Iterator;

import net.datastructures.List;

public class ArrayList<E> implements List<E> {

	public int capacity = 16;//Starting capacity
	private E[ ] data;//starting list
	int size = 0;//starting size
	//Default list setup
	public ArrayList( ) { this(16); }
	
	//sets up new list to be used
	public ArrayList(int capacity) {
		data = (E[ ]) new Object[capacity];//Initialization of list
	}

	@Override
	//returns size
	public int size() {
		return size;
	}

	@Override
	//returns true if empty
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	//returns value of selected element
	public E get(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		return data[i];
	}

	@Override
	//sets the value of an element
	public E set(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		 E temp = data[i];
		data[i] = e;
		return temp;
	}

	@Override
	//adds a new element into the list
	public void add(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size + 1);
		if (size == data.length) { // not enough capacity
			capacity =capacity*2;
			E[] temp =  (E[ ]) new Object[capacity];//doubles the total capacity
			for (int k = 0; k < size; k++) {
				temp[k] = data[k];
			}
			data = temp; 
		}
		for (int k=size-1; k >= i; k--) // start by shifting rightmost
			data[k+1] = data[k];//only of room needs to be made
		 data[i] = e; // ready to place the new element
		 size++;
	}
	
	@Override
	//removes an element from the list
	public E remove(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		 E temp = data[i];
		 //fills in gaps of list
		 for (int k=i; k < size-1; k++) 
			 data[k] = data[k+1];
		 data[size-1] = null; 
		 size--;
		 return temp;
		}
	
		
	@Override
	//makes a new iterator
	public Iterator<E> iterator() {
		return new ArrayIteror();
	}
//adds to the front of the list
	public void addFirst(E e)  {
		add(0, e);
	}
	//adds to the back of a list
	public void addLast(E e)  {
		add(size, e); 
	}
	//removes first element
	public E removeFirst() throws IndexOutOfBoundsException {
		return this.remove(0);	
	}
	//removes last element
	public E removeLast() throws IndexOutOfBoundsException {
		return this.remove(size-1);
	}
	
	// Return the capacity of array, not the number of elements.
	public int capacity() {
		return capacity;
	}
	//used to check if an index is within bounds of the list
	protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
		 if (i < 0 || i >= n) {
		 throw new IndexOutOfBoundsException("Illegal index: " + i);
		 }
		}
	//nested class to create an iterator
	private class ArrayIteror implements Iterator{//inner class
		int cursor =0;//Current position
		//checks if there is anther element to get
		public boolean hasNext(){
		if (cursor > size-1)return false;
		return true;
		}
		//gets next element
		public E next(){
		E e=get(cursor);
		cursor++;
		return e;
		}
		}
// end of nested class
	
}
