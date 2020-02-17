package cs2321;

public class Josephus {
	
	/**
	 * Michael Romero
	 *Assignment 2
	 * This class is used to make an array act like a array list
	 */
	
	/**
	 * All persons sit in a circle. When we go around the circle, initially starting
	 * from the first person, then the second person, then the third... 
	 * we count 1,2,3,.., k-1. The next person, that is the k-th person is out. 
	 * Then we restart the counting from the next person, go around, the k-th person 
	 * is out. Keep going the same way, when there is only one person left, she/he 
	 * is the winner. 
	 *  
	 * @parameter persons  an array of string which contains all player names.
	 * @parameter k  an integer specifying the k-th person will be kicked out of the game
	 * @return return a doubly linked list in the order when the players were out of the game. 
	 *         the last one in the list is the winner.  
	 */
	public DoublyLinkedList<String> order(String[] persons, int k ) {
		//TODO: implement this method with the help of CircularArrayQueue
		CircularArrayQueue<String> inGame = new CircularArrayQueue(persons.length);
		DoublyLinkedList<String> outPut = new DoublyLinkedList();
		for(int i=0; i < persons.length;i++) {//used to load all data into an array
			inGame.enqueue(persons[i]);  
		}
		while (!inGame.isEmpty()) {
			int current =0; 
			for(int i = 0; i < k-1; i++) { //Cycles through the data
				String temp = inGame.dequeue();
				inGame.enqueue(temp);  
				current++;
			} 
			String temp = inGame.dequeue();//cuts the k-th person from the list
			outPut.addLast(temp);   
		}   
		return outPut; 
	}	
}
