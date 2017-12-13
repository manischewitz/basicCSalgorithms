package objects;

public class Person extends Comparable implements java.lang.Comparable<Person> {

	private long id;
	private String name;
	private int age;
	
	public Person(long id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	public int compareTo(Comparable obj) {
		Person other = (Person) obj;
		if (this.equals(obj)) {
			return 0;
		} else if (other.getId() > id) {
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getId() {
		return id;
	}

	@Override
	public int compareTo(Person o) {
		return compareTo((Comparable) o);
	}

}
