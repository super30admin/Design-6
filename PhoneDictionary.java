iclass PhoneDirectory {
    Set<Integer> used; // to store number we already 
    Queue<Integer> available;
    int maxNumber;
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        this.used = new HashSet<Integer>();
        this.available = new LinkedList<Integer>();
        this.maxNumber = maxNumber;
        //adding numbers in the queue, numbers available to assign 
        for (int i = 0; i < maxNumbers; i++) {
            available.add(i);
        }
    }
    //Time Complexity: O(1)
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
         //queue is empty, numbers not available to assign
        if(available.isEmpty())
            return -1;
        //providing a number which is not assigned to anyone, get it from available queue
        int result = available.poll();
	    //adding number in the used set, to know in the future that this number is already assigned.
		used.add(result);
        //give the number.
		return result;	
    }
    //Time Complexity: O(1)
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !used.contains(number);
    }
    //Time Complexity: O(1)
    /** Recycle or release a number. */
    public void release(int number) {
        if (used.remove(number))
            available.add(number);
    }
}
/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
