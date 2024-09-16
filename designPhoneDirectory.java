//Time Complexity : O(1)
//Space Complexity : O(n)
//Did this code successfully run on Leetcode :yes
//Any problem you faced while coding this : no
class PhoneDirectory {

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    HashSet<Integer> set;
    Queue<Integer> q1;
    int maxNumbers;
    
    public PhoneDirectory(int maxNumbers) {
        
        set = new HashSet<>();
        q1 = new LinkedList<>();
        this.maxNumbers = maxNumbers;
        
        for(int i = 0; i < maxNumbers; i ++){
            
            q1.add(i);
        }
        
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        
        if(!q1.isEmpty()){
            
            int temp = q1.poll();
            set.add(temp);
            return temp;
        }
        
        return -1;
        
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        
        if(!set.contains(number)){
            
            return true;
        }
        
        return false;
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        
        if(set.contains(number)){
            q1.add(number);
        
        set.remove(number);
            
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