import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
//Time Complexity : O(1)
//Space Complexity : O(N)
//Did this code successfully run on Leetcode : Yes
//Any problem you faced while coding this : No

/**
 * Push all slots to a map and queue. To get a slot, pop from queue and remove
 * it in map. To check if a number is present, check if map contains that key.
 * To release, add the element to the map and queue.
 *
 */
class PhoneDirectory {
	Map<Integer, Boolean> phone = new HashMap<>();
	Queue<Integer> queue = new LinkedList<>();

	public PhoneDirectory(int maxNumbers) {
		for (int i = 0; i < maxNumbers; i++) {
			phone.put(i, true);
			queue.offer(i);
		}
	}

	public int get() {
		if (phone.isEmpty())
			return -1;
		int val = queue.poll();
		phone.remove(val);
		return val;
	}

	public boolean check(int number) {
		return phone.containsKey(number);
	}

	public void release(int number) {
		if (phone.containsKey(number))
			return;
		phone.put(number, true);
		queue.offer(number);
	}
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers); int param_1 = obj.get();
 * boolean param_2 = obj.check(number); obj.release(number);
 */
