package recursion;

import java.util.ArrayList;
import java.util.List;

public class Anagrams {

	private static volatile char[] symbols;
	private static volatile int size;
	private static List<String> words;
	
	public synchronized static List<String> solve(String word){
		size = word.length();
		symbols = new char[size];
		words = new ArrayList<>(RecursionProblems.factorial(size));
		for (int i = 0; i < size; i++) {
			symbols[i] = word.charAt(i);
		}
		anagram(size);
		return words;
	}
	
	private static void anagram(int newSize) {
		if (newSize != 1) {
			for (int i = 0; i < newSize; i++) {
				anagram(newSize - 1);
				if (newSize == 2) {
					words.add(new String(symbols));
				}
				rotate(newSize);
			}
		}
	}
	
	private static void rotate(int newSize) {
		int j;
		final int position = size - newSize;
		char temp = symbols[position];
		for (j = position + 1; j < size; j++) {
			symbols[j - 1] = symbols[j];
		}
		symbols[j - 1] = temp;
	}
	
	public static void main(String[] args) {
		Anagrams.solve("1234").forEach(s -> System.out.println(s));
	}
	
}
