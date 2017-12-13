package linkedList;

public interface AbstractFirstLastLinkedList<T> extends AbstractLinkedList<T> {

	public void insertLast(T value);
	
	public T getLast();
	
}
