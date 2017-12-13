package arrays;

import commands.Command;
import commands.SortingCommands;
import heap.ArrayBasedHeap;
import objects.Int64;
import perfomance.Algorithm;
import perfomance.DeleteDuplicates;
import perfomance.Payload;
import perfomance.Searching;
import perfomance.SortDeleteDuplicates;
import perfomance.Sorting;

public class Test {

	private final static int N = 10000;
	private final static int X = 50000;
	private static Array unordered;
	private static Array ordered;
	
	public static void main(String[] args) {
		
		//performance(new SortingCommands().new BubbleSort());
		performance(new SortingCommands().new InsertionSort());
		//performance((a) -> Array.selectionSort(a));
		//test(new SortDeleteDuplicates());
		//searchTest();
		performance((a) -> Array.shellSort(a));
		performance((a) -> Array.QuickSort.sort(a));
		System.out.println("");
	}
	
	private static void test(Algorithm<Array> a) {
		unordered = fillArrayWithInt64(X, new UnorderedArray(X));
		ordered = fillArrayWithInt64(X, new OrderedArray(X));
		System.out.println("Unordered before");
		unordered.display();
		a.startExecuting(unordered);
		System.out.println("Unordered after");
		unordered.display();
		System.out.println("Ordered before");
		ordered.display();
		a.startExecuting(ordered);
		System.out.println("Ordered after");
		ordered.display();
		System.out.println("");
	}
	
	private static void performance(Command<objects.Comparable[]> alg) {
		unordered = fillArrayWithInt64(X, new UnorderedArray(X, alg));
		ordered = fillArrayWithInt64(X, new OrderedArray(X, alg));
		Algorithm<Array> a = new Sorting();
		System.out.println(alg.getClass().getName());
		System.out.println("Unordered");
		a.startExecuting(unordered);
		System.out.println("Ordered");
		a.startExecuting(ordered);
		System.out.println("");
	}
	
	private static void searchTest() {
		Algorithm<Payload> a = new Searching();
		
		unordered = fillArrayWithInt64(X, new UnorderedArray(X));
		System.out.println("Unordered O(N)");
		a.startExecuting(new Payload(unordered, new Int64[]{new Int64(66)}));
		
		ordered = fillArrayWithInt64(X, new OrderedArray(X));
		System.out.println("Ordered O(log N)");
		a.startExecuting(new Payload(ordered, new Int64[]{new Int64(66)}));
		
		Array recursion = fillArrayWithInt64(X, new OrderedRecursionArray(X));
		System.out.println("With recursion O(log N)");
		a.startExecuting(new Payload(recursion, new Int64[]{new Int64(66)}));
		System.out.println("");
	}
	
	private static Array fillArrayWithInt64(int n, Array array) {
		for (int i = 0; i < n; i++) {
			long x = (long) (Math.random()*(N));
			array.push(new Int64(x));
		}
		return array;
	}

}
