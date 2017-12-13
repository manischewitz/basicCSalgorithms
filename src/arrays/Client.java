package arrays;

import objects.Int64;
import objects.Person;

public class Client {

	public static void main(String[] args) {
		
	}
	
	private static void testPerson(Array array) {
		
		Person alex = new Person(47, "Alex", 23);
		
		array.push(new Person(32, "John", 22));
		array.push(new Person(4, "Anna", 20));
		array.push(alex);
		array.push(new Person(45, "Ivan", 30));
		array.push(new Person(765, "Chelsea", 33));
		array.push(new Person(11, "Ellis", 900));
		
		array.display();
		
		if (array.has(alex) > 0) {
			System.out.println("Found!");
		}
		
		array.delete(alex);
		
		array.display();
		
		System.out.println(array.removeMax().toString());
		
		array.display();
		
		Array arr = new OrderedArray(33);
		arr.push(new Person(1000, "Erich", 21));
		arr.push(new Person(73, "Elena", 45));
		arr.push(new Person(3, "William", 76));
		arr.push(new Person(73, "Elena", 45));
		
		Array merged = array.merge(arr);
		merged.display();
		
		merged.deleteDuplicates();
		merged.display();
	}
	
	private static void testInts64(Array array) {
		
		array.push(new Int64(32));
		array.push(new Int64(43));
		array.push(new Int64(464));
		array.push(new Int64(2));
		array.push(new Int64(123));
		array.push(new Int64(88));
		array.push(new Int64(40));
		array.push(new Int64(5));
		array.push(new Int64(788));
		array.push(new Int64(999));
		
		array.display();
		
		if (array.has(new Int64(88)) > 0) {
			System.out.println("Found!");
		}
		
		array.delete(new Int64(00));
		array.delete(new Int64(788));
		array.delete(new Int64(2));
		
		array.display();
		
		System.out.println(array.removeMax().toString());
		
		array.display();
		
		Array arr = new UnorderedArray(33);
		arr.push(new Int64(89));
		arr.push(new Int64(2366));
		arr.push(new Int64(765));
		
		Array merged = array.merge(arr, 111);
		merged.display();
		
		merged.push(new Int64(2366));
		merged.push(new Int64(33));
		merged.push(new Int64(2366));
		
		merged.display();
		
		merged.deleteDuplicates();
		merged.display();
		
	}

}
