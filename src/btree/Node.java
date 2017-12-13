package btree;

@SuppressWarnings("unchecked")
public class Node<Key extends Comparable<Key>, Value> {
	
	private int itemsCount;
	private Node<Key, Value> parent;
	private Node<Key, Value>[] children;
	private Data[] values;
	private final int ORDER;
	
	class Data {
		Key key;
		Value value;
		
		@Override
		public String toString() {
			return "Data [key=" + key + ", value=" + value + "]";
		}
	}
	
	public Node() {
		ORDER = 4;
		children = new Node[ORDER];
		values = new Node.Data[ORDER - 1];
	}
	
	public Node(int order) {
		ORDER = order;
		children = new Node[ORDER];
		values = new Node.Data[ORDER - 1];
	}
	
	public void connectChild(int no, Node<Key, Value> child) {
		children[no] = child;
		if (child != null) {
			child.parent = this;
		}
	}
	
	public Node<Key, Value> disconnectChild(int no) {
		Node<Key, Value> temp = children[no];
		children[no] = null;
		return temp;
	}
	
	public void removeItem(int at) {
		values[at] = null;
		for (int i = at; i < itemsCount - 1; i++) {
			values[i] = values[i + 1];
		}
		itemsCount--;
	}
	
	public Node<Key, Value> getChild(int no) {
		return (no < children.length) ? children[no] : null;
	}
	
	public Value getValue(int no) {
		return values[no].value;
	}
	
	public Data getItem(int no) {
		return values[no];
	}

	public Node<Key, Value> getParent() {
		return parent;
	}

	public boolean isLeaf() {
		return children[0] == null;
	}

	public int getItemsCount() {
		return itemsCount;
	}
	
	public boolean isFull() {
		return itemsCount == ORDER - 1;
	}
	
	public int getIndex(Key key) {
		for (int i = 0; i < ORDER - 1; i++) {
			if (values[i] == null) {
				break;
			}
			int compared = key.compareTo(values[i].key);
			if (compared == 0) {
				return i;
			}
		}
		return -1;
	}
	
	public int insert(Key key, Value value) {
		//duplicates are not allowed
		for (int i = 0; i < itemsCount; i++) {
			Data current = values[i];
			if (current != null && current.key.compareTo(key) == 0) {
				current.value = value;
				System.out.println("DUPLICATE");
				return -1;
			}
		}
		
		final Data data = new Data();
		data.key = key;
		data.value = value;
		itemsCount++;
		
		for (int i = ORDER - 2; i >= 0 ; i--) {
			if (values[i] != null) {
				int compared = key.compareTo(values[i].key);
				if (compared < 0) {
					values[i + 1] = values[i];
				} else {
					values[i + 1] = data;
					return i + 1;
				}
			}
		}
		values[0] = data;
		return 0;
	} 
	
	public Data removeMax() {
		Data temp = values[itemsCount - 1];
		values[itemsCount - 1] = null;
		itemsCount--;
		return temp;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer(this.getClass().getName());
		sb.append(" [itemsCount=");
		sb.append(itemsCount);
		sb.append(", items=");
		for (int i = 0; i < itemsCount; i++) {
			sb.append('\n');
			sb.append(values[i].toString());
		}
		sb.append(']');
		return sb.toString();
	}
	
}
