// Time Complexity : O(1) for all three operations
// Space Complexity : O(n) where n is maxNumbers
// Did this code successfully run on Leetcode : Yes
// Approach : We maintain a hashset and a queue so that check and get can be done in O(1). First we add all number until maxNumbers to
// the hashset and the queue. For get, we check if queue is not empty and return the head. For check, we check if it's present in the
// set. For release, we add it back to the queue and set if it's not already present.

class PhoneDirectory {

    Queue<Integer> queue;
    HashSet<Integer> set;

    public PhoneDirectory(int maxNumbers) {
        this.queue = new LinkedList<>();
        this.set = new HashSet<>();

        for(int i = 0; i < maxNumbers; i++) { //fill the hashset and queue
            queue.add(i);
            set.add(i);
        }
    }

    public int get() {
        if(queue.isEmpty()) return -1;

        int res = queue.poll(); //return the head of queue
        set.remove(res); //remove from set also
        return res;
    }

    public boolean check(int number) {
        return set.contains(number); //check if it's present in set
    }

    public void release(int number) {
        if(set.contains(number)) return; //already contains

        set.add(number);
        queue.add(number);
    }
}
