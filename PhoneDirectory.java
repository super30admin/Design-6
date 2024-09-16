//Time Complexity - O(n)
//Space Complexity - O(n)

class PhoneDirectory {

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    HashSet<Integer> hset;
    Queue<Integer> q;
    public PhoneDirectory(int maxNumbers) {
        hset = new HashSet<>();
        q = new LinkedList<>();
        for(int i = 0; i < maxNumbers; i++){
            hset.add(i);
            q.add(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(!q.isEmpty()){
            int num = q.poll();
            hset.remove(num);
            return num;
        }
        return -1;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return hset.contains(number);
        
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if(!hset.contains(number)){
            hset.add(number);
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