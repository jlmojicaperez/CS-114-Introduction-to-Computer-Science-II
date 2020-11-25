import java.util.Arrays;
import java.util.ArrayList;
/**
 * Merge Sort with k-way decomposition
 * i.e. subdividing the array in k subarrays
 * instead of two
 * 
 * @author Jose Mojica Perez
 * @version 1.0
 * @since 2020-11-11
 */
public class MergeSort{
	public static void main(String[] args){
		int size = 100;
		int k = 4;
		int[] someArray = new int[size];
		for(int i = 0; i < someArray.length;i++){
			someArray[i] = (int)(Math.random()*100 + 1);
		}
		String unsorted = Arrays.toString(someArray);
		int[] temp = new int[someArray.length];
		mergesort(someArray,temp,0, someArray.length-1, k);
		System.out.println("UNSORTED : " + unsorted);
		System.out.println("SORTED   : " + Arrays.toString(someArray));
	}
	static void mergesort(int[] array, int[] temp, int l, int r, int k){
		if((r-l) <= k){
			return;
		}
		if((r-l) > k && r != l+(l+r)/k){
			mergesort(array, temp, l, (l+r)/k, k);
			mergesort(array, temp, r, array.length-1, k);
		}
		for(int i = l; i < k; i += (l+r)/k){
			if(i+2*(l+r)/k <= (l+r)-1){
				mergesort(array, temp, i, i+(l+r)/k-1);
				mergesort(array, temp, i+(l+r)/k, i+2*(l+r)/k);
			}
			else{
				mergesort(array, temp, i, i+(l+r)/k-1);
				mergesort(array, temp, i+(l+r)/k, (l+r)-1);
			}
		}
	}
	static void mergesort(int[] A, int[] temp, int l, int r){
		int mid = (l + r) / 2; // Select midpoint
		if (l == r)
			return; // List has one element
		mergesort(A, temp, l, mid); // Mergesort first half
		mergesort(A, temp, mid + 1, r); // Mergesort second half
		for (int i = l; i <= r; i++){
			// Copy subarray to temp
			temp[i] = A[i];
		}
			// Do the merge operation back to A
		int i1 = l;
		int i2 = mid + 1;
		for (int curr = l; curr <= r; curr++) {
			if (i1 == mid + 1) // Left sublist exhausted
				A[curr] = temp[i2++];
			else if (i2 > r) // Right sublist exhausted
				A[curr] = temp[i1++];
			else if (temp[i1] < (temp[i2])) // Get smaller
				A[curr] = temp[i1++];
			else
				A[curr] = temp[i2++];	
		}
	}
}