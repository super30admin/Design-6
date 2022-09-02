import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//Time Complexity: O(1)
//Space Complexity: O(n)

class PhoneDirectory {
    Queue<Integer> q;
    HashSet<Integer> set;

    public PhoneDirectory(int maxNumbers) {
        q = new LinkedList<>();
        set = new HashSet<>();
        for (int i = 0; i < maxNumbers; i++) {
            q.add(i);
        }

    }

    public int get() {
        if (q.isEmpty())
            return -1;
        int assigned = q.poll();
        set.add(assigned);
        return assigned;

    }

    public boolean check(int number) {
        return !set.contains(number);

    }

    public void release(int number) {
        if (set.contains(number)) {
            set.remove(number);
            q.add(number);
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