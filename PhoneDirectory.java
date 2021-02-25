// Time Complexity : The time complexity is O(1) for get, check and release operations
// Space Complexity : The space complexity if O(n) where n is the maximum numbers
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

class PhoneDirectory {

    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    Set<Integer> directory;
    Queue<Integer> q;

    public PhoneDirectory(int maxNumbers) {
        directory = new HashSet<>();
        q = new LinkedList<>();

        for(int i=0;i<maxNumbers;i++){
            directory.add(i);
            q.add(i);
        }

    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {

        if(!q.isEmpty()){
            int val = q.poll();
            directory.remove(val);
            return val;
        }

        return -1;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return directory.contains(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if(!directory.contains(number)){
            directory.add(number);
            q.offer(number);
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