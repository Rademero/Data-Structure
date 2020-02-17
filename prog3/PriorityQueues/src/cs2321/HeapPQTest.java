package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import net.datastructures.AdaptablePriorityQueue;
import net.datastructures.Entry;

public class HeapPQTest {

	AdaptablePriorityQueue<String, Integer> heappq;
	
	@Before
	public void setUp() throws Exception {
		heappq = new HeapPQ<String, Integer>();
		heappq.insert("Bulbous Bouffant", 16);
		heappq.insert("Gazebo", 6);
		heappq.insert("Balooga", 7); 
		heappq.insert("Galoshes", 8);
		heappq.insert("Eskimo", 6);
		heappq.insert("Mukluks", 7);
		heappq.insert("Macadamia", 9);
	}
 
	@Test
	public void testRemoveMin() { 
		Entry<String, Integer> e;
		String[] expected= {
				"Balooga", 
				"Bulbous Bouffant",
				"Eskimo", 
				"Galoshes", 
				"Gazebo", 
				"Macadamia",
				"Mukluks"
		};
		
		int i=0;
		while(!heappq.isEmpty()){
			e = heappq.removeMin();
			assertEquals(expected[i],  e.getKey());
			i++;
		}
	}


	@Test
	public void testSize() {
		org.junit.Assert.assertEquals((Object) 7, (Object) heappq.size());
	}
	

	@Test
	public void testIsEmpty() {
		org.junit.Assert.assertEquals((Object) false, (Object) heappq.isEmpty());
	}

	@Test
	public void testInsert() {
		heappq.insert("Michael", 0);
		org.junit.Assert.assertEquals((Object) 8, (Object) heappq.size());
	}

	@Test
	public void testMin() {
		org.junit.Assert.assertEquals((Object) "Balooga", (Object) heappq.min().getKey());
	}


	@Test
	public void testRemove() {
		heappq.remove(new PQEntry("Balooga",7));
		Boolean temp = false;
		if (heappq.min().getKey().equals("Bulbous Bouffant"))
			temp =true;
		
		org.junit.Assert.assertEquals((Object) true, (Object)temp );
	}
	@Test
	public void testReplaceKey() {
		heappq.replaceKey(new PQEntry("Balooga",7), "B");
		org.junit.Assert.assertEquals((Object) "B", (Object) heappq.min().getKey());
	}
 
	@Test
	public void testReplaceValue() { 
		heappq.replaceValue(new PQEntry("Balooga",7), 12);
		org.junit.Assert.assertEquals((Object) 12, (Object) heappq.min().getValue());
	}

}
