package perfomance;

import binarySearchTree.BinarySearchTree;
import objects.Person;

public class Trees extends Algorithm<BinarySearchTree<Person, String>> {

	private final int X;
	private Person[] persons;
	
	public Trees(int x) {
		this.X = x;
	}
	
	@Override
	protected BinarySearchTree<Person, String> computation(BinarySearchTree<Person, String> input) {
		
		for (int i = 0; i < X; i++) {
			input.insert(persons[i], "SomeVal" + i);
		}
		
		for (int i = 0; i < X; i++) {
			input.find(persons[i]);
		}
		
		for (int i = 0; i < X / 2; i++) {
			input.delete(persons[i]);
		}
		
		return input;
	}

	@Override
	public void postTime(BinarySearchTree<Person, String> input) {
		persons = new Person[X];
		for (int i = 0; i < X; i++) {
			int id = (int) (Math.random()*(1000000));
			Person person = new Person(id, "Some Name"+id, (int) (Math.random()*(100)));
			persons[i] = person;
		}
	}
}
