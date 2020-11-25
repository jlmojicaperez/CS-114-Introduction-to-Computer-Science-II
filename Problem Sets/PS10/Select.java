import java.util.Random;

public class Select {

  static Random rm;

  public static void main(String[] args) {
    rm = new Random();
    int[] A = { 7, 2, 6, 6, 4, 3, 5, 0, 11, 6 };

    for (int j = 0; j < A.length; ++j)
      System.out.print(A[j]+" ");
    System.out.println();
    int q = select(A,0, A.length-1, 5);
    System.out.println("5th smallest is "+q);
  }

  static int select(int[] A, int i, int j, int q) { // select qth smallest
    if (i == j)
      return A[i];
    int pivotindex = i + rm.nextInt(j - i + 1); // Pick a random pivot
    swap(A, pivotindex, j); // Stick pivot at end

    // k will be the first position in the right subarray
    int k = partition(A, i - 1, j, A[j]);
    swap(A, k, j); // Put pivot in place

    int sz = k - i;
    if (q == sz + 1)
      return A[k];
    else if (q <= sz)
      return select(A, i, k - 1, q);
    else 
      return select(A, k+1, j, q - sz-1);
  }

  static int partition(int[] A, int l, int r, int pivot) {
    do { // Move bounds inward until they meet
      while (A[++l] < pivot)
        ;
      while ((r != 0) && (A[--r] >= pivot))
        ;
      swap(A, l, r); // Swap out-of-place values
    } while (l < r); // Stop when they cross
    swap(A, l, r); // Reverse last, wasted swap
    return l; // Return first position in right partition
  }

  private static void swap(int[] A, int i, int j) {
    int temp = A[j];
    A[j] = A[i];
    A[i] = temp;
  }
}
