import java.util.ArrayList;
import java.util.Collections;

class UALDictionary<Key extends Comparable, E> implements Dictionary<Key, E> {
	private static final int defaultSize = 10;
	private ArrayList<KVpair<Key, E>> list;

	// Constructors
	UALDictionary() {
		this(defaultSize);
	}

	UALDictionary(int sz) {
		list = new ArrayList<KVpair<Key, E>>(sz);
	}

	public void clear() {
		list.clear();
	}

	/** Insert an element: append to list */
	public void insert(Key k, E e) {
		KVpair<Key, E> temp = new KVpair<Key, E>(k, e);
		list.add(temp);
	}

	/** Remove element with key k, return element */
	public E remove(Key k) {
		E temp = find(k);
		if (temp != null)
			list.remove(new KVpair<Key, E>(k, temp));
		return temp;
	}
	
	/** Remove any element */
    public E removeAny() {
        return list.remove(list.size()-1).value();
    }
	
	/**
	 * Find k using sequential search
	 * 
	 * @return Record with key value k
	 */
	public E find(Key k) {
		for (KVpair<Key, E> t : list)
			if (k.compareTo(t.key())==0)
				return t.value();
		return null; // "k" does not appear in dictionary
	}
	
  public Iterable<E> findAll(Key k) {
    ArrayList<E> al = new ArrayList<E>();
    for (KVpair<Key, E> t : list)
      if (k.compareTo(t.key()) == 0)
        al.add(t.value());
    return al;
  }
	
	public int size(){
		return list.size();
	}
	
	/** Returns an iterable collection of the dictionary. */
	public Iterable<E> values() {
		ArrayList<E> elements = new ArrayList<E>(list.size());
		for(KVpair<Key, E> t : list)
			elements.add(t.value());
		return elements;
	}
}
