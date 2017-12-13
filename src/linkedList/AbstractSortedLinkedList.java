package linkedList;

public interface AbstractSortedLinkedList<T extends objects.Comparable> {

	public boolean isEmpty();
	
	public void insert(T value);
	
	public T deleteFirst();
	
	public void display();
	
	public boolean has(T value);
	
	public boolean delete(T value);
	
	public T getFirst();
	
	public int size();
	
}
