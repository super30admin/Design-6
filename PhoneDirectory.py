#Time Complexity : O(1) for get ,check and release
#Sapce Complexity : O(N) where N of elements stored in queue and hashset
class PhoneDirectory {
    HashSet<Integer> set;
    PriorityQueue<Integer> q;
    public PhoneDirectory(int maxNumbers) {
        set = new HashSet<>();
        q = new PriorityQueue<>();

        for(int i=0; i < maxNumbers; i++){
            set.add(i); q.add(i);
        }
    }
    public int get() {
        if(q.isEmpty()) return -1;
            int numToReturn = q.poll();
            set.remove(numToReturn);
            return numToReturn;

    }
    public boolean check(int number) {
        return set.contains(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if(!set.contains(number)){ // need to check in the set to avoid adding duplicate elements in the queue
        q.add(number); set.add(number);

        }
    }
}