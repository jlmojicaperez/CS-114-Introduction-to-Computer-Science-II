import java.util.ArrayList;
import java.util.Arrays;

/** Max-heap implementation */
public class MaxHeap<E extends Comparable<E>> {
	private ArrayList<E> Heap;
	private int n; // Number of things in heap

	/** Constructor for initially empty heap */
	public MaxHeap() {
		Heap = new ArrayList<E>();
		n = 0;
	}

	public MaxHeap(E[] h) {
		Heap = new ArrayList<E>(Arrays.asList(h));
		n = h.length;
		buildheap();
	}

	/** @return Current size of the heap */
	public int heapsize() {
		return n;
	}

	public boolean isEmpty() {
		return n == 0;
	}

	/** @return True if pos a leaf position, false otherwise */
	public boolean isLeaf(int pos) {
		return (pos >= n / 2) && (pos < n);
	}

	/** @return Position for left child of pos */
	public int leftchild(int pos) {
		assert 2 * pos + 1 < n : "Position has no left child";
		return 2 * pos + 1;
	}

	/** @return Position for right child of pos */
	public int rightchild(int pos) {
		assert 2 * pos + 2 < n : "Position has no right child";
		return 2 * pos + 2;
	}

	/** @return Position for parent */
	public int parent(int pos) {
		assert pos > 0 : "Position has no parent";
		return (pos - 1) / 2;
	}

	/** Insert val into heap */
	public void insert(E val) {
		Heap.add(val); // Start at end of heap
		int curr = n;
		n++;
		// Now sift up until curr parents key > currs key
		while ((curr != 0)
				&& (Heap.get(curr).compareTo(Heap.get(parent(curr))) > 0)) {
			swap(curr, parent(curr));
			curr = parent(curr);
		}
	}

	/** Heapify contents of Heap */
	public void buildheap() {
		for (int i = n / 2 - 1; i >= 0; i--)
			siftdown(i);
	}

	/** Put element in its correct place */
	private void siftdown(int pos) {
		assert (pos >= 0) && (pos < n) : "Illegal heap position";
		while (!isLeaf(pos)) {
			int j = leftchild(pos);
			if ((j < (n - 1)) && (Heap.get(j).compareTo(Heap.get(j + 1)) < 0))
				j++; // j is now index of child with greater value
			if (Heap.get(pos).compareTo(Heap.get(j)) >= 0)
				return;
			swap(pos, j);
			pos = j; // Move down
		}
	}

	/** Remove and return maximum value */
	public E removemax() {
		assert n > 0 : "Removing from empty heap";
		swap(0, --n); // Swap maximum with last value
		if (n != 0) // Not on last element
			siftdown(0); // Put new heap root val in correct place
        E val = Heap.get(n);
        Heap.remove(n);
		return val;
	}

	private void swap(int i, int j) {
		E temp = Heap.get(j);
		Heap.set(j, Heap.get(i));
		Heap.set(i, temp);
	}

}
