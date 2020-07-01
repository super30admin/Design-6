class Directory {

    /**
     * Time complexity: O(1)
     * Space complexity: O(N) N is maxNumbers
     * 
     * Approach:
     * 1. Store all numbers in set.
     * 2. To keep track of next number to be removed, we can use either queue or iterator.
     */

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    Set<Integer> set;
    Queue<Integer> q;
    public PhoneDirectory(int maxNumbers) {
        set = new HashSet<>();
        q = new LinkedList<>();
        for(int i=0; i<maxNumbers; i++) {
            set.add(i);
            q.add(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(!set.isEmpty()) {
            // int n = set.iterator().next();
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
        if(!set.contains(number)){
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