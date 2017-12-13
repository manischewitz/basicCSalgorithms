package linkedList;

public class TwoWayLink<T> implements Link<T> {

	private final T payload;
	private Link<T> next;
	private Link<T> previous;
	
	public TwoWayLink (T payload) {
		this.payload = payload;
	}

	public Link<T> getNext() {
		return next;
	}

	public void setNext(Link<T> next) {
		this.next = next;
	}

	public Link<T> getPrevious() {
		return previous;
	}

	public void setPrevious(Link<T> previous) {
		this.previous = previous;
	}

	public T getPayload() {
		return payload;
	}

	@Override
	public String toString() {
		return "TwoWayLink [payload=" + payload + "]";
	}
	
}
