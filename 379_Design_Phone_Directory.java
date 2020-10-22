    /*  Explanation
    # Leetcode problem link : https://leetcode.com/problems/design-phone-directory/
    Time Complexity for operators : o(n) .. n = maxNumbers
    Extra Space Complexity for operators : o(n) .. hashset
    Did this code successfully run on Leetcode : NA
    Any problem you faced while coding this : No
# Your code here along with comments explaining your approach
        # Basic approach : 
        # Optimized approach: 
                              
            # 1. 
                    A) 
    */ 
class PhoneDirectory {

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    HashSet<Integer> hs;
    public PhoneDirectory(int maxNumbers) {
        hs= new HashSet<>();
        for(int x=0;x<maxNumbers;x++){
            hs.add(x);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(hs.size() == 0)
            return -1;
        int temp = hs.iterator().next();
        hs.remove(temp);
        return temp;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return hs.contains(number);
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        hs.add(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */