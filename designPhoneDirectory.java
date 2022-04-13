// Time Complexity : O(1)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No


// Your code here along with comments explaining your approach
// We use a set and a Queue to store the elements.
// A set will give you constant time in fetching the element and a queue will be useful for releasing and getting the element
public class PhoneDirectory {
    private HashSet<Integer> set;
    private Queue<Integer> q;

    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        q = new LinkedList<Integer>();
        set = new HashSet<Integer>();
        for (int i = 0; i < maxNumbers; i ++) {
            q.add(i);
            set.add(i);
        }
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (q.isEmpty()) {
            return -1;
        }
        int number = q.poll();
        set.remove(number);
        return number;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return set.contains(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if (set.contains(number)) return;
        q.remove(number);
        set.add(number);
    }
}