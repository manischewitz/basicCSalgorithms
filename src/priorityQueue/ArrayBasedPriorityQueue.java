package priorityQueue;
import arrays.Array;
import objects.Comparable;

public class ArrayBasedPriorityQueue implements AbstractPriorityQueue {

	private static final int INITIAL_SIZE = 12;
	private Comparable[] items;
	private int currentNItems;
	
	public ArrayBasedPriorityQueue (int initialSize) {
		if (initialSize < 1) {
			throw new IndexOutOfBoundsException("Queue size can not be less than 1.");
		}
		items = new Comparable[initialSize];
		currentNItems = 0;
	}
	
	public ArrayBasedPriorityQueue () {
		this(INITIAL_SIZE);
	}

	@Override
	public void insert(Comparable value) {
		ensureCapacity();
		int i;
		
		if (isEmpty()) {
			items[currentNItems] = value;
		} else {
			for(i = currentNItems - 1; i >= 0; i--) {
				if (value.greaterThan(items[i])) {
					items[i + 1] = items[i];
				} else {
					break;
				}
			}
			items[i + 1] = value;
		}
		currentNItems++;
	}

	@Override
	public Comparable remove() {
		Comparable temp = peekMin();
		currentNItems--;
		return temp;
	}

	@Override
	public Comparable peekMin() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("Queue is empty!");
		}
		return items[currentNItems - 1];
	}

	@Override
	public boolean isEmpty() {
		return (currentNItems == 0);
	}

	@Override
	public int size() {
		return currentNItems;
	}
	
	private void ensureCapacity() {
		final int length = items.length;
		if (currentNItems == length) {
			Comparable[] newArray = new Comparable[length + INITIAL_SIZE];
			
			for (int i = 0; i < length; i++) {
				newArray[i] = items[i];
			}
			items = newArray;
		}
	}

	@Override
	public void display() {
		final StringBuilder sb = new StringBuilder(this.getClass().getName());
		sb.append("\n");
		for (int i = 0; i < currentNItems; i++) {
			sb.append(items[i]);
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
}
