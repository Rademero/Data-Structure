package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FractionalKnapsackTest {
	FractionalKnapsack kn;
	int [][] items  = {{4,12},{8,32},{8,32},{2,40},{6,30},{1,50}};
	int comp;
	@Before
	public void setUp() throws Exception {
		comp = 10;
		kn = new FractionalKnapsack();
	}

	@Test
	public void testMaximumValue() {
		org.junit.Assert.assertEquals((Object) 124., (Object) kn.MaximumValue(items, comp) );
	}
 
}

