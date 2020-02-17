package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PostfixExpressionTest {
	String test1 ="5 3 2 * +";
	String test2 = "4 20 5 + * 6 -";
	String test3 = "10 2 3 4 5 6 2 / + * 9 * + * -";
	String test4 = "9 3 1 - -3 * + 10 2 / +";
	String test5 = "3 5 2 + *";
	String test6 = "3 5 +";
	String test7 = "1000 50000 *";
	String test8 = "3 4 2 7 6 4 1 + * + + * /";
	PostfixExpression px;
	@Before
	public void setUp() throws Exception {   
		px = new PostfixExpression(); 
	}

	@Test
	public void testEvaluate1() {
		org.junit.Assert.assertEquals((Object) 11, (Object) px.evaluate(test1));
	} 
	
	@Test
	public void testEvaluate2() {
		org.junit.Assert.assertEquals((Object) 94, (Object) px.evaluate(test2));
	}
	
	@Test
	public void testEvaluate3() {
		org.junit.Assert.assertEquals((Object) (-572), (Object) px.evaluate(test3));
	}
	
	@Test
	public void testEvaluate4() {
		org.junit.Assert.assertEquals((Object) 8, (Object) px.evaluate(test4));
	}
	
	@Test
	public void testEvaluate5() {
		org.junit.Assert.assertEquals((Object) 21, (Object) px.evaluate(test5));
	}
	
	@Test
	public void testEvaluate6() {
		org.junit.Assert.assertEquals((Object) 8, (Object) px.evaluate(test6));
	}
	
	@Test
	public void testEvaluate7() {
		org.junit.Assert.assertEquals((Object) (1000 *50000), (Object) px.evaluate(test7));
	}
	
	@Test
	public void testEvaluate8() {
		org.junit.Assert.assertEquals((Object) 0, (Object) px.evaluate(test8));
	}

}
