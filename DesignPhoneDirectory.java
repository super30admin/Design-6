import java.util.*;

class PhoneDirectory {

    HashSet<Integer> set;
    Queue<Integer> q;

    public PhoneDirectory(int maxNumbers) {
        set = new HashSet<>();
        q = new LinkedList<>();

        for (int i = 0; i < maxNumbers; i++) {
            set.add(i);
            q.add(i);
        }
    }

    //Timecomplexity : O(1)
    //SpaceComplexity : O(1)
    public int get() {

        if (set.isEmpty()) return -1;
        int val = q.poll();
        set.remove(val);
        return val;
    }

    //Timecomplexity : O(1)
    //SpaceComplexity :  O(1)
    public boolean check(int number) {
        if (set.contains(number)) return true;
        return false;
    }

    //Timecomplexity :  O(1)
    //SpaceComplexity : O(1)
    public void release(int number) {

        if (!set.contains(number)) {
            set.add(number);
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