package priorityQueue;

import objects.Int64;

public class Test {

	public static void main(String[] args) {
		AbstractPriorityQueue queue = new ArrayBasedPriorityQueue();
		queue.display();

		for (int i = 0; i < 15; i++) {
			long x = (long) (Math.random()*(100));
			queue.insert(new Int64(x));
		}
		
		queue.display();
		System.out.println(queue.size());
		System.out.println(queue.peekMin());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		System.out.println(queue.remove());
		queue.display();
	}

}
