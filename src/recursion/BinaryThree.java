package recursion;

public class BinaryThree {

	private final static BinaryThree instance = new BinaryThree();
	private StringBuffer tree;
	private int lineLength;
	
	private BinaryThree () {}
	
	public static BinaryThree getInstance() {
		return instance;
	}
	
	public String drawTree(int levels) {
		if (levels % 2 != 0 || levels < 0) {
			throw new IllegalArgumentException("Base 2 logarithm of ${levels} should be natural number.");
		}
		tree = new StringBuffer();
		lineLength = levels * levels;
		solve(1, lineLength / 2);
		return tree.toString();
	}
	
	private void solve(final int n, final int range) {
		int injections = n;
		int currentRange = range;
		
		for (int i = 0; i < lineLength; i++) {
			
			if (injections != 0 && currentRange == 0) {
				tree.append('X');
				injections--;
				currentRange = (range != 0) ? range * 2 : 1;
			} else {
				tree.append('-');
			}
			currentRange--;
		}
		
		tree.append('\n');
		if (range != 0) {
			solve(n * 2, range / 2);
		} 
	}
	
	public static void main(String[] args) {
		System.out.println(BinaryThree.getInstance().drawTree(3));
	}
}
