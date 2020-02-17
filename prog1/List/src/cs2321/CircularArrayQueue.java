	/**
	 * 
	 */
	package cs2321;
	
	import net.datastructures.Queue;
	
	/**
	 * Michael Romero
	 *Assignment 1
	 * This class is used to make an array act like a queue well keeping the propertys of 
	 * a circular array.
	 */
	
	public class CircularArrayQueue<E> implements Queue<E> {
	int size = 0;//sets initial size
	int current = 0;// sets current index
	private E[ ] data;
		public CircularArrayQueue(int queueSize) {
			data = (E[ ]) new Object[queueSize];//sets an array to hold the data
		}
		
		@Override
		//give size
		public int size() {
			return size;
		}
	
		@Override
		//returns true if empty
		public boolean isEmpty() {
			return size == 0;
		}
	
		@Override
		//adds new data at the end
		public void enqueue(E e) {
			if(current > size)
				throw new IndexOutOfBoundsException("Illegal index: "+ current);// if out of index, throws an exception
			data[size] = e;
			size++;
		}
	
		@Override
		//give the value of the first element without removing it
		public E first() {
			return data[current];
		}
	
		@Override
		//gives the first element and removes it from the list
		public E dequeue() {
			if (!this.isEmpty()) {//check to see if possible 
				E temp = data[current];
				current = current+1 % data.length;
				size--;
				return temp;
			}
			throw new IndexOutOfBoundsException("Illegal index: "+ current);//throws exception 
		}
	}
