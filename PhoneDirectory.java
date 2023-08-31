// Time Complexity : O(1) for all user operations
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class PhoneDirectory {

    Queue<Integer> unusedNumbers = new LinkedList<>();
    HashSet<Integer> usedNumbers = new HashSet<>();

    public PhoneDirectory(int maxNumbers) {
        for(int i=0; i<maxNumbers; i++)
        {
            unusedNumbers.add(i);
        }
    }

    public int get() {  // TC : O(1)
        if(unusedNumbers.isEmpty()) return -1;
        int number = unusedNumbers.poll();
        usedNumbers.add(number);
        return number;
    }

    public boolean check(int number) { //TC : O(n)
        boolean used = usedNumbers.contains(number);
        return !used;
    }

    public void release(int number) { //TC : O(n)
        if(usedNumbers.contains(number))
        {
            usedNumbers.remove(number);
            unusedNumbers.add(number);
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
