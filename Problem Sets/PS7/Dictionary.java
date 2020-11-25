
public interface Dictionary<Key, E> {
	public void clear();

	public void insert(Key k, E e);

	public E remove(Key k); // Null if none

	public E removeAny(); // Null if none

	public E find(Key k); // Null if none
	
	public Iterable<E> findAll(Key k);
	
	public Iterable<E> values();

	public int size();
};