/**
 * Algo: Use set to have unique nos and check operation - O(1), use queue to return the number in O(1).
 * Time:O(1)
 * Space:O(n) n-maxNumbers
 */
class PhoneDirectory {

    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    HashSet<Integer> tochk;
    Queue<Integer>  toadd;
    public PhoneDirectory(int maxNumbers) {
        tochk = new HashSet<>();
        toadd = new LinkedList<>();
        for(int i=0;i<maxNumbers;i++){
            tochk.add(i);
            toadd.offer(i);
        }
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(!toadd.isEmpty()){
            int ret = toadd.poll();
            tochk.remove(ret);
            return ret;
        }
        return -1;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        if(tochk.contains(number)) return true;
        return false;
    }

    /** Recycle or release a number. */
    public void release(int number) {
        toadd.offer(number);
        tochk.add(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */