package hashTables;

public interface HashTable<Key, Value> {

	public void put(Key key, Value value);
	
	public Value get(Key by);
	
	public Value remove(Key by);
	
	public int size();
}
