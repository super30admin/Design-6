//Leetcode: 379. Design Phone Directory
//Time Complexity: get -- O(n), check --O(1) , releae -- O(1)
//Space Complexity: O(n) 
class PhoneDirectory {

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    
    boolean[] numbers;
    int size;
    public PhoneDirectory(int maxNumbers) {
        size=maxNumbers;
        numbers= new boolean[maxNumbers];
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(size>0 ){
            for (int i=0;i<numbers.length;i++){
                if(!numbers[i]){
                    size--;
                    numbers[i]= true;
                    return i;
                }
            }
        }
        return -1;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return (!numbers[number]);
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        size++;
        numbers[number] = false;
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */