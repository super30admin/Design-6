// Time complexity is O(1) as every operation is O(1)
// Space complexity is O(N)
// This solution is submitted on leetode

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BigN124PhoneDirectory {
	class PhoneDirectory {
		HashSet<Integer> set;
		Queue<Integer> q;

		/**
		 * Initialize your data structure here
		 * 
		 * @param maxNumbers - The maximum numbers that can be stored in the phone
		 *                   directory.
		 */
		public PhoneDirectory(int maxNumbers) {
			q = new LinkedList<>();
			set = new HashSet<>();
			for (int i = 0; i < maxNumbers; i++) {
				set.add(i);
				q.offer(i);
			}
		}

		/**
		 * Provide a number which is not assigned to anyone.
		 * 
		 * @return - Return an available number. Return -1 if none is available.
		 */
		public int get() {
			if (!q.isEmpty()) {
				int res = q.remove();
				set.remove(res);
				return res;
			}
			return -1;
		}

		/** Check if a number is available or not. */
		public boolean check(int number) {
			return set.contains(number);
		}

		/** Recycle or release a number. */
		public void release(int number) {
			if (!set.contains(number)) {
				q.offer(number);
				set.add(number);
			}
		}
	}

	/**
	 * Your PhoneDirectory object will be instantiated and called as such:
	 * PhoneDirectory obj = new PhoneDirectory(maxNumbers); int param_1 = obj.get();
	 * boolean param_2 = obj.check(number); obj.release(number);
	 */
}