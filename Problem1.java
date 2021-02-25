// Time Complexity - O(1)
// Space Complexity - O(n) where n is the number of phone numbers
// This Solution worked on LeetCode

class PhoneDirectory {

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    HashSet<Integer> set = new HashSet<>(); // HashSet for O(1) operation to get,check and release
    public PhoneDirectory(int maxNumbers) {
        for(int i = 0; i< maxNumbers;i++){
            set.add(i);       // Add maxnumbers in the set 
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(set.size() == 0) return -1;
        int Returnphone = set.iterator().next();    // Use Set Iterator to get the number from set
        set.remove(Returnphone);              // remove the number from set since it is not available for assignment now
        return Returnphone;           
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return set.contains(number);    
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        set.add(number);    
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
