// Time Complexity : O(1) for all operations - get(), check(), and release()
// Space Complexity : O(n), where n = maxNumbers (for queue and HashSet storage)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Approach :
//   - Use a Queue to keep track of available phone numbers.
//   - Use a HashSet to store assigned numbers for constant-time lookup.
//   - 'get()' retrieves an available number from the queue and marks it used.
//   - 'check(number)' returns true if the number is available (i.e., not in the used set).
//   - 'release(number)' adds the number back to the queue only if it is currently marked as used.

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class DesignPhoneDirectory {
    Queue<Integer> q;
    HashSet<Integer> usedSet;

    public PhoneDirectory(int maxNumbers) {
        this.q = new LinkedList<>();
        this.usedSet = new HashSet<>();
        for(int i = 0; i < maxNumbers; i++) {
            q.add(i);
        }
    }

    // TC - O(1)
    public int get() {
        if(q.isEmpty()) {
            return -1;
        }

        int num = q.poll();
        usedSet.add(num);
        return num;
    }

    // TC - O(1)
    public boolean check(int number) {
        return !usedSet.contains(number);
    }

    // TC - O(1)
    public void release(int number) {
        if(!usedSet.contains(number)) {
            return;
        }
        usedSet.remove(number);
        q.add(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */

