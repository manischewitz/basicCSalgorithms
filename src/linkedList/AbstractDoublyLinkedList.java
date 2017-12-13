package linkedList;

public interface AbstractDoublyLinkedList<T> extends Iterable<T> {

	public boolean delete(T value);
	
	public void display();
	
	public boolean insertAfter(T value, T inserted);
	
	public T deleteLast();
	
	public T deleteFirst(); 
	
	public void insertLast(T valeu);
	
	public void insertFirst(T value);
	
	public boolean isEmpty();
	
	public boolean has(T value);
	
	public int size();
	
	public T getLast();
	
	public T getFirst();
}
