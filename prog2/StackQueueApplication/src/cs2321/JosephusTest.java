package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JosephusTest {
	String[] test1 ={"C","F","D","B","E","A"};
	String[] test2 ={};
	String[] test3 = {"Alice", "Mike", "Tim", "Jhon"};
	Josephus game;
	String[] person = {"A" , "B","C","D","E","F"};
	
	public DoublyLinkedList init() {
 
		return new DoublyLinkedList<String>();
	 
	}
	@Before
	public void setUp() throws Exception {
		 game = new Josephus();
	}

	@Test
	public void testOrder1() {
		DoublyLinkedList temp = game.order(person, 3);
		
	//org.junit.Assert.assertEquals((Object) test1[0], (Object) temp.removeFirst());
	} 

	@Test
	public void testOrder2() {
		DoublyLinkedList temp = game.order(person, 3); 
		
		Boolean t = true;
		int i = 0;
		while (!temp.isEmpty()) {
			if (!(test1[i]==temp.removeFirst())){
				t = false;
			}
			i++; 
		} 
		org.junit.Assert.assertEquals((Object) true, (Object) t);
	} 

	@Test
	public void testOrder3() {
		DoublyLinkedList temp = game.order(test2, 3);  
		
	}

	@Test
	public void testOrder4() {
		String[] ans = {"Jhon","Alice", "Tim", "Mike", };
DoublyLinkedList temp = game.order(test3, 4); 
		Boolean t = true;
		int i = 0;
		while (!temp.isEmpty()) {
			if (!(ans[i]==temp.removeFirst())){
				t = false;
			}
			i++; 
		} 
		org.junit.Assert.assertEquals((Object) true, (Object) t);
	} 

}
