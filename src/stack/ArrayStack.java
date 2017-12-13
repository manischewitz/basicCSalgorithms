package stack;

import arrays.Array;

public class ArrayStack<T> implements AbstractStack<T> {

	private static final int INITIAL_SIZE = 12;
	private int top;
	private Object[] items;
	
	public ArrayStack (int initialSize) {
		if (initialSize < 1) {
			throw new IndexOutOfBoundsException("Stack size can not be less than 1.");
		}
		items = new Object[initialSize];
		top = -1;
	}
	
	public ArrayStack () {
		this(INITIAL_SIZE);
	}

	@Override
	public void push(T value) {
		ensureCapacity();
		top++;
		items[top] = value;
	}

	@Override
	public T pop() {
		T e = peek();
		top--;
		return e;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T peek() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("Stack is empty!");
		}
		return (T) items[top];
	}

	@Override
	public boolean isEmpty() {
		return (top == -1);
	}

	private void ensureCapacity() {
		if (top == items.length - 1) {
			items = Array.copy(items, INITIAL_SIZE);
		}
	}

	@Override
	public void display() {
		final StringBuffer sb = new StringBuffer(this.getClass().getName());
		sb.append("\n");
		for (int i = 0; i <= top; i++) {
			sb.append(items[i]);
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}
