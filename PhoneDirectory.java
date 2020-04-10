class PhoneDirectory {

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    Set<Integer> set;
    Queue<Integer> q;
    public PhoneDirectory(int maxNumbers) {
        set = new HashSet<>();
        q = new LinkedList<>();
        
        for(int i = 0; i< maxNumbers; i++)q.offer(i);
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        Integer number = q.poll();
        if(number == null)return -1;
        set.add(number);
        return number;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        boolean chk = !set.contains(number);
        return chk;
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if(set.remove(number))q.offer(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
