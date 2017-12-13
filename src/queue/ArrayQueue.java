package queue;

public class ArrayQueue<T> implements AbstractQueue<T> {

	private static final int INITIAL_SIZE = 12;
	private Object[] items;
	private int front;
	private int rear;
	private int currentNItems;
	
	public ArrayQueue (int initialSize) {
		if (initialSize < 1) {
			throw new IndexOutOfBoundsException("Queue size can not be less than 1.");
		}
		items = new Object[initialSize];
		front = 0;
		rear = -1;
		currentNItems = 0;
	}
	
	public ArrayQueue () {
		this(INITIAL_SIZE);
	}
	
	@Override
	public void insert(T value) {
		ensureCapacity();
		rear++;
		items[rear] = value;
		currentNItems++;
	}

	@Override
	public T remove() {
		final T temp = peekFront();
		items[front] = null;
		front++;
		currentNItems--;
		return temp;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T peekFront() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("Queue is empty!");
		}
		return (T) items[front];
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
		if (rear == length - 1) {
			Object[] newArray = new Object[length + INITIAL_SIZE];
			int notNull = -1;
		
			for (int i = 0; i < length; i++) {
				final Object item = items[i];
				if (item != null) {
					notNull++;
					newArray[notNull] = item;
				}
			}
			front = 0;
			rear = notNull;
			items = newArray;
		}
	}

	@Override
	public void display() {
		final StringBuilder sb = new StringBuilder(this.getClass().getName());
		sb.append("\n");
		for (int i = front; i <= rear; i++) {
			sb.append(items[i]);
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
}
