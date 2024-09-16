// Time Complexity :O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach

class PhoneDirectory {
    HashSet<Integer> database = new HashSet<>();

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        for(int i = 0; i < maxNumbers; i++){
            database.add(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(database.size() == 0){
            return -1;
        }
        
        int num = database.iterator().next();
        
        database.remove(num);
        
        return num;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return database.contains(number);
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        database.add(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */