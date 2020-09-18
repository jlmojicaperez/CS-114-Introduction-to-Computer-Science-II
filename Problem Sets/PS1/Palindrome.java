import java.util.Scanner;
/**
 * The Palindrome program implements a recursive algorithm
 * that reverses a string and uses it to check if it is
 * a palindrome (not case sensitive)
 *
 * A string is a palindrome if the reversed string reads the same
 * as the original string
 *
 * @author Jose Mojica Perez
 * @version 1.0
 * @since 2020-09-07
 */
public class Palindrome{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine();
		System.out.println(IsPalindrome(str));
	}

	public static String ReverseString(String str){
		if(str.length() == 1){
			return str;
		}
		else{
			return ReverseString(str.substring(1)) + str.substring(0,1);
		}
	}

	public static boolean IsPalindrome(String str){
		String reversedStr = ReverseString(str);
		if(str.equalsIgnoreCase(reversedStr)){
			return true;
		}
		else{
			return false;
		}

	}
}
