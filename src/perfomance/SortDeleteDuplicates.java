package perfomance;

import arrays.Array;

public class SortDeleteDuplicates extends Algorithm<Array> {

	@Override
	protected Array computation(Array input) {
		input.sortAndDeleteDuplicates();
		return input;
	}

}
