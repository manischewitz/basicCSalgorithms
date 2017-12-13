package binarySearchTree;

public interface BinarySearchTree<K, T> {

	public enum TraverseOrder {
		PRE_ORDER,
		IN_ORDER,
		POST_ORDER
	} 
	
	public T find(K withKey);
	
	public void insert(K key, T value);
	
	public T delete(K byKey);
	
	public String traverse(TraverseOrder order);
	
}
