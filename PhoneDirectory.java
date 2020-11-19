
// Time Complexity : O(n) for get, O(1) for check and release
// Space Complexity :O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
// keep a boolean assigned array to keep track of used nums, find the one which isn't assigned from the start

class PhoneDirectory {

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    boolean[] assigned;
    
    public PhoneDirectory(int maxNumbers) {
        assigned = new boolean[maxNumbers];
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        for(int i=0; i<assigned.length; i++){
            if(assigned[i]==false){
                assigned[i] = true;
                return i;
            }
        }
        return -1;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !assigned[number];
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        assigned[number] = false;
    }
}