package hashTables;

import binarySearchTree.BinarySearchTree;
import binarySearchTree.BinarySearchTree.TraverseOrder;
import binarySearchTree.LinkedBinarySearchTree;

@SuppressWarnings("unchecked")
public class ChainingHashTable<Key, Value> implements HashTable<Key, Value> {

	private BinarySearchTree<Key, Value>[] entries;
	private int actualSize = 0;
	private final static int GROWTH_MULTIPLICATOR = 2;
	
	public ChainingHashTable () {
		entries = new LinkedBinarySearchTree[23];
		for (int i = 0; i < 23; i++) {
			entries[i] = new LinkedBinarySearchTree();
		}
	}
	
	private int getPrimeNumber(int start) {
		for (int i = start + 1; true; i++) {
			if (isPrime(i)) {
				return i;
			}
		}
	}
	
	private boolean isPrime(int x) {
		for (int i = 2; (i * i <= x); i++) {
			if (x % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	private int hash(Object value) {
		return (value != null) ? Math.abs(value.hashCode()) % entries.length : 0;
	}
	
	private void resize(int newSize) {
		/*newSize = getPrimeNumber(newSize);
		BinarySearchTree<Key, Value>[] old = entries;
		entries = new LinkedBinarySearchTree[newSize];
		
		for (int i = 0; i < old.length; i++) {
			this.put(old[i], old[i]);
		}
		*/
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer(this.getClass().getSimpleName());
		sb.append("[\n");
		for (int i = 0; i < entries.length; i++) {
			sb.append(entries[i].traverse(TraverseOrder.IN_ORDER));
			sb.append('\n');
		}
		sb.append(']');
		return sb.toString();
	}
	
	@Override
	public void put(Key key, Value value) {
		if (actualSize / entries.length > 1) {
			resize(entries.length * GROWTH_MULTIPLICATOR);
		}
		actualSize++;
		entries[hash(key)].insert(key, value);
	}

	@Override
	public Value get(Key by) {
		return entries[hash(by)].find(by);
	}

	@Override
	public Value remove(Key by) {
		Value removed = entries[hash(by)].delete(by);
		if (removed != null) {
			actualSize--;
		}
		return removed;
	}

	@Override
	public int size() {
		return actualSize;
	}

}
