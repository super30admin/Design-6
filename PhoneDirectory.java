import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class PhoneDirectory {
    Queue<Integer> queue = new LinkedList<>();
    Set<Integer> set = new HashSet<>();
    public PhoneDirectory(int maxNumbers) {
        for (int i = 0; i < maxNumbers; i++) {
            queue.add(i);
            set.add(i);
        }
    }

    // TC: O(1)
    // SC: O(1)
    public int get() {
        if (queue.isEmpty()) {
            return -1;
        }
        int number = queue.poll();
        set.remove(number);
        return number;
    }

    // TC: O(1)
    // SC: O(1)
    public boolean check(int number) {
        return set.contains(number);
    }

    // TC: O(1)
    // SC: O(1)
    public void release(int number) {
        if (!set.contains(number)) {
            set.add(number);
            queue.add(number);
        }
    }
}
