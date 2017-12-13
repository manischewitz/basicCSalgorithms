package btree;


public interface BTreeMap<Key, Value> {

	public Value get(Key key);
	
	public void put(Key key, Value value);
	
	public Value delete(Key key);
	
	public int size();
	
	public Value getMin();
	
	public String traverse();
	
}
