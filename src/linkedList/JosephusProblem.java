package linkedList;

public class JosephusProblem {

	public static void main(String[] args) {
		System.out.println(solve(20,7,2));

	}
	
	private static int solve(Integer numOfPeople, Integer shouldStayAlive, Integer startPerson) {
		if (shouldStayAlive > numOfPeople) {
			throw new IllegalArgumentException();
		}
		AbstractCycleLinkedList<Integer> cll = getList(numOfPeople, startPerson);
		cll.display();
		int step = 2;
		
		for (int i = 0; i < numOfPeople - 1; i++) {
			for (int j = 1; j < step - 1; j++) {
				cll.step();
			}
			if (cll.getNext().equals(shouldStayAlive)) {
				step++;
				i = 0;
				cll = getList(numOfPeople, startPerson);
			} else {
				cll.deleteAfterCurrent();
				cll.step();
			}
		}
		return step;
	}

	private static AbstractCycleLinkedList<Integer> getList(int n, int startWith) {
		AbstractCycleLinkedList<Integer> cll = new CycleLinkedList<>();
		
		for (int i = 1; i <= n; i++) {
			cll.insert(i);
		}
		while (!cll.getCurrent().equals(startWith)) {
			cll.step();
		}
		
		return cll;
	}
	
}
