package linkedList;

public class MatrixLinkedList<T> implements AbstractMatrixLinkedList<T> {

	private class Link {
		Link down;
		Link right;
		T payload;
	}
	private Link first = new Link();
	
	@Override
	public void insert(T value, int k, int j) {
		if (k < 1 || j < 1) {
			throw new IllegalArgumentException();
		}
		findConcreteLink(k, j).payload = value; 
	}
	
	@Override
	public T delete(int k, int j) {
		if (k < 1 || j < 1 || isEmpty()) {
			throw new IllegalArgumentException();
		}
		Link toDelete = findConcreteLink(k, j);
		T temp = toDelete.payload;
		toDelete.payload = null;
		return temp;
	}

	@Override
	public void delete(T value) {
		Link vertical = first;
		Link horizontal = first;
		while (vertical != null) {
			while (horizontal != null) {
				if (horizontal.payload != null && horizontal.payload.equals(value)) {
					horizontal.payload = null;
					return;
				}
				horizontal = horizontal.right;
			}
			vertical = vertical.down;
			horizontal = vertical;
		}
	}
	
	private Link findConcreteLink(int k, int j) {
		Link current = first;
		Link temp = first;
		for (int i = 0; i < j; i++) {
			temp = current;
			for (int m = 1; m < k; m++) {
				if (current.right == null) {
					current.right = new Link();
				}
				current = current.right;
			}
			if (i + 1 == j) {
				break;
			}
			current = temp;
			if (current.down == null) {
				current.down = new Link();
			}
			current = current.down;
		}
		return current; 
	}

	@Override
	public void display() {
		StringBuffer sb = new StringBuffer(this.getClass().getName());
		Link vertical = first;
		Link horizontal = first;
		while (vertical != null) {
			sb.append('\n');
			while (horizontal != null) {
				sb.append(horizontal.payload);
				sb.append(" ");
				horizontal = horizontal.right;
			}
			vertical = vertical.down;
			horizontal = vertical;
		}
		System.out.println(sb.toString());
	}
	
	public static void main(String...strings) {
		AbstractMatrixLinkedList<Integer> matrix = new MatrixLinkedList<>();
		matrix.insert(100, 3, 3);
		matrix.insert(6, 1, 3);
		matrix.insert(434, 2, 2);
		matrix.insert(3223, 1, 1);
		matrix.insert(66, 1, 2);
		matrix.display();
		
		matrix.delete(1, 1);
		matrix.display();
		
		matrix.delete(6);
		matrix.delete(666);
		matrix.display();
	}

	@Override
	public boolean isEmpty() {
		return (first == null);
	}

}
