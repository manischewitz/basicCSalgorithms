package binarySearchTree;

public class Node<K extends objects.Comparable, T> {

	private T payload;
	private K key;
	private Node<K, T> leftChild;
	private Node<K, T> rightChild;
	
	public Node(K key, T payload) {
		this.payload = payload;
		this.key = key;
	}
	
	public Node() { }
	
	public T getPayload() {
		return payload;
	}
	
	public void setPayload(T payload) {
		this.payload = payload;
	}
	
	public Node<K, T> getLeftChild() {
		return leftChild;
	}
	
	public void setLeftChild(Node<K, T> leftChild) {
		this.leftChild = leftChild;
	}
	
	public Node<K, T> getRightChild() {
		return rightChild;
	}
	
	public void setRightChild(Node<K, T> rightChild) {
		this.rightChild = rightChild;
	}
	
	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Node ");
		sb.append("payload [");
		sb.append(payload.toString());
		sb.append("] key [");
		sb.append(key.toString());
		sb.append("]");
		return sb.toString();
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}
}
