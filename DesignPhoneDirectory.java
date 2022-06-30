// Time: O(1) the user front method | O(N) for shifiting all the elements after polling | Space: O(1)

class PhoneDirectory {
    Queue<Integer> q; // basically we are maintaining a queue to provide the next available number to the user
    Set<Integer> s; // to find out if the typed number exists or available
    public PhoneDirectory(int maxNumbers) {
        q = new LinkedList<>();
        s = new HashSet<>();
        // making all possible slots from 0 to maxNumbers
        for(int i=0;i<maxNumbers;i++) {
            q.add(i);
            s.add(i);
        }
    }

    public int get() {
        // when we are providing the top of the queue slot to the user, we are removing it from the queue
        if(q.isEmpty()) return -1;
        int top = q.poll();
        s.remove(top);
        return top;
    }

    public boolean check(int number) {
        return s.contains(number);
    }

    public void release(int number) {
        // when a certain slot is about to become available again,
        // we check our directory
        // only if it does not exist already, we put it there
        if(!s.contains(number)) {
            s.add(number);
            q.add(number);
        }
    }
}