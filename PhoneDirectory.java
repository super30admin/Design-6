// Time Complexity : For all the three opereation {get, check and release}: O(1)
// Space Complexity : O(n) --> where n is equal to the input maxNumbers
// Did this code successfully run on Leetcode (379): Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

class PhoneDirectory {
    HashSet<Integer> set;
    Queue<Integer> q;
    int max;
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        this.set = new HashSet<>();
        this.q = new LinkedList<>();
        this.max = maxNumbers;
        for (int i = 0; i < maxNumbers; i++) {
            set.add(i); q.add(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (!q.isEmpty()) {
            int n = q.poll();
            set.remove(n);
            return n;
        }
        return -1;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return set.contains(number);
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if (!set.contains(number) && number < max) {
            q.add(number);
            set.add(number);            
        }
    }
}