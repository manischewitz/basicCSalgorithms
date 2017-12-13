package linkedList;

public interface AbstractLinkedList<T> {

	public boolean isEmpty();
	
	public void insertFirst(T value);
	
	public T deleteFirst();
	
	public void display();
	
	public boolean has(T value);
	
	public boolean delete(T value);
	
	public T getFirst();
	
	public int size();
	
}
