package perfomance;

import java.util.Date;



public abstract class Algorithm<T> {

	public final T startExecuting(T input) {
		
		postTime(input);
		final Date start = startTime();
		T results = computation(input);
		endTime(start);
		
		return results;
	}
	
	protected final Date startTime() {
		return new Date();
	}
	
	protected void postTime(T input) { }
	
	protected abstract T computation(T input);
	
	protected final Date endTime(Date start) {
		final Date end = new Date();
		final long overall = end.getTime() - start.getTime();
		System.out.println("Mills passed "+ overall);
		
		return end;
		
	}
	
}
