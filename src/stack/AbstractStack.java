package stack;

public interface AbstractStack<T> {

	public void push(T value);
	
	public T pop();
	
	public T peek();
	
	public boolean isEmpty();
	
	public void display();
}
