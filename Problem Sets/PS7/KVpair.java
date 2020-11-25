
/** Container class for a key-value pair */
class KVpair<Key, E> {
	private Key k;
	private E e;

	/** Constructors */
	KVpair() {
		k = null;
		e = null;
	}

	KVpair(Key kval, E eval) {
		k = kval;
		e = eval;
	}

	/** Data member access functions */
	public Key key() {
		return k;
	}

	public E value() {
		return e;
	}
}