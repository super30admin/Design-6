//Time Complexity: O(1)
//Space Complexity: O(n)

class PhoneDirectory {
    HashSet<Integer> set;
    PriorityQueue<Integer> q;
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        set = new HashSet<>();
        q = new PriorityQueue<>();

        for(int i=0; i < maxNumbers; i++){
            set.add(i); q.add(i);
        }
    }

    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(q.isEmpty()) return -1;
            int numToReturn = q.poll();
            set.remove(numToReturn);
            return numToReturn;

    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return set.contains(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if(!set.contains(number)){ 
        q.add(number); set.add(number);

        }
    }
}