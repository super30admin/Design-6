// Approach: Use A Queue and a Hashset to store the numbers
// Time: O(1) for all the methods
// Space: O(n)

import java.util.*;

class DesignPhoneDirectory {
    private final Queue<Integer> q;
    private final Set<Integer> set;
    public DesignPhoneDirectory(int maxNumbers) {
        this.q = new LinkedList<>();
        this.set = new HashSet<>();
        for (int i = 0; i<maxNumbers; i++) {
            q.add(i);
            set.add(i);
        }
    }

    public int get() {
        if (q.isEmpty()) return -1;
        int result = q.poll();
        set.remove(result);
        return result;
    }

    public boolean check(int number) {
        return set.contains(number);
    }

    public void release(int number) {
        if (!set.contains(number)) {
            q.add(number);
            set.add(number);
        }
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */