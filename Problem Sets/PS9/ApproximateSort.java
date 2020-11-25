import java.util.Arrays;
/**
 * This is an algorithm based on quicksort that approximately sorts an array. 
 * The difference with quicksort is that partitions of length  k aren't sent 
 * to be recursively sorted. Still, the elements of any partition are ensured 
 * to be at most k positions from its sorted position. This is because any 
 * partition only contains elements that are either greater than or less than 
 * the pivot. Thus, a partition of length k contains either only k elements 
 * that are smaller than the pivot or only k elements that are greater than 
 * the pivot
 * 
 * This algorithm recursively divides any given partition in two subpartitions 
 * unless the partition is of length   k, in which case it leaves the partition 
 * as it is.
 * 
 * In the average case this algorithm will do at most log(n/k) partitions, 
 * and every pair of subpartitions takes n comparisons to process, giving us a 
 * time complexity of Θ(nlog(n/k)) 
 * 
 * @author Jose Mojica Perez
 * @version 1.0
 * @since 2020-11-04
 */
public class ApproximateSort{
	public static void main(String[] args){
		int size = 100;
		int k = 4;
		int[] sampleArray = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] someArray = new int[size];
		for(int i = 0; i < someArray.length; i++){
			someArray[i] = (int) (Math.random()*1000 + 1);
		}
		System.out.println("TEST   " + Arrays.toString(someArray));
		System.out.println("SAMPLE " + Arrays.toString(sampleArray));
		ksort(someArray, k);
		ksort(sampleArray, k);
		System.out.println("Approx:\tTEST   " + Arrays.toString(someArray));
		Arrays.sort(someArray);
		System.out.println("Sorted:\tTEST   " + Arrays.toString(someArray));
		System.out.println("Approx:\tSAMPLE " + Arrays.toString(sampleArray));
		Arrays.sort(sampleArray);
		System.out.println("Sorted:\tSAMPLE " + Arrays.toString(sampleArray));
	}
	public static void ksort(int[] array, int k){
		//Initial call to the recursive function should use:
		//left = index of beginning of the array
		//right = index of end of the array
		ksort(array, 0, array.length - 1, k);
	}
	public static void ksort(int[] array, int left, int right, int k){
		//This conditional ensures that partitions
		//of length <= k are not completely sorted
		if(Math.abs(right - left)  <= k){
			return;
		}
		int pivot = array[(left + right) / 2];
		int partitionIndex = partition(array, left, right, pivot, k);
		ksort(array, left, partitionIndex - 1, k);
		ksort(array, partitionIndex, right, k);
	}
	public static int partition(int[] array, int left, int right, int pivot, int k){
		while(left <=  right){
			while(array[left] < pivot)
				left++;
			while(array[right] > pivot)
				right--;

			if(left <= right){
				swap(array, left, right);
				System.out.println("SWAPPED : " + left + "<--->" + right);
				left++;
				right--;
			}
		}
		return left;
	}
	private static void swap(int[] array, int i, int j) {
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}
}