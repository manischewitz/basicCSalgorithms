package linkedList;

public class CycleLinkedList<T> implements AbstractCycleLinkedList<T> {

	private Link<T> current;
	
	@Override
	public void insert(T value) {
		Link<T> newElement = new ForwardLink<>(value);
		if (isEmpty()) {
			newElement.setNext(newElement);
			current = newElement;
		} else {
			Link<T> next = current.getNext();
			current.setNext(newElement);
			newElement.setNext(next);
			step();
		}
	}

	@Override
	public boolean delete(T value) {
		if (isEmpty()) {
			return false;
		} else {
			Link<T> after = current.getNext();
			Link<T> before = current;
			Link<T> curr = current;
			while (!curr.getPayload().equals(value) && after != current) {
				before = curr;
				curr = curr.getNext();
				after = curr.getNext();
			}
			if (curr == before) { //first element - match
				if (curr == after) { //is it only one
					current = null;
				} else {
					step();
					delete(value);
				}
			} else if (curr.getPayload().equals(value)) {
				before.setNext(after);
			}
		}
		return true;
	}

	@Override
	public boolean has(T value) {
		if (isEmpty()) {
			return false;
		} else {
			Link<T> curr = current;
			boolean isEqual;
			if (current == current.getNext()) {
				isEqual = !curr.getPayload().equals(value);
			} else {
				while ((isEqual = !curr.getPayload().equals(value))) {
					curr = curr.getNext();
					if (curr == current) {
						break;
					}
				}
			}
			return !isEqual; 
		}
	}

	@Override
	public void step() {
		if (!isEmpty()) {
			current = current.getNext();
		}
	}

	@Override
	public void display() {
		final StringBuffer sb = new StringBuffer(this.getClass().getName());
		if (!isEmpty()) {
			Link<T> curr = current.getNext();
			sb.append("\nCurrent -> ");
			sb.append(current.toString());
			while (curr != current) {
				sb.append("\n        -> ");
				sb.append(curr.toString());
				curr = curr.getNext();
			}
		}
		System.out.println(sb.toString());
	}

	@Override
	public int size() {
		int i = 0;
		Link<T> curr = current;
		while (curr != null) {
			curr = curr.getNext();
			i++;
			if (curr == current) {
				break;
			}
		}
		return i;
	}

	@Override
	public boolean isEmpty() {
		return (current == null);
	}

	@Override
	public T deleteAfterCurrent() {
		if (isEmpty()) {
			return null;
		}
		T temp = current.getPayload();
		current.setNext(current.getNext().getNext());
		return temp;
	}

	@Override
	public T getCurrent() {
		return (isEmpty()) ? null : current.getPayload();
	}

	@Override
	public T getNext() {
		return (isEmpty()) ? null : current.getNext().getPayload();
	}

}
