package binarySearchTree;

import binarySearchTree.BinarySearchTree.TraverseOrder;
import objects.Int64;

public class Test {

	private final static int N = 25;
	private final static int X = 100;
	
	public static void main(String[] args) {
		BinarySearchTree<Int64, String> bst = new RedBlackTree<>();
		Int64 key = new Int64(-16);
		fillTreeWithRandomValues(bst);
		bst.insert(key, "MANUAL");
		System.out.println(bst.traverse(TraverseOrder.IN_ORDER));
		System.out.println(bst.find(key));
		bst.delete(key);
		System.out.println(bst.find(key));
		System.out.println(bst.traverse(TraverseOrder.IN_ORDER));
		System.out.println(bst);
	}
	
	private static void fillTreeWithRandomValues(BinarySearchTree<Int64, String> tree) {
		for (int i = 0; i < N; i++) {
			long x = (long) (Math.random()*(X));
			tree.insert(new Int64(x), "Val " + x);
		}
	}
	

}
