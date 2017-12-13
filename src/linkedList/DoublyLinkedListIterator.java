package linkedList;

public class DoublyLinkedListIterator<T> implements Iterator<T> {

	private Link<T> current;
	private Link<T> previous;
	private final AbstractDoublyLinkedList<T> list;
	
	public DoublyLinkedListIterator(AbstractDoublyLinkedList<T> list, Link<T> first) {
		this.list = list;
		current = first;
		previous = null;
	}
	
	@Override
	public boolean hasNext() {
		return (current != null);
	}

	@Override
	public T remove() {
		return (list.delete(previous.getPayload())) ? previous.getPayload() : null;
	}

	@Override
	public T next() {
		T temp = current.getPayload();
		previous = current;
		current = current.getNext();
		return temp;
	}

}
