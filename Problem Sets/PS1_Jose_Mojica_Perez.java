public class PS1{
	public static void main(String[] args){
		
		long startTime = System.currentTimeMillis();
		int a = 10, b = 1;
		int product = Multiply(a,b);
		System.out.println(product);
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println(TotalTime + " ms");
	
	}
	private static int Multiply(int x, int y){
		if(y == 1){
			return x;
		}
		else{
			return x + Multiply(x, y-1);
		}

	} 
}
