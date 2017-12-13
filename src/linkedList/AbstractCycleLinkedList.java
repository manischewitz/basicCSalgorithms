package linkedList;

public interface AbstractCycleLinkedList<T> {

	public void insert(T value);
	
	public boolean delete(T value);
	
	public boolean has(T value);
	
	public void step();
	
	public void display();
	
	public int size();
	
	public boolean isEmpty();
	
	public T deleteAfterCurrent();
	
	public T getCurrent();
	
	public T getNext();
	
}
