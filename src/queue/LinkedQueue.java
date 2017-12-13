package queue;

import linkedList.AbstractFirstLastLinkedList;
import linkedList.FirstLastLinkedList;

public class LinkedQueue<T> implements AbstractQueue<T> {

	private final AbstractFirstLastLinkedList<T> list;
	
	public LinkedQueue() {
		list = new FirstLastLinkedList<>();
	}
	
	@Override
	public void insert(T value) {
		list.insertLast(value);
	}

	@Override
	public T remove() {
		return list.deleteFirst();
	}

	@Override
	public T peekFront() {
		return list.getFirst();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public void display() {
		System.out.println(this.getClass().getName() + " -> ");
		list.display();
	}

}
