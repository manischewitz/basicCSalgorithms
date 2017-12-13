package arrays;

import commands.Command;
import commands.SortingCommands;
import objects.Comparable;

public abstract class ConcreteArray implements Array {

	protected Comparable[] array;
	protected int currentIndex;
	protected final int length;
	protected Command<Comparable[]> sortingAlgorithm;
	
	public ConcreteArray(int max) {
		this.array = new Comparable[max + 2];
		length = max;
		this.currentIndex = 0;
		sortingAlgorithm = new SortingCommands().new InsertionSort();
	}
	
	public ConcreteArray(int max, Command<Comparable[]> sortingAlgorithm) {
		this.array = new Comparable[max + 2];
		length = max;
		this.currentIndex = 0;
		this.sortingAlgorithm = sortingAlgorithm;
	}
	
	@Override
	public abstract int has(Comparable value);

	@Override
	public abstract boolean push(Comparable value);

	@Override
	public abstract Comparable getMax();
	
	@Override
	public abstract boolean delete(Comparable value);

	@Override
	public abstract Comparable removeMax();
	
	protected abstract Array getConcreteNewArray(int size);
	
	@Override
	public Comparable[] toArray() {
		Comparable[] newArray = new Comparable[currentIndex];
		for (int i = 0; i < currentIndex; i++) {
			newArray[i] = array[i];
		}
		return newArray;
	}
	
	private Array mergeArrays(Array array, int size) {

		final Array newArray = getConcreteNewArray(size);
		int k = 0;
		
		for (int i = 0; i < currentIndex; i++) {
			newArray.push(this.array[i]);
		}
		
		Comparable item = array.get(k);
		
		while (null != item) {
			newArray.push(item);
			k++;
			item = array.get(k);
		}
		
		return newArray;
	}
	
	@Override
	public void display() {

		final StringBuffer sb = new StringBuffer("Array length [");
		sb.append(length);
		sb.append("] Current Index [");
		sb.append(currentIndex);
		sb.append("]\n");
		
		for (int i = 0; i < currentIndex; i++) {
			sb.append(array[i].toString());
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}

	@Override
	public int size() {
		return currentIndex;
	}

	@Override
	public Array merge(Array array) {
		
		final int calculatedSize = currentIndex + array.size();
		return mergeArrays(array, calculatedSize);
	}

	@Override
	public Array merge(Array array, int size) {

		final int calculatedSize = currentIndex + array.size();
		
		if (size < calculatedSize) {
			throw new UnsupportedOperationException();
		}
		
		return mergeArrays(array, size);
	}

	@Override
	public Comparable get(int index) {

		if (index >= length) {
			return null;
		}
		
		return array[index];
	}
	
	@Override
	public void deleteDuplicates() {
		
		for (int i = 0; i < currentIndex; i++) {
			
			final Comparable current = array[i];
			
			for (int k = i + 1; k < currentIndex; k++) {
				
				if (array[k].equals(current)) {
					
					for (int m = k; m < currentIndex; m++) {
						array[m] = array[m + 1];
					}
					
					currentIndex--;
				}
			}
		}
	}
	
	@Override
	public void sortAndDeleteDuplicates() { 
		sort();
		Comparable current;
		final int index = currentIndex;
		int i, j = 1;
		for (i = 1; i < index; i++) {
			current = array[i - 1];
			while (j < index) {
				if (!array[j].equals(current)) {
					break;
				}
				j++;
			} 
			if (j >= index) {
				break;
			}
			array[i] = array[j];
		}
		currentIndex = i;
	}
	
	@Override
	public Comparable median() {
		final Comparable[] a = this.toArray();
		final int length = a.length;
		final int size = (length % 2 == 0) ? length / 2 : (length + 1) / 2;
		Array.insertionSort(a);
		return a[size - 1];
	}
	
	@Override
	public void sort() {
		final Comparable[] a = this.toArray();
		this.sortingAlgorithm.execute(a);
		this.array = a;
		this.currentIndex = a.length;
	}
	
}
