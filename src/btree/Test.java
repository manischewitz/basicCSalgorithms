package btree;

import objects.Int64;

public class Test {

	private final static int N = 25;
	private final static int X = 100;
	
	public static void main(String[] args) {
		test(new Tree234<Int64, String>());

	}
	
	private static void test(BTreeMap<Int64, String> tree) {
		for (int i = 0; i < N; i++) {
			long x = (long) (Math.random()*(X));
			tree.put(new Int64(x), "Val " + x);
		}
		
		Int64 val = new Int64(X + 1);
		tree.put(val, "SPECIAL");
		System.out.println(tree.get(val));
		System.out.println(tree.toString());
		System.out.println(tree.delete(val));
		
		System.out.println(tree.size() == N);
		System.out.println(tree.toString());
		System.out.println(tree.delete(new Int64(X + 1000)));
		
		System.out.println(tree.traverse());
		System.out.println(tree.getMin());
		System.out.println(tree.size());
	}

}
