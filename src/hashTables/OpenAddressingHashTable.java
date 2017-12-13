package hashTables;
@SuppressWarnings("unchecked")
public class OpenAddressingHashTable<Key, Value> implements HashTable<Key, Value> {

	private Entry<Key, Value>[] entries;
	private final Entry<Key, Value> nullEntry = new Entry<>(null, null);
	private int actualSize = 0;
	private final static int GROWTH_MULTIPLICATOR = 2;
	
	public OpenAddressingHashTable() {
		entries = new Entry[23];
	}
	
	public OpenAddressingHashTable(int initialSize) {
		entries = new Entry[getPrimeNumber(initialSize)];
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
	
	private int doubleHash(Object value) {
		return 5 - (((value != null) ? Math.abs(value.hashCode()) : 0) % 5);
	}
	
	@Override
	public void put(Key key, Value value) {
		if (actualSize / entries.length > 0.5) {
			resize(entries.length * GROWTH_MULTIPLICATOR);
		} 
		int hash = hash(key);
		final int step = doubleHash(key);
		
		while (entries[hash] != null && entries[hash] != nullEntry) {
			if (entries[hash].key.equals(key)) {
				entries[hash].value = value;
				return;
			}
			hash += step;
			hash %= entries.length;
		}
		entries[hash] = new Entry<>(key, value);
		actualSize++;
	}

	@Override
	public Value get(Key by) {
		int i = getIndexEntryAt(by);
		return (i != -1) ? entries[i].value : null;
	}
	
	private int getIndexEntryAt(Key by) {
		int hash = hash(by);
		final int step = doubleHash(by);
		
		while (entries[hash] != null) {
			if (by.equals(entries[hash].key)) {
				return hash;
			}
			hash += step;
			hash %= entries.length;
		}
		return -1;
	}

	@Override
	public Value remove(Key by) {
		int i = getIndexEntryAt(by);
		Value temp = null;
		if (i != -1) {
			temp = entries[i].value;
			entries[i] = nullEntry;
			actualSize--;
		}
		return temp;
	}

	@Override
	public int size() {
		return actualSize;
	}
	
	private void resize(int newSize) {
		Entry<Key, Value>[] old = entries;
		actualSize = 0;
		entries = new Entry[getPrimeNumber(newSize)];
		for (int i = 0; i < old.length; i++) {
			if (old[i] != nullEntry && old[i] != null) {
				this.put(old[i].key, old[i].value);
			}
		}
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer(this.getClass().getSimpleName());
		sb.append("[\n");
		for (int i = 0; i < entries.length; i++) {
			if (entries[i] != null && entries[i] != nullEntry) {
				sb.append(entries[i].toString());
				sb.append('\n');
			} else {
				sb.append("**\n");
			}
		}
		sb.append(']');
		return sb.toString();
	}

}
