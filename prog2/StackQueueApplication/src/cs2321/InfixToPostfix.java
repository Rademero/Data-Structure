package cs2321;
/**
 * Michael Romero
 *Assignment 2
 * This class is used to make an array act like a array list
 */

public class InfixToPostfix {
	/* Convert an infix expression and to a postfix expression
	 * infix expression : operator is between operands. Ex. 3 + 5
	 * postfix Expression: operator is after the operands. Ex. 3 5 +
	 * 
	 * The infixExp expression includes the following
	 *      operands:  integer or decimal numbers 
	 *      and operators: + , - , * , /
	 *      and parenthesis: ( , )
	 *      
	 *      For easy parsing the expression, there is a space between operands and operators, parenthesis. 
	 *  	Ex: "1 * ( 3 + 5 )"
	 *      Notice there is no space before the first operand and after the last operand/parentheses. 
	 *  
	 * The postExp includes the following 
	 *      operands:  integer or decimal numbers 
	 *      and operators: + , - , * , /
	 *      
	 *      For easy parsing the expression, there should have a space between operands and operators.
	 *      Ex: "1 3 5 + *"
	 *      Notice there is space before the first operand and last operator. 
	 *      Notice that postExp does not have parenthesis. 
	 */
	public static String convert(String infixExp) {
		//TODO : complete this function with the help of Stack
		//Hint: you can use the string.split(" ") to return an array of tokens in infixExp. 
		DLLStack st = new DLLStack();
		int i =0;
		String result = "";
		String[] data = infixExp.split(" "); 
		while (i <= (data.length-1)) {
			//checks for precedence for multiplication and devision
			if(data[i].equals("*") |data[i].equals("/")) {
					while((!st.isEmpty()&&st.top().equals("*")| st.top().equals("/")))  { 
							result += st.pop()+ " "; 
					}    
				st.push(data[i]);  
			}else if(data[i].equals("+")| data[i].equals("-")) {
				//checks for precedence for addition and subtraction
					while (!st.isEmpty() && !st.top().equals("(") && 
							(st.top().equals("*")|| st.top().equals("/") 
									||st.top().equals("-") ||st.top().equals("+"))) {	
						result += st.pop()+ " "; 
					}  
				st.push(data[i]);    
			}   
			else if (data[i].equals("(") ) {
				st.push(data[i]); 
			}
			//pops until ( is found
			else if (data[i].equals(")")) { 
				Boolean t = false; 
				while(t == false & !st.isEmpty()) { 
					String temp = (String) st.pop();
					if (temp.equals("(")) {
						t = true;
					}else {
					result += temp + " ";
					} 
				}
			} 
			//Default num push
			else { 
				result += data[i] + " "; 
			} 
			i++;
		}
		while(!st.isEmpty()) {// makes sure the list is empty
			result += st.pop();
			if (st.isEmpty()) {
				
			}else {
			result +=" ";
			}
		}
		return result;
	}	
} 
 