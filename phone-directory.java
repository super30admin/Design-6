// TC: O(1) get, check, release and O(maxNumbers) constructor
// SC: O(maxNumbers)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class PhoneDirectory {
    Set<Integer> set;
    public PhoneDirectory(int maxNumbers) {
        set = new HashSet<>();
        for (int i = 0; i < maxNumbers; i++) {
            set.add(i);
        }
    }
    public int get() {
        if (set.size() == 0) {
            return -1;
        }
        int num = set.iterator().next();
        set.remove(num);
        return num;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return set.contains(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        set.add(number);
    }
}
