package recursion;



public class RecursionProblems {

	public static void main(String[] args) {
		//System.out.println(triangleNumbers(10));
		//System.out.println(factorial(5));
		//Anagrams.solve("edl").forEach(s -> System.out.println(s));
		//hanoiTower(1, 'A', 'B', 'C');
		System.out.println(pow(5, 2));
		
	}
	
	public static int triangleNumbers(int n) {
		if (n == 1) {
			return 1;
		}
		return n + triangleNumbers(n - 1);
	}

	public static int factorial(int n) {
		if (n == 0) {
			return 1;
		}
		return n * factorial(n - 1);
	}
	
	public static void hanoiTower(int disks, char from, char intermediate, char to) {
		if (disks == 1) { // from the top to the bottom
			System.out.println("Disk 1 from "+from+" to "+to);
		} else {
			hanoiTower(disks - 1, from, to, intermediate);
			System.out.println("Disk "+disks+" from "+from+" to "+to);
			hanoiTower(disks - 1, intermediate, from, to);
		}
	}
	
	public static int multiplication(int n, int by) {
		if (n == 0 || by == 0) {
			return 0;
		} 
		
		int absN = Math.abs(n);
		int absBy = Math.abs(by);
		final int answer = (absN > absBy) ? doMultiplication(absN, absBy) : doMultiplication(absBy, absN);
		
		return (n < 0 || by < 0) ? answer - answer - answer : answer;
	}
	
	private static int doMultiplication(int n, int counter) {
		if (counter == 1) {
			return n;
		}
		return n + doMultiplication(n, counter - 1);
	}
	
	public static long pow(long number, long by) {
		if (by == 0) {
			return 1;
		}
		long a = Math.abs(number);
		long b = Math.abs(by);
		long result = power(a, b);
		
		if (by < 0) {
			result = 1 / result;
		}
		return result;
	}
	
	private static long power(long number, long by) {
		long original = number;
		if (by != 1) {
			number = power(number * number, by / 2);
			if (by % 2 != 0) {
				number = original * number;
			}
		}
		return number;
	}
	
	
	
	
	
	
	
	
	
	
}
