package recursion;

import java.util.HashSet;
import java.util.Set;

public class Combinations {

	private static final Combinations INSTANCE = new Combinations();
	private Set<String> set;
	private int number;
	
	private Combinations () {}
	
	public static Combinations getInstance() {
		return INSTANCE;
	}
	
	public Set<String> combinations(String set, int number) {
		this.set = new HashSet<>();
		this.number = number;
		rearrange("", set);
	    return this.set;
	}
	
	public void rearrange(String prefix, String str){
		if (prefix.length() == this.number) {
			set.add(prefix);
		}
	    for (char ch : str.toCharArray()) {
	    	 	rearrange(prefix + ch, str.replaceFirst(ch + "", ""));
		}
	}
	 
	public static void main(String[] args) {
		Combinations.INSTANCE.combinations("ABCD", 2)
		.forEach(s -> System.out.println(s));
		
	}
}
