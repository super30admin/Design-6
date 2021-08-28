// Time Complexity : get: O(1), release: O(1), check: O(1), n = max number
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes

class PhoneDirectory {

    boolean directory [];
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    Queue<Integer> queue;
    Set<Integer> usedSet;
    public PhoneDirectory(int maxNumbers) {
        queue = new LinkedList<>();
        usedSet = new hashSet<>();
        int max = maxNumbers-1;
        for(int i=0; i<maxNumbers; i++)
            queue.add(i);
    }

    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(!queue.isEmpty()) {
            int curr = queue.poll(); //not available
            usedSet.add(curr);
            return curr;
        }
        return -1;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        if(number<0 || number>=max)
            return false;
        if(!usedSet.contains(number))
            return true;
        return false;
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if(usedSet.contains(number)) {
            usedSet.remove(number);
            queue.add(number);
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