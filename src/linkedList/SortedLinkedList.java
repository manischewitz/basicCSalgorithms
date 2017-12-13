package linkedList;

public class SortedLinkedList<T extends objects.Comparable> extends GenericLinkedList<T> implements AbstractSortedLinkedList<T> {

	@Override
	public void insert(T value) {
		Link<T> newElement = new ForwardLink<>(value);
		Link<T> before = null;
		Link<T> current = first;
		while (current != null && value.greaterThan(current.getPayload())) {
			before = current;
			current = current.getNext();
		}
		if (before != null) {
			before.setNext(newElement);
		} else {
			first = newElement;
		}
		newElement.setNext(current);
	}
	

	
}
