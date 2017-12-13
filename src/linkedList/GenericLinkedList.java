package linkedList;

abstract class GenericLinkedList<T> {

	protected Link<T> first = null;
	
	public boolean isEmpty() {
		return (first == null);
	}
	
	public void display() {
		StringBuffer sb = new StringBuffer(this.getClass().getName());
		Link<T> current = first;
		int no = 0;
		while(current != null) {
			sb.append("\n");
			sb.append(no);
			sb.append(" -> ");
			sb.append(current.toString());
			current = current.getNext();
			no++;
		}
		System.out.println(sb.toString());
	}
	
	public int size() {
		Link<T> current = first;
		int no = 0;
		while(current != null) {
			current = current.getNext();
			no++;
		}
		return no;
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
			first = first.getNext();
		} else {
			before.setNext(current.getNext());
		}
		return true;
	}
	
	public T getFirst() {
		return first.getPayload();
	}
	
	public boolean has(T value) {
		Link<T> current = first;
		while(current != null) {
			if (current.getPayload().equals(value)) {
				return true;
			} else {
				current = current.getNext();
			}
		}
		return false;
	}
	
	public T deleteFirst() {
		if (isEmpty()) {
			return null;
		} else {
			Link<T> temp = first;
			first = first.getNext();
			return temp.getPayload();
		}
	}
}
