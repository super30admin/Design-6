package week10.day3;

import java.util.HashSet;
import java.util.Stack;

public class PhoneDirectory {
	HashSet<Integer> set;
	Stack<Integer> stack;

	public PhoneDirectory(int maxNumbers) {
		this.set = new HashSet<>();
		stack = new Stack<>();
		// intialize
		for (int i = 0; i < maxNumbers; i++) {
			set.add(i);
			stack.push(i);
		}
	}

	public int get() {
		if (stack.isEmpty())
			return -1;
		int el = (int) stack.pop();
		set.remove(el);
		return el;
	}

	public boolean check(int number) {
		return set.contains(number);

	}

	public void release(int number) {
		if (set.contains(number)) // if available in set already, no need to release
			return;
		set.add(number);
		stack.push(number);
	}

	public static void main(String[] args) {
		PhoneDirectory obj = new PhoneDirectory(3);
		int param_1 = obj.get();
		boolean param_2 = obj.check(2);
		obj.release(2);
		String s = "sat";
		System.out.println(s.length());

	}

}
