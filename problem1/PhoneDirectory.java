// Time Complexity : Get: O(1), Release: O(1), Check: O(1), n -> Max number
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
package problem1;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class PhoneDirectory {
	Set<Integer> numbers;
	Queue<Integer> queue;
	int maxNum;

	/**
	 * Initialize your data structure here
	 * 
	 * @param maxNumbers - The maximum numbers that can be stored in the phone
	 *                   directory.
	 */
	public PhoneDirectory(int maxNumbers) {
		queue = new LinkedList<Integer>();
		for (int i = 0; i < maxNumbers; i++) {
			queue.add(i);
		}
		numbers = new HashSet<Integer>();
		maxNum = maxNumbers;
	}

	/**
	 * Provide a number which is not assigned to anyone.
	 * 
	 * @return - Return an available number. Return -1 if none is available.
	 */
	public int get() {
		if (!queue.isEmpty()) {
			int num = queue.poll();
			numbers.add(num);
			return num;
		}
		return -1;
	}

	/** Check if a number is available or not. */
	public boolean check(int number) {
		if (number < 0 || number >= maxNum) {
			return false;
		}
		return !numbers.contains(number);
	}

	/** Recycle or release a number. */
	public void release(int number) {
		if (numbers.contains(number)) {
			numbers.remove(number);
			queue.add(number);
		}
	}

	public static void main(String[] args) {
		PhoneDirectory obj = new PhoneDirectory(5);
		System.out.println("get(): " + obj.get());
		System.out.println("get(): " + obj.get());
		System.out.println("get(): " + obj.get());
		System.out.println("check(4): " + obj.check(4));
		System.out.println("get(): " + obj.get());
		System.out.println("check(3): " + obj.check(3));
		System.out.println("release(3)");
		obj.release(3);
		System.out.println("check(3): " + obj.check(3));
	}

}
