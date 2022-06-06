import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Problem1 {
    Queue<Integer> que;
    HashSet<Integer> set;

    // TC : O(n)
    // SC : O(maxNumbers)
    public void PhoneDirectory(int maxNumbers) {
        que = new LinkedList<>();
        set = new HashSet<>();
        for (int i = 0; i < maxNumbers; i++) {
            que.add(i);
        }
    }

    // TC : O(1)
    // SC : 0(1)
    public int get() {
        int result = -1;
        if (!que.isEmpty()) {
            result = que.poll();
            set.add(result);
        }
        return result;
    }

    // TC : O(1)
    // SC : 0(1)
    public boolean check(int number) {
        return !set.contains(number);
    }

    // TC : O(1)
    // SC : 0(1)
    public void release(int number) {
        if (set.contains(number)) {
            set.remove(number);
            que.add(number);
        }
    }
}