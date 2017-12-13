package stack;

public class Test {

	public static void main(String...strings) {
		//bracketChecker("ada()adawd{adaw}[10]");
		//bracketChecker("ada()adawdadaw}[10]");
		
		final AbstractStack<Integer> stack = new ArrayStack<>(5);
		for (int i = 0; i <= 150; i+= 10) {
			stack.push(i);
		}
		
		stack.display();
		System.out.println(stack.peek() + "\n");
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		stack.display();
	}
	
	public static final boolean bracketChecker(String input) {
		final int length = input.length();
		final AbstractStack<Character> stack = new ArrayStack<>(length);
		boolean legal = true;
		
		for (int i = 0; i < length; i++) {
			final char c = input.charAt(i);
			
			switch (c) {
				case '(':
				case '[':
				case '{': {
					stack.push(c);
					break;
				}
				case ')':
				case ']':
				case '}': {
					if (!stack.isEmpty()) {
						char bracket = stack.pop();
						if ( (bracket!='}' && c == '{') || (bracket!=')' && c=='(') || (bracket!='[' && c==']'))  {
							System.out.println("Error: "+c+" at "+i);
							legal = false;
						} 
					} else {
						System.out.println("Error: "+c+" at "+i);
						legal = false;
					}
					break;
				}
				default: break;
			}
		}
		
		if (!stack.isEmpty()) {
			System.out.println("Error: missing right delimiter.");
			legal = false;
		}
		
		return legal;
	}
}
