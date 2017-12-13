package arrays;

import commands.Command;
import linkedList.AbstractSortedLinkedList;
import linkedList.SortedLinkedList;
import objects.Comparable;

public interface Array {

	public int has(Comparable value);
	
	public boolean push(Comparable value);
	
	public boolean delete(Comparable value);
	
	public void display();
	
	public int size();
	
	public Comparable getMax();
	
	public Comparable removeMax();
	
	public Array merge(final Array array);
	
	public Array merge(final Array array, final int size);
	
	public Comparable get(int index);
	
	public void deleteDuplicates();
	
	public void sortAndDeleteDuplicates();
	
	public void sort();
	
	public Comparable[] toArray();
	
	public Comparable median();
	
	//Most slow sorting among all simple sorting algorithms. Complexity: O(N^2)
	public static void bubbleSort(Comparable[] array) {
		int i, k;
		Comparable current, next;
		for (i = array.length - 1; i > 1; i--) {
			
			for (k = 0; k < i; k++) {
				current = array[k];
				next = array[k + 1];
				if (next.lessThan(current)) {
					array[k + 1] = current;
					array[k] = next;
				}
			}
		}
	}
	
	public static void bubbleBiderectionalSort(Comparable[] array) {
		int i, k, j;
		Comparable current, next, preceding;
		for (i = array.length - 1; i > 1; i--) {
			
			for (k = 0; k < i; k++) {
				current = array[k];
				next = array[k + 1];
				if (next.lessThan(current)) {
					array[k + 1] = current;
					array[k] = next;
				}
			}
			for (j = i; j >= array.length - i; j--) {
				current = array[j];
				preceding = array[j - 1];
				if (current.lessThan(preceding)) {
					array[j - 1] = current;
					array[j] = preceding;
				}
			}
		}
	}
	
	//Slightly faster than bubbleSort()
	public static void selectionSort(Comparable[] array) {
		int a, b, min;
		Comparable currentMin, current;
		
		for (a = 0; a < array.length - 1; a++) {
			min = a;
			current = array[a];
			currentMin = array[a];
			for (b = a + 1; b < array.length; b++) {
				if (array[b].lessThan(currentMin)) {
					min = b;
				}
			}
			array[a] = array[min];
			array[min] = current;
		}
	}
	
	//Most effective among simple sorting. Better with pre-sorted structures.
	public static void insertionSort(Comparable[] array) {
		int i, k;
		Comparable temp;
		for (i = 1; i < array.length; i++) {
			
			temp = array[i];
			k = i;
			while (k > 0 && (array[k - 1].equals(temp) || array[k - 1].greaterThan(temp))) {
				array[k] = array[k - 1];
				k--;
			}
			array[k] = temp;
		}
	}
	
	public static void linkedBasedInsertionSort(Comparable[] array) {
		AbstractSortedLinkedList<Comparable> asll = new SortedLinkedList<>();
		for (int i = 0; i < array.length; i++) {
			asll.insert(array[i]);
		}
		
		for (int i = 0; i < array.length; i++) {
			array[i] = asll.deleteFirst();
		}
	}
	
	//Most efficient with at least two processors (one per odd and even)
	public static void oddEvenSort(Comparable[] array) {
		int i, k;
		boolean odd = true;
		Comparable current, next;
		for (i = array.length - 1; i > 1; i--) {
			
			if (odd) {
				for (k = 1; k < i; k+=2) {
					current = array[k];
					next = array[k + 1];
					if (current.lessThan(next)) {
						array[k] = next;
						array[k + 1] = current;
					}
				}
			} else {
				for (k = 0; k < i; k+=2) {
					current = array[k];
					next = array[k + 1];
					if (current.lessThan(next)) {
						array[k] = next;
						array[k + 1] = current;
					}
				}
			}
			odd = !odd;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] copy(final T[] array, final int addSize) {
		final int length = array.length;
		Object[] newArray = new Object[length + addSize];
		
		for (int i = 0; i < length; i++) {
			newArray[i] = array[i];
		}
		return (T[]) newArray;
	}
	// O(N*log(N))
	public static class MergeSort {
		
		private static volatile Comparable[] array;
		
		public static synchronized void mergeSort(Comparable[] array) {
			MergeSort.array = array;
			final int length = array.length;
			Comparable[] temp = new Comparable[length];
			doMergeSort(temp, 0, length - 1);
		}
		
		private static void doMergeSort(Comparable[] temp, int low, int upper) {
			if (low != upper) {
				int middle = (low + upper) / 2;
				doMergeSort(temp, low, middle);
				doMergeSort(temp, middle + 1, upper);
				merge(temp, low, middle + 1, upper);
			}
		}
		
		private static void merge(Comparable[] temp, int low, int middle, int upper) {
			int j = 0;
			int lowerBound = low;
			int mid = middle - 1;
			int n = upper - low + 1;
			
			while (low <= mid && middle <= upper) {
				if (array[low].lessThan(array[middle])) {
					temp[j] = array[low];
					low++;		
				} else {
					temp[j] = array[middle];
					middle++;
				}
				j++;
			}
			
			while (low <= mid) {
				temp[j] = array[low];
				low++;
				j++;
			}
			
			while (middle <= upper) {
				temp[j] = array[middle];
				middle++;
				j++;
			}
			
			for (j = 0; j < n; j++) {
				array[lowerBound + j] = temp[j];
			}
		}
	}
	
	// O(N^1.17 - N^1.5)
	public static void shellSort(Comparable[] array) {
		int a, b;
		final int size = array.length;
		Comparable temp;
		int h = 1; // h = h*3 + 1 (Knuth)
		
		while (h <= size / 3) {
			h = h * 3 + 1;
		}
		
		while (h > 0) {
			
			for (a = h; a < size; a++) {
				temp = array[a];
				b = a;
				
				while (b > h - 1 && (array[b - h].greaterThan(temp) || array[b - h].equalsWith(temp))) {
					array[b] = array[b - h];
					b -= h;
				}
				array[b] = temp;
			}
			h = (h - 1) / 3;
		}
	}
	
	// O(N * Log(N))
	public static class QuickSort {
		
		private volatile static Comparable[] array;
		
		public synchronized static void sort(Comparable[] array) {
			QuickSort.array = array;
			doSort(0, array.length - 1);
		}

		private static void doSort(final int left, final int right) {
			int size = right - left + 1;
			if (size < 10) {
				insertionSort(left, right);
			} else {
				Comparable median = medianOfThree(left, right);
				int partition = doPartition(left, right, median);
				doSort(left, partition - 1);
				doSort(partition + 1, right);
			}
		}
		
		private static Comparable medianOfThree(int left, int right) {
			int center = (left + right) / 2;
			
			if (array[left].greaterThan(array[center])) {
				swap(left, center);
			}
			
			if (array[left].greaterThan(array[right])) {
				swap(left, right);
			}
			
			if (array[center].greaterThan(array[right])) {
				swap(center, right);
			}
			swap(center, right - 1);
			return array[right - 1];
		}
		
		private static void swap(int firstIndex, int secondIndex) {
			Comparable temp = array[firstIndex];
			array[firstIndex] = array[secondIndex];
			array[secondIndex] = temp;
		}
		
		private static int doPartition(int left, int right, Comparable median) {
			int leftPointer = left;
			int rightPointer = right - 1;
			
			while (true) {
				
				while(array[++leftPointer].lessThan(median));
				
				while(array[--rightPointer].greaterThan(median));
				
				if (leftPointer >= rightPointer) {
					break;
				} else {
					swap(leftPointer, rightPointer);
				}
			}
			swap(leftPointer, right - 1);
			return leftPointer;
		}
		
		private static void insertionSort(int left, int right) {
			int a, b;
			
			for (a = left + 1; a <= right; a++) {
				Comparable temp = array[a];
				b = a;
				
				while (b > left && (array[b - 1].greaterThan(temp) || array[b - 1].equalsWith(temp))) {
					array[b] = array[b - 1];
					b--;
				}
				array[b] = temp;
			}
		}
	}
	
}
