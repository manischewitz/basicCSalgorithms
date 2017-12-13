package arrays;

import commands.Command;
import objects.Comparable;

public class OrderedArray extends ConcreteArray {

	public OrderedArray(int max) {
		super(max);
	}

	public OrderedArray(int x, Command<Comparable[]> alg) {
		super(x, alg);
	}

	//binary search O(log N)
	@Override
	public int has(Comparable value) {
		
		int lowerBound = 0;
		int upperBound = currentIndex - 1;
		int current;
		
		while (true) {
			
			current = (lowerBound + upperBound) / 2;
			
			if (array[current].equalsWith(value)) {
				//element is successfully found
				return current;
			} else if (lowerBound > upperBound) {
				// not found
				return -1;
			} else {
				
				if (array[current].lessThan(value)) {
					lowerBound = current + 1; //in higher half
				} else {
					upperBound = current - 1; //in lower half
				}
			}
			
		}
		
	}

	//linear search O(N)
	@Override
	public boolean push(Comparable value) {
		
		if (currentIndex == length) {
			return false;
		}
		
		int i;
		
		for (i = 0; i < currentIndex; i++) {
			
			if (array[i].greaterThan(value)) {
				break;
			}
		}
		
		for (int k = currentIndex; k > i; k--) {
			array[k] = array[k - 1];
		}
		
		array[i] = value;
		currentIndex++;
		return true;
	}

	@Override
	public boolean delete(Comparable value) {
		
		int i = has(value);
		
		if(i == -1) {
			return false;
		} else {
			
			for (int m = i; m < currentIndex; m++) {
				array[m] = array[m + 1];
			}
			
			currentIndex--;
			return true;
		}
		
	}

	@Override
	public Comparable getMax() {
		
		if (currentIndex == 0) {
			return null;
		}
		
		return array[currentIndex - 1];
	}

	@Override
	public Comparable removeMax() {
		
		if (currentIndex == 0) {
			return null;
		}
		
		final Comparable removed = array[currentIndex - 1];
		array[currentIndex - 1] = null;
		currentIndex--;
		
		return removed;
	}

	@Override
	protected Array getConcreteNewArray(int size) {
		return new OrderedArray(size);
	}
}
