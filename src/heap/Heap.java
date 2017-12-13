package heap;

public interface Heap <T> {

	public void insert(T value);
	
	public T deleteMax();
	
	public T getMax();
	
	public void offer(T value);
	
	public void restoreHeap();
	
	public int size();
}
