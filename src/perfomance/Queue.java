package perfomance;

import queue.AbstractQueue;

public class Queue<T> extends Algorithm<AbstractQueue<Integer>> {

	private final static int N = 100000;
	
	@Override
	protected AbstractQueue<Integer> computation(AbstractQueue<Integer> queue) {
		for(int i = 0; i < N; i++) {
			queue.insert(i);
		}
		
		for(int i = 0; i < N; i++) {
			queue.remove();
		}
		System.out.println(queue.size() == 0);
		return null;
	}

}
