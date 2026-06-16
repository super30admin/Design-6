// Time Complexity :
// get() -> O(1)
// check() -> O(1)
// release() -> O(1)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach in three sentences only
// We use a Queue to store all currently available phone numbers.
// A HashSet keeps track of assigned numbers for quick lookup.
// get() allocates a number, check() verifies availability, and release() makes a number reusable.

class PhoneDirectory {
    Queue<Integer> q;
    HashSet<Integer> usedSet;

    public PhoneDirectory(int maxNumbers) {
        this.q = new LinkedList<>();
        this.usedSet = new HashSet<>();

        // initialize all available numbers
        for(int i = 0; i < maxNumbers; i++) {
            q.add(i);
        }
    }

    // allocate next available number
    public int get() {
        if(q.isEmpty()) return -1;

        int num = q.poll();
        usedSet.add(num);
        return num;
    }

    // check if number is available
    public boolean check(int number) {
        return !usedSet.contains(number);
    }

    // release number back to pool
    public void release(int number) {
        if(!usedSet.contains(number)) return;

        usedSet.remove(number);
        q.add(number);
    }
}