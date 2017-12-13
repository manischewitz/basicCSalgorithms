package hashTables;

import binarySearchTree.LinkedBinarySearchTree;
import binarySearchTree.RedBlackTree;
import objects.Int64;
import objects.Person;
import perfomance.HashTables;
import perfomance.Trees;

public class Test {

	public static void main(String[] args) {
		//test(new OpenAddressingHashTable<Int64, String>());
		
		new HashTables(100000).startExecuting(new OpenAddressingHashTable<Person, String>());
		new Trees(100000).startExecuting(new RedBlackTree<Person, String>());
		new HashTables(100000).startExecuting(new ChainingHashTable<Person, String>());
		
		//test(new ChainingHashTable<Int64, String>());
	}
	
	private static void test(HashTable<Int64, String> table) {
		for (int i = 0; i < 25; i++) {
			long x = (long) (Math.random()*(100));
			table.put(new Int64(x), "Val " + x);
		}
		Int64 value1 = new Int64(1000000);
		table.put(value1, "MANUALLY INSERTED");
		System.out.println(table.toString());
		System.out.println(table.get(value1));
		System.out.println(table.size());
		System.out.println(table.remove(value1));
		System.out.println(table.toString());
		System.out.println(table.size());
	}

}
