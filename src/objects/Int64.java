package objects;

public class Int64 extends Comparable implements java.lang.Comparable<Int64> {

	private long value;

	public Int64(long value) {
		this.value = value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public long getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "Int64 [value=" + value + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (value ^ (value >>> 32));
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
		Int64 other = (Int64) obj;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public int compareTo(Int64 o) {
		Int64 other = (Int64) o;
		if (this.equals(o)) {
			return 0;
		} else if (other.getValue() < value) {
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public int compareTo(Comparable value) {
		return compareTo((Int64) value);
	}
	
}
