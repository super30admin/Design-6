package Design6;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class DesignPhoneDirectory {

    Queue<Integer> q;
    HashSet<Integer> set;
    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public DesignPhoneDirectory(int maxNumbers) {
        q = new LinkedList<>();
        set = new HashSet<>();
        for(int i = 0; i < maxNumbers ; i++) {
            set.add(i);
            q.add(i);
        }
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(q.isEmpty()) return -1;
        int result = q.poll();
        set.remove(result);
        return result;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        //O(1)
        return set.contains(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if(set.contains(number)) return;
        set.add(number);
        q.add(number);
    }

    /**
     * Your PhoneDirectory object will be instantiated and called as such:
     * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
     * int param_1 = obj.get();
     * boolean param_2 = obj.check(number);
     * obj.release(number);
     */

    public static void main(String[] args) {

    }
}
