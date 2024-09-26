import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class PhoneDirectory {
    // Using que and hasset to get most optimal result.
    // from list takes time to add number back
    /**
     * Time complexity
     * - get() - o(1)
     * - check() - o(1)
     * - release() -o(1)
     *
     * Space complexity
     * o(n) - n is maxNumbers
     *
     *
     *
     */

    HashSet<Integer> set;
    Queue<Integer> q;

    public PhoneDirectory(int maxNumbers) {
        // iterate through and add all the numbers
        set = new HashSet<>();
        q = new LinkedList<>();
        for (int i = 0; i < maxNumbers; i++) {
            q.add(i);
            set.add(i);
        }
    }

    public int get() {
        // when a number is asked give from the front of q and remove from the hashset.
        // return the number
        if (q.isEmpty()) {
            return -1;
        }
        int number = q.poll();
        set.remove(number);
        return number;
    }

    public boolean check(int number) {
        // for checking query hashset
        if (!q.isEmpty()) {
            return set.contains(number);
        }
        return false;

    }

    public void release(int number) {
        // to release number add it back to the que and hashset
        if (!set.contains(number)) {
            q.add(number);
            set.add(number);
        }
    }
}
