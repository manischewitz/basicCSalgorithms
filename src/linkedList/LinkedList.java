package linkedList;

public class LinkedList<T> extends GenericLinkedList<T> implements AbstractLinkedList<T> {

	public void insertFirst(T value) {
		Link<T> newLink = new ForwardLink<>(value);
		newLink.setNext(first);
		first = newLink;
	}
}
