package queue;

public class LoopArrayQueue<T> implements LoopQueue<T> {

	private final Object[] items;
	private int front;
	private final int maxSize;
	private int rear;
	private int currentNItems;
	
	public LoopArrayQueue(int maxSize) {
		if (maxSize < 1) {
			throw new IndexOutOfBoundsException("Queue size can not be less than 1.");
		}
		this.maxSize = maxSize;
		items = new Object[maxSize];
		front = 0;
		rear = -1;
		currentNItems = 0;
	}

	@Override
	public void insert(T value) {
		if (isFull()) {
			throw new IndexOutOfBoundsException("Queue is already full!");
		} 
		if (rear == maxSize - 1) {
			rear = -1;
		}
		rear++;
		items[rear] = value;
		currentNItems++;
	}

	@Override
	public T remove() {
		T temp = peekFront();
		if (front == maxSize - 1) {
			front = 0;
		}
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

	@Override
	public boolean isFull() {
		return (currentNItems == maxSize);
	}

	@Override
	public void display() {
		final StringBuilder sb = new StringBuilder(this.getClass().getName());
		sb.append("\n");
		for (int i = front; i < maxSize; i++) {
			sb.append(items[i]);
			sb.append("\n");
			if (i == maxSize - 1 && rear < front) {
				for (int j = 0; j <= rear; j++) {
					sb.append(items[j]);
					sb.append("\n");
				}
			}
		}
		System.out.println(sb.toString());
	}
}
