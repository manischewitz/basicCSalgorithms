package linkedList;

import objects.Int64;

public class Test {

	public static void main(String[] args) {
		//testLinkedList();
		//testFirstLastList();
		//testSortedLinkedList();
		//testDoublyLinkedList();
		//testDoublyLinkedListIterator();
		testCycleLinkedList();
	}
	
	private static void testCycleLinkedList() {
		AbstractCycleLinkedList<Integer> cll = new CycleLinkedList<>();
		Integer num1 = new Integer(228);
		cll.insert(1);
		cll.insert(2);
		cll.step();
		cll.insert(3);
		cll.insert(num1);
		cll.insert(200);
		cll.insert(777);
		cll.display();
		
		cll.delete(3);
		cll.display();
		cll.delete(1);
		cll.display();
		cll.delete(1);
		cll.display();
		
		System.out.println(cll.has(777));
		System.out.println(cll.has(888));
		System.out.println(cll.size());
		
		System.out.println(cll.getCurrent());
		cll.step();
		System.out.println(cll.getCurrent());
		cll.deleteAfterCurrent();
		cll.display();
	}
	
	private static void testDoublyLinkedListIterator() {
		AbstractDoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
		dll.insertLast(44);
		dll.insertLast(234);
		dll.insertLast(2532);
		dll.insertLast(1);
		dll.insertLast(232);
		dll.insertLast(8);
		dll.insertLast(26);
		dll.insertLast(89);
		
		Iterator<Integer> it = dll.createIterator();
		while(it.hasNext()) {
			final Integer val = it.next();
			System.out.println(val);
			if (val % 2 == 0) {
				it.remove();
			}
		}
		dll.display();
	}
	
	private static void testDoublyLinkedList() {
		AbstractDoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
		Integer num1 = new Integer(228);
		Integer num2 = new Integer(128);
		
		dll.insertFirst(44);
		dll.insertFirst(234);
		dll.insertLast(num1);
		dll.insertLast(89);
		dll.display();
		
		dll.insertAfter(num1, num2);
		dll.display();
		
		dll.delete(num1);
		dll.deleteFirst();
		dll.deleteLast();
		dll.display();
		
		System.out.println(dll.has(num2));
		System.out.println(dll.has(new Integer(128)));
		System.out.println(dll.has(new Integer(89)));
	}
	
	private static void testSortedLinkedList() {
		AbstractSortedLinkedList<Int64> asll = new SortedLinkedList<>();
		Int64 num1 = new Int64(900);
		Int64 num2 = new Int64(256);
		Int64 num3 = new Int64(6);
		Int64 num4 = new Int64(777);
		Int64 num5 = new Int64(8);
		Int64 num6 = new Int64(1200);
		
		asll.insert(num1);
		asll.insert(num2);
		asll.insert(num3);
		asll.insert(num4);
		asll.insert(num5);
		asll.insert(num6);
		
		asll.display();
		asll.delete(num5);
		asll.deleteFirst();
		asll.display();
		System.out.println(asll.has(num6));
		System.out.println(asll.has(new Int64(14333)));
	}
	
	private static void testFirstLastList() {
		AbstractFirstLastLinkedList<Integer> flll = new FirstLastLinkedList<>();
		Integer num1 = new Integer(900);
		Integer num2 = new Integer(256);
		flll.insertFirst(566);
		flll.insertFirst(9);
		flll.display();
		
		flll.insertLast(2323);
		flll.insertLast(1);
		flll.display();
		
		flll.deleteFirst();
		flll.deleteFirst();
		flll.display();
		
		flll.insertFirst(num1);
		flll.insertFirst(num2);
		flll.display();
		
		flll.delete(num1);
		flll.display();
		System.out.println(flll.has(num2));
		System.out.println(flll.has(num1));
	}
	
	private static void testLinkedList() {
		AbstractLinkedList<Integer> ll = new LinkedList<>();
		Integer num1 = new Integer(1000);
		Integer num2 = new Integer(256);
		Integer num3 = new Integer(777);
		ll.insertFirst(343);
		ll.insertFirst(4);
		ll.insertFirst(67);
		ll.insertFirst(696);
		ll.insertFirst(1);
		ll.insertFirst(434);
		
		ll.display();

		ll.deleteFirst();
		ll.deleteFirst();
		ll.deleteFirst();
		
		ll.display();
		
		ll.insertFirst(num1);
		ll.insertFirst(num2);
		ll.insertFirst(num3);
		
		System.out.println(ll.has(num3));
		System.out.println(ll.has(num2));
		System.out.println(ll.has(num1));
		System.out.println(ll.delete(num1));
		System.out.println(ll.delete(num2));
		System.out.println(ll.delete(num3));
		ll.display();
	}

}
