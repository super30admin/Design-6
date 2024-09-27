class PhoneDirectory {

    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    boolean[] directory;
    int maxNumbers;

    // O(1)
    public PhoneDirectory(int maxNumbers) {
        this.maxNumbers = maxNumbers;
        directory = new boolean[maxNumbers];
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    // O(n)
    public int get() {
        for(int i = 0; i < maxNumbers; i++) {
            if(directory[i] == false) {
                directory[i] = true;
                return i;
            }
        }
        return -1;
    }

    /** Check if a number is available or not. */
    // O(1)
    public boolean check(int number) {
        return !directory[number];
    }

    /** Recycle or release a number. */
    // O(1)
    public void release(int number) {
        directory[number] = false;
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */