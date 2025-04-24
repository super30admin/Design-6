import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Time Complexity: get, check, release - O(1)
 * Space Complexity: O(N), where N is max numbers
 * Did this code successfully run on Leetcode: Yes
 * Any problem you faced while coding this: None

 * Approach:
 * Using queue to get the first in
 * Using Set for constant access and inserts
 */

public class PhoneDirectory {
    Set<Integer> availableNumbers;
    Queue<Integer> q;
    public PhoneDirectory(int maxNumbers) {
        availableNumbers = new HashSet<>();
        q = new LinkedList<>();
        for (int i = 0; i < maxNumbers; i++) {
            availableNumbers.add(i);
            q.add(i);
        }
    }
    
    public int get() {
        if (q.isEmpty() || availableNumbers.isEmpty()) {
            return -1;
        }
        int value = q.poll();
        availableNumbers.remove(value);
        return value;
    }
    
    public boolean check(int number) {
        return availableNumbers.contains(number);
    }
    
    public void release(int number) {
        if (availableNumbers.contains(number)) {
            return;
        }
        availableNumbers.add(number);
        q.add(number);
    }
    public static void main(String[] args) {
        // Your code here
        PhoneDirectory obj = new PhoneDirectory(5);
        int param_1 = obj.get();
        boolean param_2 = obj.check(3);
        obj.release(1);
    }
}