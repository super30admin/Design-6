class PhoneDirectory {
    
    Set<Integer> available = new HashSet<Integer>();

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
    
        for(int i=0;i<maxNumbers;i++){
            available.add(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        int ans = -1;
        for(int i:available){
            ans = i;
            break;
        }
        available.remove(ans);
        return ans;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return available.contains(number);
        
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        available.add(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
