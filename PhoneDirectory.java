//Time Complexity: O(n) n is the maxNumbers
//Space Complexity:O(n)
//LeetCode : passed all test cases

class PhoneDirectory {

    HashSet<Integer> set;
    Queue<Integer> q;
    int maxNumbers;
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        set = new HashSet<>();
        q = new LinkedList<>();
        this.maxNumbers = maxNumbers;
        for(int i =0;i<maxNumbers;i++){
            set.add(i);
            q.add(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(!q.isEmpty()){
            int n= q.poll();
            set.remove(n);
            return n;  
        }
        else return -1;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        if(set.contains(number)) return true;
        else return false;
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if(!set.contains(number) && number <= maxNumbers){
            set.add(number);
            q.add(number);
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