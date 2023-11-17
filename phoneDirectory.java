// Time Complexity : O(1)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
/*
 * 1. Create a queue and a set. Add all the numbers to the queue and set.
 * 2. get() - poll from queue and remove from set.
 * 3. check() - check if the number is present in the set.
 * 4. release() - add the number to the queue and set.
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class PhoneDirectory {
    Queue<Integer> queue;
    HashSet<Integer> set;
    public PhoneDirectory(int maxNumbers) {
        this.queue = new LinkedList<>();
        this.set = new HashSet<>();
        for(int i=0; i<maxNumbers; i++){
            queue.add(i);
            set.add(i);
        }
    }

    public int get() {
        if(queue.isEmpty())
            return -1;
        int num = queue.poll();
        set.remove(num);
        return num;
    }

    public boolean check(int number) {
        if(!set.contains(number))
            return false;
        return true;
    }

    public void release(int number) {
        if(set.contains(number))
            return;
        set.add(number);
        queue.add(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */