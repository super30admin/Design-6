// Time Complexity : O(1)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Approach

// We design this using Hashset and queue
// We use the set and queue to store unused numbers

class PhoneDirectory {
    HashSet<Integer> set;
    Queue<Integer> q;

    public PhoneDirectory(int maxNumbers) {
        set = new HashSet<>();
        q = new LinkedList<>();
        for (int i = 0; i < maxNumbers; i++) {
            q.add(i);
            set.add(i);
        }
    }

    public int get() {
        if (q.isEmpty())
            return -1;
        int number = q.poll();
        set.remove(number);
        return number;
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