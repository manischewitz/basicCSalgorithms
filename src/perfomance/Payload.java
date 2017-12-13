package perfomance;

public class Payload {

	private final Object object;
	private final Object[] args;
	
	public Payload(Object object, Object[] args) {
		super();
		this.object = object;
		this.args = args;
	}

	public Object getObject() {
		return object;
	}

	public Object[] getArgs() {
		return args;
	}
	
}
