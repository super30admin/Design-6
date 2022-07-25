//time complexity : O(1) for all functions
//space complexity : O(maxNumbers)

class PhoneDirectory {

    Queue<Integer> q;
    HashSet<Integer> set;

    public PhoneDirectory(int maxNumbers) {
        q = new LinkedList();
        set = new HashSet();
        for(int i=0; i<maxNumbers; i++) {
            q.add(i);
            set.add(i);
        }
    }

    //pick random number from queue and remove from queue and set
    public int get() {
        if(q.isEmpty())
            return -1;
        int number = q.poll();
        set.remove(number);
        return number;
    }

    //number is available if it's in queue
    public boolean check(int number) {
        return set.contains(number);
    }

    //put back in set and queue, if not already there
    public void release(int number) {
        if(set.contains(number))
            return;
        set.add(number);
        q.add(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
