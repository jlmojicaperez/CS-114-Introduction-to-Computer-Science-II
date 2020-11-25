import java.util.Scanner;
import java.util.Stack;
/**
 * The recursive program SumsTo determines if +’s can be inserted
 * into a string of digits so that the resulting arithmetic 
 * expression sums to a given number 
 * 
 * The input is a line containing two strings of digits, 
 * each of length at most 18 
 * 
 * The output is a single line containing either a character 
 * string giving an arithmetic expression that evaluates 
 * to the second number, or false if there is no such expression
 *
 * Examples
 *
 * 	input 	1234 37
 * 	output	1+2+34
 *
 * 	input	1234 500
 * 	output 	false
 *
 *
 * @Author Jose Mojica Perez
 * @version 3.0
 * @since 2020-10-01
 **/
public class SumsTo{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
	        String digits = scan.next();
		long goal = Long.parseLong(scan.next());

	        String expression = "";
	        int index = 0;
	        long sum = 0;

	        sumTo(expression, digits, goal, index, sum);
	
	        if(output.equals(""))
	            System.out.println("false");
	        else
	            System.out.print(output.substring(1));
	}

	static String output = "";
	public static void sumTo(String expression, String digits, long goal, int index, long sum){
		//System.out.println(expression + "\t" + sum + "\t" + output);
		if(index > digits.length() - 1 && sum == goal){
	        	output = expression;
	        	return;
		}
		for(int i = index; i < digits.length() && output.equals(""); i++){
			String addExpress = digits.substring(index, i + 1);
			long addValue = Long.parseLong(addExpress);
			sumTo(expression + "+" + addExpress , digits, goal, i + 1, sum + addValue);
		}
		return;
	}
}	
