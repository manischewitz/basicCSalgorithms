package linkedList;

public class DoublyLinkedList<T> extends GenericLinkedList<T> implements AbstractDoublyLinkedList<T>, Iterable<T> {

	private Link<T> last;
	
	@Override
	public boolean delete(T value) {
		if (isEmpty()) {
			return false;
		}
		Link<T> current = first;
		while(!current.getPayload().equals(value)) {
			current = current.getNext();
			if (current == null) {
				return false;
			}
		}
		if (current == first) {
			first = current.getNext();
		} else {
			current.getPrevious().setNext(current.getNext());
		}
		if (current == last) {
			last = current.getPrevious();
		} else {
			current.getNext().setPrevious(current.getPrevious());
		}
		return true;
	}

	@Override
	public boolean insertAfter(T value, T inserted) {
		Link<T> current = first;
		while (!current.getPayload().equals(value)) {
			current = current.getNext();
			if (current == null) {
				return false;
			}
		}
		Link<T> newElement = new TwoWayLink<>(inserted);
		if (current == last) {
			newElement.setNext(null);
			last = newElement;
		} else {
			newElement.setNext(current.getNext());
			current.getNext().setPrevious(newElement);
		}
		newElement.setPrevious(current);
		current.setNext(newElement);
		return true;
	}

	@Override
	public T deleteLast() {
		if (!isEmpty()) {
			final Link<T> temp = last;
			if (last.getPrevious() != null) {
				last.getPrevious().setNext(null);
				last = last.getPrevious();
			} else {
				first = null;
				last = null;
			}
			return temp.getPayload();
		}
		return null;
	}

	@Override
	public T deleteFirst() {
		if (!isEmpty()) {
			final Link<T> temp = first;
			if (first.getNext() != null) {
				first.getNext().setPrevious(null);
				first = first.getNext();
			} else {
				first = null;
				last = null;
			}
			return temp.getPayload();
		}
		return null;
	}

	@Override
	public void insertLast(T value) {
		if (isEmpty()) {
			insertFirst(value);
		} else {
			Link<T> newElement = new TwoWayLink<>(value);
			last.setNext(newElement);
			newElement.setPrevious(last);
			last = newElement;
		}
	}

	@Override
	public void insertFirst(T value) {
		Link<T> newElement = new TwoWayLink<>(value);
		if (isEmpty()) {
			last = newElement;
		} else {
			first.setPrevious(newElement);
		}
		newElement.setNext(first);
		first = newElement;
	}

	@Override
	public Iterator<T> createIterator() {
		return new DoublyLinkedListIterator<T>(this, first);
	}

	@Override
	public T getLast() {
		return (last != null) ? last.getPayload() : null;
	}

}
