package binarySearchTree;

import stack.AbstractStack;
import stack.ArrayStack;

public class LinkedBinarySearchTree<K extends objects.Comparable, T> implements BinarySearchTree<K, T> {

	private Node<K, T> root;
	
	@Override
	public T find(K withKey) {
		/*if (root == null) {
			return null;
		}*/
		Node<K, T> current = root;
		
		while (!current.getKey().equalsWith(withKey)) {
			
			current = (withKey.lessThan(current.getKey())) ? 
					current.getLeftChild() : current.getRightChild();
			if (current == null) {
				return null;
			}
		}
		return current.getPayload();
	}

	@Override
	public void insert(K key, T value) {
		Node<K, T> newNode = new Node<>(key, value);
		
		if (root == null) {
			root = newNode;
		} else {
			Node<K, T> current = root;
			Node<K, T> parent;
			
			while (true) {
				parent = current;
				
				if (key.lessThan(current.getKey())) {
					current = current.getLeftChild();
					if (current == null) {
						parent.setLeftChild(newNode);
						return;
					}
				} else {
					current = current.getRightChild();
					if (current == null) {
						parent.setRightChild(newNode);
						return;
					}
				}
			}
		}
	}

	@Override
	public T delete(K byKey) {
		if (root == null) {
			return null;
		}
		Node<K, T> current = root;
		Node<K, T> parent = root;
		boolean isLeftChild = true;
		//find Node to delete
		while (!byKey.equalsWith(current.getKey())) {
			parent = current;
			isLeftChild = byKey.lessThan(current.getKey());
			current = (isLeftChild) ? current.getLeftChild() : 
				current.getRightChild();
			if (current == null) {
				return null;
			}
		}
		//Node does not have children - just delete it
		if (current.getLeftChild() == null && current.getRightChild() == null) {
			if (current == root) {
				root = null;
			} else if (isLeftChild) {
				parent.setLeftChild(null);
			} else {
				parent.setRightChild(null);
			}
		}
		//Right child does not exist (left is still in place) 
		//replace node with its left child
		else if (current.getRightChild() == null) {
			if (current == root) {
				root = current.getLeftChild();
			} else if (isLeftChild) {
				parent.setLeftChild(current.getLeftChild());
			} else {
				parent.setRightChild(current.getLeftChild());
			}
		} 
		//same for the left child
		else if (current.getLeftChild() == null)  {
			if (current == root) {
				root = current.getRightChild();
			} else if (isLeftChild) {
				parent.setLeftChild(current.getRightChild());
			} else {
				parent.setRightChild(current.getRightChild());
			}
		} 
		//Two children exist - find successor
		else {
			Node<K, T> successor = findSuccessor(current);
			
			if (root == current) {
				root = successor;
			} else if (isLeftChild) {
				parent.setLeftChild(successor);
			} else {
				parent.setRightChild(successor);
			}
		}
		return current.getPayload();
	}
	
	private Node<K, T> findSuccessor(Node<K, T> nodeToDelete) {
		Node<K, T> successorParent = nodeToDelete;
		Node<K, T> successor = nodeToDelete;
		Node<K, T> current = nodeToDelete.getRightChild();
		
		while (current != null) {
			successorParent = successor;
			successor = current;
			current = current.getLeftChild();
		}
		
		if (successor != nodeToDelete.getRightChild()) {
			successorParent.setLeftChild(successor.getRightChild());
			successor.setRightChild(nodeToDelete.getRightChild());
		}
		
		return successor;
	}

	@Override
	public String traverse(TraverseOrder order) {
		final StringBuffer sb = new StringBuffer();
		switch (order) {
		case IN_ORDER: inOrder(sb, root);
			break;
		case POST_ORDER: postOrder(sb, root);
			break;
		case PRE_ORDER: preOrder(sb, root);
			break;
		}
		return sb.toString();
	}
	
	
	private void preOrder(final StringBuffer sb, final Node<K, T> localRoot) {
		if (localRoot != null) {
			sb.append(localRoot.toString());
			sb.append('\n');
			preOrder(sb, localRoot.getLeftChild());
			preOrder(sb, localRoot.getRightChild());
		}
	}

	private void inOrder(final StringBuffer sb, final Node<K, T> localRoot) {
		if (localRoot != null) {
			inOrder(sb, localRoot.getLeftChild());
			sb.append(localRoot.toString());
			sb.append('\n');
			inOrder(sb, localRoot.getRightChild());
		}
	}

	private void postOrder(final StringBuffer sb, final Node<K, T> localRoot) {
		if (localRoot != null) {
			postOrder(sb, localRoot.getLeftChild());
			postOrder(sb, localRoot.getRightChild());
			sb.append(localRoot.toString());
			sb.append('\n');
		}
	}

	@Override
	public String toString() {
		final AbstractStack<Node<K, T>> stack = new ArrayStack<>();
		final StringBuffer sb = new StringBuffer();
		stack.push(root);
		int blanks = 32;
		boolean isRowEmpty = false;
		
		while (!isRowEmpty) {
			AbstractStack<Node<K, T>> localStack = new ArrayStack<>();
			isRowEmpty = true;
			
			for (int i = 0; i < blanks; i++) {
				sb.append(' ');
			}
			
			while (!stack.isEmpty()) {
				Node<K, T> temp = stack.pop();
				if (temp != null) {
					sb.append(temp.toString());
					localStack.push(temp.getLeftChild());
					localStack.push(temp.getRightChild());
					if (temp.getLeftChild() != null || temp.getRightChild() != null) {
						isRowEmpty = false;
					}
				} else {
					sb.append("null");
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
	}

}
