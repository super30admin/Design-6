import java.util.*;
// Time Complexity : O(1) where n is maxNumbers
// Space Complexity : O(n) where n is maxNumbers
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
class PhoneDirectory {
    Set<Integer> set;
    Queue<Integer> q;

    public PhoneDirectory(int maxNumbers) {
        set = new HashSet();
        q = new LinkedList();
        for (int i = 0; i < maxNumbers; i++) {
            set.add(i);
            q.add(i);
        }
    }

    public int get() {
        if (q.isEmpty())
            return -1;
        int val = q.poll();
        set.remove(val);
        return val;
    }

    public boolean check(int number) {
        return set.contains(number);
    }

    public void release(int number) {
        if (set.contains(number))
            return;
        q.add(number);
        set.add(number);
    }
}