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
		long desired = Long.parseLong(scan.next());
		Stack<String> answer = new Stack<String>();
		
		leftDecomp("", digits, desired, answer);
		if(answer.peek().equals("left false")){
			rightDecomp(digits, "", desired, answer);
		}

		String output = answer.pop();
		if(output.equals("right false")){
			System.out.println("false");
		}
		else if(output.equals("rightDecomp")){
			String out = "";
			while(!answer.peek().equals("left false")){
				out = "+" + answer.pop() + out;
			}
			System.out.print(out.substring(1, out.length()));
		}
		else if(output.equals("leftDecomp")){
			System.out.print(answer.pop());
			while(!answer.isEmpty()){
				System.out.print("+" + answer.pop());
			}
		}
		else{
			System.out.print("false?");
		}
	}
	public static void leftDecomp(String left, String right, long desired, Stack<String> answer){
		if(right.length() != 0 && Long.parseLong(right) == desired && left.equals("")){
			answer.push(right);
			answer.push("leftDecomp");
			return;
		}
		else if(right.length() != 0){
			long rightNum = Long.parseLong(right);
			if(rightNum < desired){
				answer.push(right);
				desired -= rightNum;
				leftDecomp("", left, desired, answer);
			}
			else{
				left += right.substring(0,1);
				right = right.substring(1,right.length());
				leftDecomp(left,right,desired,answer);
			}
		}
		else{
			answer.push("left false");
			return;
		}
		return;
	}

	public static void rightDecomp(String left, String right, long desired, Stack<String> answer){
		if(left.length() != 0 && Long.parseLong(left) == desired && right.equals("")){
			answer.push(left);
			answer.push("rightDecomp");
			return;
		}
		else if(left.length() != 0){
			long leftNum = Long.parseLong(left);
			if(leftNum < desired){
				answer.push(left);
				desired -= leftNum;
				rightDecomp(right, "", desired, answer);
			}
			else{
				right = left.substring(left.length()-1, left.length()) + right;
				left = left.substring(0,left.length()-1);
				rightDecomp(left,right,desired,answer);
			}
		}
		else{
			answer.push("right false");
			return;
		}
		return;
	}
}
