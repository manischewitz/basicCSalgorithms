package commands;

import arrays.Array;

public class SortingCommands {

	public class BubbleSort implements Command<objects.Comparable[]> {
		@Override
		public void execute(objects.Comparable[] byValue) {
			Array.bubbleSort(byValue);
		}
	}
	
	public class BubbleBiderectionalSort implements Command<objects.Comparable[]> {
		@Override
		public void execute(objects.Comparable[] byValue) {
			Array.bubbleBiderectionalSort(byValue);
		}
	}
	
	public class SelectionSort implements Command<objects.Comparable[]> {
		@Override
		public void execute(objects.Comparable[] byValue) {
			Array.selectionSort(byValue);
		}
	}

	public class InsertionSort implements Command<objects.Comparable[]> {
		@Override
		public void execute(objects.Comparable[] byValue) {
			Array.insertionSort(byValue);
		}
	}
	
	public class LinkedBasedInsertionSort implements Command<objects.Comparable[]> {
		@Override
		public void execute(objects.Comparable[] byValue) {
			Array.linkedBasedInsertionSort(byValue);
		}
	}
	
	public class OddEvenSort implements Command<objects.Comparable[]> {
		@Override
		public void execute(objects.Comparable[] byValue) {
			Array.oddEvenSort(byValue);
		}
	}
}
