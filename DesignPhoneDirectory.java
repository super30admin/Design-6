// Time Complexity : O(1) for all operations
// Space Complexity : O(n) for queue and set
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
/*
We use a queue to store numbers of the phone directory and a hash set to keep track of unavailable numbers.
When get happens, we poll from the queue and add it to the usedset which helps us identify if a number is
available or not for check operation.Similarly, for release operation, we remove from the used set and add it
back to the queue.
 */
class PhoneDirectory {
    Queue<Integer> phoneDirectory;
    Set<Integer> usedSet;

    public PhoneDirectory(int maxNumbers) {
        this.phoneDirectory = new LinkedList<>();
        this.usedSet = new HashSet<>();

        for(int i = 0 ; i < maxNumbers ; i++) {
            phoneDirectory.add(i);
        }
    }

    public int get() {
        if(phoneDirectory.isEmpty())
            return -1;
        int num = phoneDirectory.poll();
        usedSet.add(num);
        return num;
    }

    public boolean check(int number) {
        return !usedSet.contains(number);
    }

    public void release(int number) {
        if(!usedSet.contains(number))
            return;
        usedSet.remove(number);
        phoneDirectory.add(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */