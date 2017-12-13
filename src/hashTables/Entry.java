package hashTables;

class Entry<Key, Value> {

	Key key;
	Value value;
	
	Entry(Key key, Value value) {
		this.key = key;
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "[key=" + key + ", value=" + value + "]";
	}
}
