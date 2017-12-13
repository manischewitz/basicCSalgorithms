package heap;


public class Test {

	public static void main(String[] args) {
		//test(new ArrayBasedHeap<Integer>());
		//performance(100000);
		test(new HeapTree<Integer>());
	}

	private static void test(Heap<Integer> table) {
		for (int i = 0; i < 36; i++) {
			int x = (int) (Math.random()*(100));
			table.insert(x);
		}
		Integer value1 = 1000000;
		table.insert(value1);
		System.out.println(table.toString());
		System.out.println(table.deleteMax());
		System.out.println(table.size());	
		System.out.println(table.getMax());
		System.out.println(table.toString());
		
	}
	
	private static void performance(int n) {
		final int X = n;
		Integer[] array = new Integer[X];
		for (int i = 0; i < X; i++) {
			int x = (int) (Math.random()*(1000));
			array[i] = x;
		}
		
		for (int i = 0; i < X; i++) {
			//System.out.print(array[i]+" ");
		}
		System.out.print('\n');
		
		long start = System.currentTimeMillis();
		ArrayBasedHeap.heapSort(array);
		long end = System.currentTimeMillis();
		
		for (int i = 0; i < X; i++) {
			//System.out.print(array[i]+" ");
		}
		
		System.out.println("\n"+(end - start));
	}
	
	
}
