package linkedList;

public class FirstLastLinkedList<T> extends GenericLinkedList<T> implements AbstractFirstLastLinkedList<T> {

	private Link<T> last;
	
	@Override
	public void insertFirst(T value) {
		Link<T> newElement = new ForwardLink<>(value);
		if (isEmpty()) {
			first = newElement;
			last = newElement;
		} else {
			newElement.setNext(first);
			first = newElement;
		}
	}
	
	@Override
	public T deleteFirst() {
		if (!isEmpty()) {
			Link<T> temp = first;
			if (first.getNext() == null) {
				last = null;
			}
			first = first.getNext();
			return temp.getPayload();
		}
		return null;
	}
	
	@Override
	public void insertLast(T value) {
		Link<T> newElement = new ForwardLink<>(value);
		if (isEmpty()) {
			first = newElement;
			last = newElement;
		} else {
			last.setNext(newElement);
			last = newElement;
		}
	}

	@Override
	public T getLast() {
		return (last != null) ? last.getPayload() : null;
	}
	
	public boolean delete(T value) {
		if (isEmpty()) {
			return false;
		}
		Link<T> current = first;
		Link<T> before = first;
		while(!current.getPayload().equals(value)) {
			final Link<T> next = current.getNext();
			if (next == null) {
				return false;
			} else {
				before = current;
				current = next;
			}
		}
		if (current == first) {
			first = current.getNext();
		} else {
			before.setNext(current.getNext());
		}
		if (current == last) {
			last = before;
		}
		return true;
	}
	
}
