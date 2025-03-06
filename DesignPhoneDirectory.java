import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// TC: O(1) there is no traversal involved
// SC: O(n) for using queue and set

// Runs successfully on leetcode.
// Two data structure makes it easier for retrieval and present operation
class PhoneDirectory {
    Set<Integer> set;
    Queue<Integer> queue;

    public PhoneDirectory(int maxNumbers) {
        set = new HashSet<>();
        queue = new LinkedList<>();
        for (int i = 0; i < maxNumbers; i++) {
            set.add(i);
            queue.offer(i);
        }
    }

    public int get() {
        if (!queue.isEmpty()) {
            int poll = queue.poll();
            set.remove(poll);
            return poll;
        }
        return -1;
    }

    public boolean check(int number) {
        return set.contains(number);
    }

    public void release(int number) {
        if (!set.contains(number)) {
            queue.offer(number);
            set.add(number);
        }
    }
}

public class DesignPhoneDirectory {

    public static void main(String[] args) {
        PhoneDirectory obj = new PhoneDirectory(3);
        System.out.println(obj.get()); // 0
        System.out.println(obj.get()); // 1
        System.out.println(obj.check(2)); // true
        System.out.println(obj.get()); // 2
        System.out.println(obj.check(2)); // false
        obj.release(2); // null
        System.out.println(obj.check(2)); // , true
    }
}