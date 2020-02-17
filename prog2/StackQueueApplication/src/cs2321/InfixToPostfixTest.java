package cs2321;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class InfixToPostfixTest {
	String test1 ="3 + 5 * 2"; 
	String test2 ="1 * ( 3 + 5 )"; 
	String test3 = "5 + 3 - 6"; 
	String test4 ="10 - 2 * ( 3 + 4 * ( 5 + 6 / 2 ) * 9 )";  
	String test5 = "9 + ( 3 - 1 ) * -3 + 10 / 2"; 
	String test6 = "9 * 9 + 1 - 4 / 8 * -1";
	String test7 = "9 + 4 - 4 + 9"; 
	String test8 ="10 - 2 * 3 + 4"; 
	InfixToPostfix px;  
	@Before 
	public void setUp() throws Exception {
		px = new InfixToPostfix();    
	}

	@Test
	public void testConvert1() {
		org.junit.Assert.assertEquals((Object) "3 5 2 * +", (Object) px.convert(test1));
	}
	
	@Test 
	public void testConvert2() {
		org.junit.Assert.assertEquals((Object) "1 3 5 + *", (Object) px.convert(test2));
	}

	@Test
	public void testConvert3() {
		org.junit.Assert.assertEquals((Object) "5 3 + 6 -", (Object) px.convert(test3)); 
	} 
	 
	@Test
	public void testConvert4() {
		org.junit.Assert.assertEquals((Object) "10 2 3 4 5 6 2 / + * 9 * + * -", (Object) px.convert(test4));
	}
	
	@Test
	public void testConvert5() { 
		org.junit.Assert.assertEquals((Object) "9 3 1 - -3 * + 10 2 / +", (Object) px.convert(test5));
	}
	 
	@Test
	public void testConvert6() { 
		org.junit.Assert.assertEquals((Object) "9 9 * 1 + 4 8 / -1 * -", (Object) px.convert(test6));
	}
	  
	@Test
	public void testConvert7() {
		org.junit.Assert.assertEquals((Object) "9 4 + 4 - 9 +", (Object) px.convert(test7));
	}
	
	@Test
	public void testConvert8() {
		org.junit.Assert.assertEquals((Object) "10 2 3 * - 4 +", (Object) px.convert(test8));
	}
}
