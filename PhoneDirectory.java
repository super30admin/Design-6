class PhoneDirectory {

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    Set<Integer> set;
    Queue<Integer> q;
    
    public PhoneDirectory(int maxNumbers) {
        q = new LinkedList<>();
        set = new HashSet<>();
        for(int i=0; i<maxNumbers; i++){
            q.add(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(q.size()==0)return -1;
        int num = q.poll();
        set.add(num);
        return num;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
         if(set.contains(number)){
            return false;
        }
        return true;
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if(set.contains(number)){
            set.remove(number);
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


//Time complexity : get->O(1), check->O(1), release->O(1)
//Space complexity : O(N) N is maxNumbers, extra space for queue and set
