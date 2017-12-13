package linkedList;

public interface AbstractMatrixLinkedList<T> {

	public void insert(T value, final int k, final int j);
	
	public T delete(final int k, final int j);
	
	public void delete(T value);
	
	public void display();
	
	public boolean isEmpty();
}
