package objects;

public abstract class Comparable {

	public boolean greaterThan(Comparable value) {
		return (compareTo(value) == 1);
	}
	
	public boolean lessThan(Comparable value) {
		return (compareTo(value) == -1);
	}
	
	public boolean equalsWith(Comparable value) {
		return (compareTo(value) == 0);
	}
	
	public abstract int compareTo(Comparable value);
	
}
