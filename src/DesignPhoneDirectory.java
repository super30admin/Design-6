//                     Constructor       get, check, release
// Time Complexity:       O(n)                  O(1)
// Space Complexity:      O(n)                  O(1)

public class PhoneDirectory {
    Set<Integer> set;
    Queue<Integer> q;

    public PhoneDirectory(int maxNumbers) {
        set = new HashSet<>();
        q = new LinkedList<>();
        for(int i=0; i<maxNumbers; i++) {
            set.add(i);
            q.add(i);
        }
    }

    public int get() {
        if(q.isEmpty()) return -1;                      // if no number is available
        int next = q.poll();                            // take next available number
        set.remove(next);                               // make it unavailable
        return next;
    }

    public boolean check(int number) {
        return set.contains(number);                    // check availablity
    }

    public void release(int number) {
        if(!set.contains(number)) {                     // if number not available
            set.add(number);                            // make it
            q.add(number);
        }
    }
}
