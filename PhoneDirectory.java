//Time Complexity: O(1)
//Space Complexity: o(n)
//Add numbers in the queue when initializing the phone directory and a set to maintain numbers used and to remove
//Keep removing from queue and add it to set to maintain if its used and reverse when freeing it 
class PhoneDirectory {
    Set<Integer> s;
    Queue<Integer> q;

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        s = new HashSet<>();
        q = new LinkedList<>();
        for(int i = 0; i < maxNumbers; i++)
        {
            q.add(i);
        }
        
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(!q.isEmpty()){
            int num = q.poll();
            s.add(num);
            return num;
        }
        return -1;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        if(s.contains(number))
        {
            return false;
        }
        return true;
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if(s.contains(number))
        {
            q.add(number);
            s.remove(number);
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