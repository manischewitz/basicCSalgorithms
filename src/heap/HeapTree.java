package heap;

import stack.AbstractStack;
import stack.ArrayStack;

public class HeapTree<T extends Comparable<T>> implements Heap<T> {

	private class Node {
		Node parent;
		Node left;
		Node right;
		T payload;
		
		Node(HeapTree<T>.Node parent, T payload) {
			this.parent = parent;
			this.payload = payload;
		}
	}
	
	private int size;
	private Node root;
	
	private StringBuffer toBinary(int value) {
		StringBuffer sb = new StringBuffer();
		while (value >= 1) {
			sb.append(value % 2);
			value = value / 2;
		}
		return sb;
	}
	
	@Override
	public void insert(T value) {
		size++;
		
		if (root == null) {
			root = new Node(null, value);
		} else {
			Node current = root;
			int i;
			StringBuffer path = toBinary(size).reverse();
			
			for (i = 1; i < path.length(); i++) {
				
				if (path.charAt(i) == '1' && current.right == null) {
					current.right = new Node(current, value);
					current.right.parent = current;
					goUp(current.right, path);
				} else if (path.charAt(i) == '1') {
					current = current.right;
				} else if (path.charAt(i) == '0' && current.left == null) {
					current.left = new Node(current, value);
					current.left.parent = current;
					goUp(current.left, path);
				} else {
					current = current.left;
				}
			}
		}
	}
	
	private void goUp(Node start, StringBuffer path) {
		Node current = start.parent; 
		T temp = start.payload;
		
		for (int i = path.length() - 1; i > 0; i--) {
			int compared = temp.compareTo(current.payload);
			
			if (compared > 0) {
				if (path.charAt(i) == '1') {
					current.right.payload = current.payload;
				} else {
					current.left.payload = current.payload;
				}
				current.payload = temp;
			} else {
				break;
			}
			current = current.parent;
		}
	}
	
	private void goDown(Node start) {
		Node larger = start;
		T temp = start.payload;
		while (larger != null) {
			
			if (larger.right != null && larger.left != null) {
				int compared = larger.left.payload.compareTo(larger.right.payload);
				larger = (compared > 0) ? larger.left : larger.right;
			} else if (larger.right != null && larger.left == null) {
				larger = larger.right;
			} else if (larger.right == null && larger.left != null) {
				larger = larger.left;
			} else {
				break;
			}
			
			if (temp.compareTo(larger.payload) >= 0) {
				break;
			}
			larger.parent.payload = larger.payload;
			larger.payload = temp;
		}
	}

	@Override
	public T deleteMax() {
		if (root == null) {
			return null;
		} 
		
		T temp = root.payload;
		
		if (size == 1) {
			root = null;
			size--;
			return temp;
		}
		
		Node current = root;
		StringBuffer path = toBinary(size).reverse();
		int i;
		
		for (i = 1; i < path.length(); i++) {
			if (path.charAt(i) == '1') {
				current = current.right;
			} else {
				current = current.left;
			}
		}
		
		root.payload = current.payload;
		
		if (current.parent.left == current) {
			current.parent.left = null;
		} else if (current.parent.right == current) {
			current.parent.right = null;
		}
		
		size--;
		goDown(root);
		return temp;
	}

	@Override
	public T getMax() {
		return (root != null) ? root.payload : null;
	}
	
	@Override
	public void offer(T value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void restoreHeap() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return size;
	}
	
	@Override
	public String toString() {
		final AbstractStack<Node> stack = new ArrayStack<>();
		final StringBuffer sb = new StringBuffer();
		stack.push(root);
		int blanks = 32;
		boolean isRowEmpty = false;
		
		while (!isRowEmpty) {
			AbstractStack<Node> localStack = new ArrayStack<>();
			isRowEmpty = true;
			
			for (int i = 0; i < blanks; i++) {
				sb.append(' ');
			}
			
			while (!stack.isEmpty()) {
				Node temp = stack.pop();
				if (temp != null) {
					sb.append(temp.payload.toString());
					localStack.push(temp.left);
					localStack.push(temp.right);
					if (temp.left != null || temp.right != null) {
						isRowEmpty = false;
					}
				} else {
					sb.append("**");
					localStack.push(null);
					localStack.push(null);
				}
				for (int i = 0; i < blanks * 2 - 2; i++) {
					sb.append(' ');
				}
			}
			
			sb.append('\n');
			blanks /= 2;
			while (!localStack.isEmpty()) {
				stack.push(localStack.pop());
			}
		}
		return sb.toString();
		/*final StringBuffer sb = new StringBuffer();
		traverse(sb, root);
		return sb.toString();*/
	}
	
	private void traverse(StringBuffer sb, Node start) {
		if (start != null) {
			sb.append(start.payload.toString());
			sb.append("\n go left ");
			traverse(sb, start.left);
			sb.append("\n go right ");
			traverse(sb, start.right);
		} else {
			sb.append(" end ");
		}
	}

}
