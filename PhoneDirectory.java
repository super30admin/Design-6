// Time Complexity : O(1) all functions
// Space Complexity : O(n) for maintaining n numbers in a set
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None
/* Algorithm: Put the numbers in a hashset. When a number is called in get(), start an iterator on the set, and return the first number it is pointing to.
Remove the number from the set since the number has been returned. When check is called, simply check if the set contains the number. If release() is
called, put the number back into the pool.
*/
class PhoneDirectory {
    HashSet<Integer> store;
    Iterator itr;
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        store = new HashSet<>();
        for(int i = 0; i < maxNumbers; i++){                                                            // Add all the numbers in the set
            store.add(i);
        }
        itr = store.iterator();
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(store.size() != 0){
            int k = (int) store.iterator().next();                                          // Return the number it is pointing too and move to the next
            store.remove(k);                                                                // Remove the number from the set since already returned
            return k;
        } else {
            return -1;
        }
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return store.contains(number);                                                      // Number contains in the set or not
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        store.add(number);                                                                                  // Add the number to be released
    }
}