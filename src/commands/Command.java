package commands;

public interface Command<T> {

	public void execute(T byValue);

	
}
