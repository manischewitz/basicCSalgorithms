package deque;

import objects.Int64;

public class Test {

	public static void main(String[] args) {
		AbstractDeque<Integer> deque = new ArrayBasedDeque<>(5);
		deque.display();
		
		for (int i = 0; i <= 100; i+=10) {
			deque.insertLeft(i);
		}
		deque.display();
		for (int i = 0; i <= 150; i+=10) {
			deque.insertRight(i);
		}
		deque.display();
		System.out.println(deque.size());
		System.out.println(deque.peekLeft());
		System.out.println(deque.peekRight()+"\n");
		
		System.out.println(deque.removeLeft());
		System.out.println(deque.removeLeft());
		System.out.println(deque.removeLeft());
		System.out.println(deque.removeLeft());
		System.out.println(deque.removeLeft() + "\n");
		System.out.println(deque.removeRight());
		System.out.println(deque.removeRight());
		System.out.println(deque.removeRight());
		System.out.println(deque.removeRight());
		System.out.println(deque.removeRight()+"\n");
		deque.display();
		System.out.println(deque.size());
	}

}
