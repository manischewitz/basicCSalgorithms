package binarySearchTree;

import java.util.HashMap;
import java.util.Map;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import objects.Comparable;
import priorityQueue.AbstractPriorityQueue;
import priorityQueue.ArrayBasedPriorityQueue;

public class Huffman {

	private class Node extends objects.Comparable {

		char key;
		int insertions;
		Node leftChild;
		Node rightChild;
		String pathTo;
		
		Node(char key, int insertions) {
			this.key = key;
			this.insertions = insertions;
		}
		
		Node() {}

		@Override
		public String toString() {
			return "Node [key=" + key + ", insertions=" + insertions + "]";
		}

		@Override
		public int compareTo(Comparable value) {
			Node val = (Node) value;
			if (val.insertions < this.insertions) {
				return 1;
			} else if (val.insertions > this.insertions) {
				return -1;
			} else {
				return 0;
			}
		}
	}
	
	private Map<Character, Integer> charNinsertions = new HashMap<>();
	private Map<Character, String> table = new HashMap<>();
	private String inputSequence = "Hi there!\nI say Hello World!";
	
	public static void main(String[] args) {
		Huffman h = new Huffman();
		h.readFile("");
		Node t = h.makeHuffmanTree();
		String code = h.getCode(t);
		System.out.println(code);
		System.out.println(h.decode(code, t));
	}
	
	public void readFile(String path) {
		/**here is code to read from file char by char**/
		char[] seq = inputSequence.toCharArray();
		for (int i = 0; i < seq.length; i++) {
			push(seq[i]);
		}
	}

	private void push(char value) {
		Integer insertions = charNinsertions.get(value);
		if (insertions != null) {
			charNinsertions.put(value, insertions + 1);
		} else {
			charNinsertions.put(value, new Integer(1));
		}
	}
	
	public Node makeHuffmanTree() {
		AbstractPriorityQueue pq = new ArrayBasedPriorityQueue(charNinsertions.size());
		charNinsertions.forEach((key, value) -> pq.insert(new Node(key, value)));
		
		while (pq.size() != 1) {
			Node first = (Node) pq.remove();
			Node second = (Node) pq.remove();
			int insertions = 0;
			
			if (first != null) {
				insertions += first.insertions;
				
			}
			if (second != null) {
				insertions += second.insertions;
			}
			
			Node newNode = new Node();
			newNode.insertions = insertions;
			newNode.leftChild = first;
			newNode.rightChild = second;
			pq.insert(newNode);
		}
		return (Node) pq.remove();
	}
	
	public String getCode(final Node root) {
		final StringBuffer sb = new StringBuffer();
		char[] seq = this.inputSequence.toCharArray();
		getCode(root, "");
		
		for (int i = 0; i < seq.length; i++) {
			sb.append(table.get(seq[i]));
		}
		table.forEach((key, value) -> {
			System.out.println(key+" -> "+value);
		});
		
		return sb.toString();
	}
	
	private void getCode(Node node, final String path) {
		if (node == null) {
			return;
		}
		node.pathTo = path;
		if (node.key != '\u0000') {
			table.put(node.key, path);
		} else {
			getCode(node.leftChild, node.pathTo + "0");
			getCode(node.rightChild, node.pathTo + "1");
		}
	}
	
	private void persist(String filename, String path) {
		/**Method to save binary sequence in distinct file using %filename%.huff**/
	}
	
	private void load(String path) {
		/**Loads coded .huff file**/
	}
	
	public String decode(String code, final Node root) {
		char[] binarySeq = code.toCharArray();
		final StringBuffer sb = new StringBuffer();
		Node current = root;
		for (int i = 0; i < binarySeq.length; i++) {
			
			current = (binarySeq[i] == '0') ? current.leftChild : current.rightChild; 
			final char symbol = (current != null) ? current.key : '\u0000';
			if (symbol != '\u0000') {
				sb.append(symbol);
				current = root;
			} 
		}
		return sb.toString();
	}
	
}
