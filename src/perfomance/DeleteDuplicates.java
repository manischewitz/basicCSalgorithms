package perfomance;

import arrays.Array;

public class DeleteDuplicates extends Algorithm<Array> {

	@Override
	protected Array computation(Array input) {
		input.deleteDuplicates();
		return input;
	}



}
