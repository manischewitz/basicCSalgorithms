package deque;

public interface AbstractDeque<T> {

	public void insertLeft(T value);
	
	public void insertRight(T value);
	
	public T removeLeft();
	
	public T removeRight();
	
	public T peekLeft();
	
	public T peekRight();
	
	public boolean isEmpty();
	
	public int size();
	
	public void display();
}
