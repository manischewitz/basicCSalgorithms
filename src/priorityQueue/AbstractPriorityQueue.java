package priorityQueue;
import objects.Comparable;

public interface AbstractPriorityQueue {

	public void insert(Comparable value);
	
	public Comparable remove();
	
	public Comparable peekMin();
	
	public boolean isEmpty();
	
	public int size();
	
	public void display();
}
