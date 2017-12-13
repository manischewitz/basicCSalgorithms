package btree;

public class Tree234<Key extends Comparable<Key>, Value extends Object> implements BTreeMap<Key, Value> {

	private Node<Key, Value> root = new Node<>();
	private int size = 0;
	
	@Override
	public Value get(Key key) {
		Node<Key, Value> found = find(key);
		int i = found.getIndex(key);
		return (i < 0) ? null : found.getItem(i).value;
	}
	
	private Node<Key, Value> find(Key key) {
		Node<Key, Value> current = root;
		
		while(true) {
			if (current.getIndex(key) != -1) {
				return current;
			} else if (current.isLeaf()) {
				return null;
			} else {
				current = getNextChild(current, key);
			}
		}
	}

	@Override
	public void put(Key key, Value value) {
		Node<Key, Value> current = root;
		
		while (true) {
			if (current.isFull()) {
				split(current);
				current = current.getParent();
				current = getNextChild(current, key);
			} else if (current.isLeaf()) {
				break;
			} else {
				current = getNextChild(current, key);
			}
		}
		if (current.insert(key, value) != -1) {
			size++;
		}
	}
	
	private void split(Node<Key, Value> splitted) {
		Node<Key, Value>.Data itemB, itemC;
		Node<Key, Value> parent, secondChild, thirdChild;
		int itemIndex;
		
		itemC = splitted.removeMax();
		itemB = splitted.removeMax();
		secondChild = splitted.disconnectChild(2);
		thirdChild = splitted.disconnectChild(3);
		Node<Key, Value> newRight = new Node<>();
		
		if (splitted == root) {
			root = new Node<>();
			parent = root;
			root.connectChild(0, splitted);
		} else {
			parent = splitted.getParent();
		}
		
		itemIndex = parent.insert(itemB.key, itemB.value);
		int n = parent.getItemsCount();
		
		for (int i = n - 1; i > itemIndex; i--) {
			Node<Key, Value> temp = parent.disconnectChild(i);
			parent.connectChild(i + 1, temp);
		}
		
		parent.connectChild(itemIndex + 1, newRight);
		newRight.insert(itemC.key, itemC.value);
		newRight.connectChild(0, secondChild);
		newRight.connectChild(1, thirdChild);
	}
	
	private Node<Key, Value> getNextChild(Node<Key, Value> current, Key key) {
		int i;
		int count = current.getItemsCount();
		for (i = 0; i < count; i++) {
			int compared = key.compareTo(current.getItem(i).key);
			if (compared < 0) {
				return current.getChild(i);
			}
		}
		return current.getChild(i);
	}

	@Override
	public Value delete(Key key) {
		/*Node<Key, Value> found = find(key);
		if (found != null) {
			int i = found.getIndex(key);
			if (i < 0) {
				return null;
			} 
			Value temp = found.getValue(i);
			found.removeItem(i);
			size--;
			return temp;
		}*/
		return null;
	}

	@Override
	public int size() {
		return size;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Tree234 values=[");
		toString(root, 0, 0, sb);
		sb.append("\n]");
		return sb.toString();
	}
	
	private void toString(Node<Key, Value> current, int level, int childNo, final StringBuffer sb) {
		sb.append("\nLevel=");
		sb.append(level);
		sb.append(" child No=");
		sb.append(childNo);
		sb.append(" Node=");
		sb.append(current.toString());
		
		int count = current.getItemsCount();
		for (int i = 0; i < count + 1; i++) {
			Node<Key, Value> next = current.getChild(i);
			if (next != null) {
				toString(next, level + 1, i, sb);
			} else {
				return;
			}
		}
	}

	@Override
	public Value getMin() {
		return getMin(root).getValue(0);
	}
	
	private Node<Key, Value> getMin(Node<Key, Value> current) {
		Node<Key, Value> mostLeftChild;
		if (current != null) {
			mostLeftChild = current.getChild(0);
			return (mostLeftChild != null) ? getMin(mostLeftChild) : current;
		}
		return current;
	}

	@Override
	public String traverse() {
		final StringBuffer sb = new StringBuffer();
		traverse(sb, root);
		return sb.toString();
	}
	
	private void traverse(final StringBuffer sb, Node<Key, Value> current) {
		if (current != null) {
			for (int i = 0; i < current.getItemsCount() + 1; i++) {
				if (i == 1) {
					sb.append(current.toString());
					sb.append('\n');
				}
				traverse(sb, current.getChild(i));
			}
		}
	}

}
