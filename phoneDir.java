// Time Complexity :O(1)all methods
// Space Complexity :O(1) all methods
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :No
class PhoneDirectory {
    private HashSet<Integer> set;
    private Queue<Integer> q;

    public PhoneDirectory(int maxNumbers) {
        // initialize set
        set = new HashSet<>();
        // initialize queue
        q = new LinkedList<>();
        for (int i = 0; i < maxNumbers; i++) {
            set.add(i);
            q.add(i);
        }
    }

    public int get() {

        // in our queue, we are storing unassigned numbers
        if (!q.isEmpty()) {// we'll remove element from queue and set both
            int el = q.poll();
            set.remove(el);
            return el;

        }
        return -1;
    }

    public boolean check(int number) {
        // in set we have unassigned numbers
        return (set.contains(number));

    }

    public void release(int number) {
        // first we'll check if element is already unassigned
        if (!set.contains(number)) {
            // if not, we'll make it unassigned
            set.add(number);
            q.add(number);
        }

    }
}
