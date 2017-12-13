package perfomance;

import arrays.Array;

public class Searching extends Algorithm<Payload> {

	@Override
	protected Payload computation(Payload input) {
		Array a = (Array) input.getObject();
		a.has((objects.Comparable) input.getArgs()[0]);
		return input;
	}

}
