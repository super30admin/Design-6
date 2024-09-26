class PhoneDirectory {

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    Queue<Integer> q;
    HashSet<Integer> set;
    public PhoneDirectory(int maxNumbers) {
        q = new LinkedList<>();
        set = new HashSet<>();
        for(int i=0; i<maxNumbers; i++) {
            q.add(i);
            set.add(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    //time o(1)
    public int get() {
        if(q.isEmpty()) {
            return -1;
        }
        int num = q.poll();
        set.remove(num);
        return num;
    }
    
    //time o(1)
    /** Check if a number is available or not. */
    public boolean check(int number) {
        if(set.contains(number)) {
            return true;
        }
        return false;
    }
    
    //time o(1)
    /** Recycle or release a number. */
    public void release(int number) {
        if(set.contains(number))
            return;
        set.add(number);
        q.add(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */