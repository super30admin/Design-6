/*
we make use of linear data structure like quuee to add all the numbers
then we make use of hashset to keep track of assignned numbers
*/
class PhoneDirectory {

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    
    Queue<Integer> queue;
    HashSet<Integer> set;
        
    public PhoneDirectory(int maxNumbers) {
        this.set = new HashSet<>();
        this.queue = new LinkedList<>(); 
        for(int i = 0;i < maxNumbers;i++){ //tc:O(maxNum)
            queue.add(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
       
        if(!queue.isEmpty()){ 
             int res = this.queue.poll(); //O(1)
             this.set.add(res); //O(1)
                return res;
        }
        return -1;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !set.contains(number); //O(1)
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if(set.contains(number)){  
            this.set.remove(number);
            this.queue.add(number);
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