class BSTNode<K, E> implements BinNode<E> {
	private K key;
	private E element;
	private BSTNode<K, E> left;
	private BSTNode<K, E> right;

	public BSTNode() {
		left = right = null;
	}

	public BSTNode(K k, E val) {
		left = right = null;
		key = k;
		element = val;
	}

	public BSTNode(K k, E val, BSTNode<K, E> l, BSTNode<K, E> r) {
		left = l;
		right = r;
		key = k;
		element = val;
	}

	public K key() {
		return key;
	}

	public K setKey(K k) {
		return key = k;
	}

	public E element() {
		return element;
	}

	public E setElement(E v) {
		return element = v;
	}

	public BSTNode<K, E> left() {
		return left;
	}

	public BSTNode<K, E> setLeft(BSTNode<K, E> p) {
		return left = p;
	}

	public BSTNode<K, E> right() {
		return right;
	}

	public BSTNode<K, E> setRight(BSTNode<K, E> p) {
		return right = p;
	}

	public boolean isLeaf() {
		return (left == null) && (right == null);
	}
}