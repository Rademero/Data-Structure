package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TaskSchedulingTest {
	TaskScheduling ts;
	int [][] task  = {{1,4},{1,3},{2,5},{3,7},{4,7},{6,9},{7,8}};
	int [][] task2  = {{1,4},{6,8},{10,15}};
	int [][] task3  = {{2,5},{2,5},{1,4},{1,4}};
	@Before
	public void setUp() throws Exception {
		ts = new TaskScheduling();
		}

	@Test
	public void testNumOfMachines() {
		//1 is 3
		//2 is 1
		//3 is 4
		org.junit.Assert.assertEquals((Object) 3, (Object) ts.NumOfMachines(task));
	}
}
 