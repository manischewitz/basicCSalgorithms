package linkedList;

public class ForwardLink<T> implements Link<T> {

	private final T payload;
	private Link<T> next;
	
	public ForwardLink(T payload) {
		this.payload = payload;
	}

	public Link<T> getNext() {
		return next;
	}

	public void setNext(Link<T> next) {
		this.next = next;
	}

	public T getPayload() {
		return payload;
	}

	@Override
	public String toString() {
		return "Link [payload=" + payload + "]";
	}

	@Override
	public Link<T> getPrevious() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setPrevious(Link<T> next) {
		throw new UnsupportedOperationException();
	}

}
