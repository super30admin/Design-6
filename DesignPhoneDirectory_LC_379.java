import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*
Approach- Use HashSet and Queue
in the constructor add the maxNumber elements to queue and set
check() - just check if the number is present in the set or not.
get() - remove the element from queue and same element from set too and return that

release - while releasing the number check if set does not contains the element then add it to set and queue both.

Time Complexity - O(N)
Space Complexity - O(1) for all functions , For constructor - O(N)

Did this code successfully run on Leetcode :  Yes
Any problem you faced while coding this :  No
*/
public class DesignPhoneDirectory_LC_379 {
    HashSet<Integer> hs;
    Queue<Integer> q ;


    public DesignPhoneDirectory_LC_379(int maxNumbers) {
        this.q = new LinkedList<>();
        this.hs = new HashSet<>();
        for(int i = 0; i<maxNumbers; i++) {
            hs.add(i);
            q.add(i);
        }
    }

    public int get() {
        if(hs.isEmpty()) return -1;
        int curr = q.poll();
        hs.remove(curr);
        return curr;
    }

    public boolean check(int number) {
        return hs.contains(number);
    }

    public void release(int number) {
        if(!hs.contains(number)) {
            hs.add(number);
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