package heap;


@SuppressWarnings("unchecked")
public class ArrayBasedHeap<T extends Comparable<T>> implements Heap<T> {

	private Object[] heapArray;
	private int currentSize;
	private int nonHeapSize;
	
	private ArrayBasedHeap(Object[] heapArray) {
		this.heapArray = heapArray;
		nonHeapSize = heapArray.length;
	}
	
	public ArrayBasedHeap() {
		heapArray = new Object[20];
	}
	
	private void goUp(int atIndex) {
		int parent = (atIndex - 1) / 2;
		T bottom = (T) heapArray[atIndex];
		while (atIndex > 0 && ((T) heapArray[parent]).compareTo(bottom) < 0) {
			heapArray[atIndex] = heapArray[parent];
			atIndex = parent;
			parent = (parent - 1) / 2;
		}
		heapArray[atIndex] = bottom;
	}
	
	private void ensureCapacity(int newSize) {
		Object[] newHeapArray = new Object[newSize];
		
		for (int i = 0; i < currentSize; i++) {
			newHeapArray[i] = heapArray[i];
		}
		heapArray = newHeapArray;
	}
	
	private void goDown(int startFrom) {
		int largerChild;
		T root = (T) heapArray[startFrom];
		while (startFrom < currentSize / 2) {
			int leftChild = 2 * startFrom + 1;
			int rightChild = leftChild + 1;
			
			largerChild = (rightChild < currentSize && 
					((T) heapArray[leftChild]).
					compareTo((T) heapArray[rightChild]) < 0) 
					? rightChild : leftChild;
			
			if (root.compareTo((T) heapArray[largerChild]) >= 0) {
				break;
			}
			
			heapArray[startFrom] = heapArray[largerChild];
			startFrom = largerChild;
		}
		heapArray[startFrom] = root;
	}
	
	@Override
	public void insert(T value) {
		if (currentSize == heapArray.length) {
			ensureCapacity(heapArray.length * 2);
		}
		heapArray[currentSize] = value;
		goUp(currentSize);
		currentSize++;
	}
	
	@Override
	public T deleteMax() {
		if (currentSize == 0) {
			return null;
		}
		T root = (T) heapArray[0];
		currentSize--;
		heapArray[0] = heapArray[currentSize];
		goDown(0);
		return root;
	}
	
	@Override
	public T getMax() {
		return (currentSize > 0) ? (T) heapArray[0] : null;
	}
	
	@Override
	public void offer(T value) {
		final int overallSize = currentSize + nonHeapSize;
		if (overallSize == heapArray.length) {
			ensureCapacity(heapArray.length * 2);
		}
		heapArray[overallSize] = value;
		nonHeapSize++;
	}
	
	@Override
	public void restoreHeap() {
		currentSize = currentSize + nonHeapSize;
		for (int i = currentSize / 2 - 1; i >= 0; i--) {
			goDown(i);
		}
		nonHeapSize = 0;
	}
	
	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer(this.getClass().getSimpleName());
		sb.append('\n');
		
		for(int m = 0; m < currentSize; m++) {
			if(heapArray[m] != null) {
				sb.append(heapArray[m].toString());
				sb.append(' ');
			} else {
				sb.append("-- ");
			}
		}
		sb.append('\n');	 
		
		int nBlanks = 32;
		int itemsPerRow = 1;
		int column = 0;
		int j = 0; 
		
		while(currentSize > 0) {
			
			if(column == 0) {
				for(int k = 0; k < nBlanks; k++) {
					sb.append(' ');
				}
			}
			
			sb.append(heapArray[j].toString());
			j++;
			if(j == currentSize) {
				break;
			}
			
			column++;
			if(column == itemsPerRow) {
				nBlanks /= 2; 
				itemsPerRow *= 2; 
				column = 0; 
				sb.append('\n');
			 } else {
				 for(int k = 0; k < nBlanks * 2 - 2; k++) {
					 sb.append(' ');
				 }
			 }
		}
		return sb.toString();	 
	}
	
	public static <T extends Comparable<T>> void heapSort(final T[] array) {
		ArrayBasedHeap<T> heap = new ArrayBasedHeap<>(array);
		heap.restoreHeap();
		
		for (int i = array.length - 1; i >= 0; i--) {
			T biggest = heap.deleteMax();
			array[i] = biggest;
		}
	}
	
	
}
