//Problem1 Design Phone Directory (https://leetcode.com/problems/design-phone-directory/)

// Time - O(1) 
// Space - O(maxnumber)
class PhoneDirectory {

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    int max;
    Set<Integer> used;
    Queue<Integer> available;
    public PhoneDirectory(int maxNumbers) {
        max = maxNumbers;
        used = new HashSet<>();
        available = new LinkedList<>();
        for(int i = 0; i < max; i++){
            available.add(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(!available.isEmpty()){
            int num = available.poll();
            used.add(num);
            return num;
        }
        
        return -1;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        if(number > max || number < 0){
            return false;
        }
        return !used.contains(number);
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if(used.remove(number))
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