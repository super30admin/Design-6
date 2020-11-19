// Time Complexiity : init : O(N) get : O(1) release : O(1) check : O(1)
// Space Complexity : O(1)
// Passed Leetcode 

class PhoneDirectory {

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    
    HashSet<Integer> directory = new HashSet<>();
    Iterator<Integer> iter;
    public PhoneDirectory(int maxNumbers) {
        for (int i = 0; i < maxNumbers; i++)
            directory.add(i);
        
        iter = directory.iterator();
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        int val = -1;
        if (!directory.isEmpty()) {
            val = iter.next().intValue();
            
            directory.remove(val);
            iter = directory.iterator();
        }
        
        return val;
    }
    
    /** Check if a number is available or notintValue(). */
    public boolean check(int number) {
        return directory.contains(number);
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        directory.add(number);
        iter = directory.iterator();
        
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */