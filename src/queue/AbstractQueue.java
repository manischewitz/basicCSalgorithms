package queue;

public interface AbstractQueue<T> {

	public void insert(T value);
	
	public T remove();
	
	public T peekFront();
	
	public boolean isEmpty();
	
	public int size();
	
	public void display();
}
