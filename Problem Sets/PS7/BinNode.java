/** ADT for binary tree nodes */
public interface BinNode<E> {
	/** Return and set the element value */
	public E element();

	public E setElement(E v);

	/** Return the left child */
	public BinNode<E> left();

	/** Return the right child */
	public BinNode<E> right();

	/** Return true if this is a leaf node */
	public boolean isLeaf();
}