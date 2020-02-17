package cs2321;
/**
 * Michael Romero
 *Assignment 2
 * This class is used to make an array act like a array list
 */
public class PostfixExpression {
	
	/**
	 * Evaluate a postfix expression. 
	 * Postfix expression notation has operands first, following by the operations.
	 * For example:
	 *    13 5 *           is same as 13 * 5 
	 *    4 20 5 + * 6 -   is same as 4 * (20 + 5) - 6  
	 *    
	 * In this homework, expression in the argument only contains
	 *     integer, +, -, *, / and a space between every number and operation. 
	 * You may assume the result will be integer as well. 
	 * 
	 * @param exp The postfix expression
	 * @return the result of the expression
	 */
	public static int evaluate(String exp) {
		DLLStack st = new DLLStack();
		int i =0;
		int result = 0; 
		String[] data = exp.split(" ");//splits the data into an array
		while (i <= (data.length-1)) {
			if(data[i].equals("*")) {
				result = (int) st.pop() * (int) st.pop();
				System.out.println(result); 
				st.push(result);
			}else if(data[i].equals("+")) {
				result = (int) st.pop() + (int) st.pop(); 
				st.push(result); 
			}else if(data[i].equals("/")) {
				int temp = (int) st.pop();
				result =(int) st.pop()/ temp; 
				st.push(result);
			}else if(data[i].equals("-")) {
				int temp = (int) st.pop();
				result =(int) st.pop()- temp; 
				st.push(result);
			}else {
				st.push(Integer.parseInt(data[i]));//Default number push
			}
			i++;
		}
		return (int) st.pop(); 
	}
				
	
}
