package perfomance;

import hashTables.HashTable;
import objects.Person;

public class HashTables extends Algorithm<hashTables.HashTable<Person, String>> {

	private final int X;
	private Person[] persons;
	
	public HashTables(int x) {
		this.X = x;
	}

	@Override
	protected HashTable<Person, String> computation(HashTable<Person, String> input) {
		
		for (int i = 0; i < X; i++) {
			input.put(persons[i], "SomeVal" + i);
		}
		
		for (int i = 0; i < X; i++) {
			input.get(persons[i]);
		}
		
		for (int i = 0; i < X / 2; i++) {
			input.remove(persons[i]);
		}
		
		return input;
	}
	
	@Override
	public void postTime(HashTable<Person, String> input) {
		persons = new Person[X];
		for (int i = 0; i < X; i++) {
			int id = (int) (Math.random()*(1000000));
			Person person = new Person(id, "Some Name"+id, (int) (Math.random()*(100)));
			persons[i] = person;
		}
	}

}
