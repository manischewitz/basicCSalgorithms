package arrays;

public class OrderedRecursionArray extends OrderedArray {

	public OrderedRecursionArray(int max) {
		super(max);
	}
	
	@Override
	public int has(objects.Comparable c) {
		return recursionBinarySearch(c, 0, currentIndex - 1);
	}
	
	private int recursionBinarySearch(objects.Comparable c, int lowerBound, int upperBound) {
		int current = (lowerBound + upperBound ) / 2;
		if (array[current].equalsWith(c)) {
			return current;
		} else if (lowerBound > upperBound) {
			return -1;
		} else {
			if (c.greaterThan(array[current])) {
				return recursionBinarySearch(c, current + 1, upperBound);
			} else {
				return recursionBinarySearch(c, lowerBound, current - 1);
			}
		}
	}

}
