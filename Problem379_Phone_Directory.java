/**
 * Time Complexity - O(n)
 * Space Complextiy - O(n)
 */


class PhoneDirectory {

    int[] phoneNumbers;
    Set<Integer> used;
    int index;
    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        phoneNumbers = new int[maxNumbers];

        for(int i=0; i < maxNumbers; i++){
            phoneNumbers[i] = i;
        }
        used = new HashSet<>();
        index = maxNumbers - 1;
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {

        if(index < 0){
            return -1;
        }

        int phoneNum = phoneNumbers[index--];
        used.add(phoneNum);
        return phoneNum;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !used.contains(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if(!used.contains(number)){
            return;
        }
        index++;
        phoneNumbers[index] = number;
        used.remove(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */