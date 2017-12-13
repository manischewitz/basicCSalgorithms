package queue;

import perfomance.Algorithm;
import perfomance.Queue;

public class Test {

	public static void main(String[] args) {
		//arrayQueueTest();
		//loopArrayQueueTest();
		
		Algorithm<AbstractQueue<Integer>> test = new Queue<>();
		test.startExecuting(new ArrayQueue<Integer>());
		test.startExecuting(new LinkedQueue<Integer>());
	}
	
	private static void arrayQueueTest() {
		LoopQueue<Integer> queue = new LoopArrayQueue<>(10);
		queue.display();
		
		for (int i = 0; i < 100; i+= 10) {
			queue.insert(i);
		}
		
		queue.display();
		if (queue.isFull()) {
			System.out.println(queue.peekFront());
			System.out.println(queue.remove());
			queue.insert(777);
			queue.display();
			queue.remove();
			queue.remove();
			queue.remove();
			queue.remove();
			queue.insert(256);
			queue.display();
		}
		System.out.println(queue.size());
	}
	
	private static void loopArrayQueueTest() {
		AbstractQueue<Integer> queue = new ArrayQueue<>(5);
		queue.display();
		
		for (int i = 0; i <= 200; i+= 10) {
			queue.insert(i);
		}
		
		queue.display();
		System.out.println(queue.peekFront());
		System.out.println(queue.remove());
		queue.insert(777);
		queue.display();
		queue.remove();
		queue.remove();
		queue.remove();
		queue.remove();
		queue.insert(256);
		queue.display();
		
		System.out.println(queue.size());
	}
}
