package linkedList;

public interface Link<T> {

	public abstract Link<T> getNext();
	
	public abstract void setNext(Link<T> next);
	
	public Link<T> getPrevious();

	public void setPrevious(Link<T> next);
	
	public abstract T getPayload();
}
