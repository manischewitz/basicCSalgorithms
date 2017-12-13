package recursion;

public class KnapsackProblem {

	private static final KnapsackProblem INSTANCE = new KnapsackProblem();
	private int length;
	private int targetSize;
	private int[] items;
	
	private KnapsackProblem () { }

	public static KnapsackProblem getInstance() {
		return INSTANCE;
	}

	public int[] solve(int[] items, int size) {
		this.targetSize = size;
		this.length = items.length;
		this.items = items;
		problem(length);problem(length);
		int sum = 0;
		for (int i = 0; i < length; i++) {
			sum += items[i];
		}
		return (sum == targetSize) ? this.items : new int[0];
	}
	
	public void problem(int newSize) {
		if (newSize != 1) {
			for (int i = 0; i < newSize; i++) {
				problem(newSize - 1);
				if (newSize == 2) {
					int current = 0;
					for (int b = 0; b < length; b++) {
						if (current > targetSize) {
							break;
						} else if (current == targetSize) {
							items[b] = 0;
						} else if (current < targetSize) {
							current += items[b];
						}
					}
				}
				rotate(newSize);
			}
		}
	}
	
	private void rotate(int newSize) {
		int j;
		final int position = length - newSize;
		int temp = items[position];
		for (j = position + 1; j < length; j++) {
			items[j - 1] = items[j];
		}
		items[j - 1] = temp;
	}
	
	public static void main(String[] args) {
		//int[] k = {8, 33, 7, 687, 457, 2, 6, 243, 5, 56, 90};
		int[] k = {10, 10, 0, 10, 67};
		int[] solved = KnapsackProblem.getInstance().solve(k, 20);
		
		for (int i = 0; i < solved.length; i++) {
			System.out.print(solved[i] + " ");
		}
	}
}
