class PhoneDirectory {
    
    HashSet<Integer> phoneNumbers; //database of all available phone numbers
    //space - O(maxNumbers)
    //time - O(maxNumbers) to add all into set
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        phoneNumbers = new HashSet<>();
        for(int i = 0; i < maxNumbers; i++)
        {
            phoneNumbers.add(i); //populate db with all numbers from 0 to maxNumbers - 1
        }
    }
    
    
    //time - O(1)
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        int returnNumber = -1; //return -1 if nothing is available
        for(Integer number : phoneNumbers) //get the 1st number, remove from the db and return it
        {
            returnNumber = number;
            phoneNumbers.remove(number);
            break;
        }
        return returnNumber;
    }
    
    //time - O(1)
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return phoneNumbers.contains(number); //return true if number is present in db
    }
    
    //time - O(1)
    /** Recycle or release a number. */
    public void release(int number) {
        phoneNumbers.add(number); //hashset will automatically handle duplicate additions
        return;
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
