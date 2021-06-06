//Time Complexity:O(n)  to add n numbers into queue
//Space Complexity:O(n) due to queue and hashset
class PhoneDirectory {

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    HashSet<Integer> dir;
    Queue<Integer> q;
    public PhoneDirectory(int maxNumbers) {
        dir = new HashSet<>();
        q = new LinkedList<>();
        for(int i=0;i<maxNumbers;i++){
            q.add(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(!q.isEmpty()){
            int i=q.poll();
            dir.add(i);
            return i;
        }
        return -1;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        if(dir.contains(number)) return false;
        return true;
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if(dir.contains(number)){
            q.add(number);
            dir.remove(number);
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