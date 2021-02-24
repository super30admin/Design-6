// Time Complexity : O(1)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
// save released number in set. if set is empty increase top and return.


class PhoneDirectory {

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    int max;
    int top;
    Set<Integer> free;
    public PhoneDirectory(int maxNumbers) {
        max = maxNumbers;
        free = new HashSet<>();
        top = -1;
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(free.size()>0){
            Iterator<Integer> itr = free.iterator();
            int val = itr.next();
            free.remove(val);
            return val;
        } else if(top<max-1){
            return ++top;
        } else {
            return -1;
        }
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return number > top || free.contains(number);
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if(number<=top) free.add(number);
    }
}