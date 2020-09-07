import java.util.Scanner;

/**
 * The RecursiveMultiply program implements a recursive algorithm 
 * that calulates the product of two given integer numbers and prints
 * it to the screen
 *
 * @author Jose Mojica Perez
 * @version 1.0
 * @since 2020-09-06
 */
public class RecursiveMultiply{
	public static void main(String[] args){	
		
		Scanner scan = new Scanner(System.in);
		int a = Integer.parseInt(scan.next());
		int b = Integer.parseInt(scan.next());
		
		long product = mult((long)a, (long)b);
		System.out.println(product);
	}
	
	private static long mult(long x, long y){
		/* mult takes in integers x and y that satisfy the following 
		 * 1 <= x <= y < 10^9
		 * and returns their product
		 * */
		if(x <= 0 || y <= 0){
			return 0;
		}
		else if(x%2 == 1){ //If the rightmost bit of the multiplier is 1
			return y + mult(x>>1, y<<1); //Add current value of y to product
		}
		else{ //If the rightmost bit of the multiplier is 0
			return mult(x>>1, y<<1); //Ignore current value of y
		}
	}
}
