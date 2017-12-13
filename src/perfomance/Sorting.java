package perfomance;

import arrays.Array;

public class Sorting extends Algorithm<Array> {

	@Override
	protected Array computation(Array input) {
		input.sort();
		return input;
	}

}
