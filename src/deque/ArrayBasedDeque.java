package deque;

public class ArrayBasedDeque<T> implements AbstractDeque<T> {

	private Object[] items;
	private int numberOfItems;
	private int left;
	private int right;
	private final static int INITIAL_CAPACITY = 24;
	
	public ArrayBasedDeque(int initialSize) {
		if (initialSize < 1) {
			throw new IndexOutOfBoundsException("Stack size can not be less than 1.");
		}
		items = new Object[initialSize];
		numberOfItems = 0;
		left = initialSize / 2;
		right = left;
	}
	
	public ArrayBasedDeque() {
		this(INITIAL_CAPACITY);
	}
	
	@Override
	public void insertLeft(T value) {
		ensureCapacity();
		left++;
		items[left] = value;
		numberOfItems++;
	}

	@Override
	public void insertRight(T value) {
		ensureCapacity();
		items[right] = value;
		right--;
		numberOfItems++;
	}

	@Override
	public T removeLeft() {
		T temp = peekLeft();
		items[left] = null;
		left--;
		numberOfItems--;
		return temp;
	}

	@Override
	public T removeRight() {
		T temp = peekRight();
		items[right] = null;
		right++;
		numberOfItems--;
		return temp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T peekLeft() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("Deque is empty!");
		}
		return (T) items[left];
	}

	@SuppressWarnings("unchecked")
	@Override
	public T peekRight() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("Deque is empty!");
		}
		return (T) items[right + 1];
	}

	@Override
	public boolean isEmpty() {
		return (numberOfItems == 0);
	}

	@Override
	public int size() {
		return numberOfItems;
	}
	
	private void ensureCapacity() {
		final int length = items.length;
		if (left == length - 1) {
			Object[] newArray = new Object[length + INITIAL_CAPACITY];
			for (int i = 0; i < length; i++) {
				newArray[i] = items[i];
			}
			items = newArray;
		} else if (right < 0) {
			Object[] newArray = new Object[length + INITIAL_CAPACITY];
			for (int i = 0; i < length; i++) {
				newArray[i + INITIAL_CAPACITY] = items[i];
			}
			right = INITIAL_CAPACITY - 1;
			left += INITIAL_CAPACITY;
			items = newArray;
		}
	}

	@Override
	public void display() {
		final StringBuilder sb = new StringBuilder(this.getClass().getName());
		sb.append("\n");
		for (int i = right + 1; i <= left; i++) {
			sb.append(items[i]);
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}
