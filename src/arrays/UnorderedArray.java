package arrays;

import commands.Command;
import objects.Comparable;

public class UnorderedArray extends ConcreteArray {

	public UnorderedArray(int max) {
		super(max);
	}

	public UnorderedArray(int x, Command<Comparable[]> alg) {
		super(x, alg);
	}

	@Override
	public int has(Comparable val) {
		
		int i;
		
		for(i = 0; i < currentIndex; i++) {
			
			if(array[i].equalsWith(val)) {
				break;
			}
		}
		//is last element reached and found nothing?
		return (i == currentIndex) ? -1 : i;
	}

	// O(1)
	@Override
	public boolean push(Comparable value) {
		
		if (currentIndex == length) {
			return false;
		}
		
		array[currentIndex] = value;
		currentIndex++;
		
		return true;
	}

	@Override
	public boolean delete(Comparable value) {
		
		int i = has(value);
		
		if (i == -1) {
			return false;
		}
		
		//shift other elements in order to avoid empty fields
		for(int k = i; k < currentIndex; k++) {
			
			array[k] = array[k + 1];
		}
		
		currentIndex--;
		return true;
	}

	@Override
	public Comparable getMax() {
		
		if (currentIndex == 0) {
			return null;
		}
		
		Comparable currentMax = array[0];
		
		for (int i = 1; i < currentIndex; i++) {
			
			final Comparable current = array[i];
			currentMax = (current.greaterThan(currentMax)) ? current : currentMax;
		}
		
		return currentMax;
	}

	@Override
	public Comparable removeMax() {
		
		if (currentIndex == 0) {
			return null;
		}
		
		Comparable currentMax = array[0];
		int index = 0;
		
		for (int i = 1; i < currentIndex; i++) {
			
			final Comparable current = array[i];
			
			if (current.greaterThan(currentMax)) {
				currentMax = current;
				index = i;
			}
		}
		
		for(int k = index; k < currentIndex; k++) {
			
			array[k] = array[k + 1];
		}
				
		currentIndex--;
		return currentMax;
	}
	
	@Override
	protected Array getConcreteNewArray(int size) {
		return new UnorderedArray(size);
	}


}
