class PhoneDirectory {
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    Queue<Integer> availableNumbers;
    HashSet<Integer> assignedNumbers;
    
    // one yime operation
    // TC: O(N)
    // SC: O(N)
    public PhoneDirectory(int maxNumbers) {
        availableNumbers = new LinkedList();
        assignedNumbers = new HashSet();
        
        // stire all numbers till maxnumber in queue 
        for (int i = 0; i < maxNumbers; i++)
        {
            availableNumbers.offer(i); 
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    // TC: O(1)
    public int get() {
        if (!availableNumbers.isEmpty())
        {
            int result = availableNumbers.poll();
            assignedNumbers.add(result);
            return result;
        }
        return -1;
    }
    
    /** Check if a number is available or not. */
    //TC: O(1)
    public boolean check(int number) {             // if the given number is not in hashset then it means it is not assigned, hence it is available
        return !assignedNumbers.contains(number);
    }
    
    /** Recycle or release a number. */
    //TC: O(1)
    public void release(int number) {
        if (assignedNumbers.contains(number))
        {
            assignedNumbers.remove(number);
            availableNumbers.add(number);
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
