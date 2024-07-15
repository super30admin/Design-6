// Time Complexity : O(1)
// Space Complexity : O(N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class PhoneDirectory {

    HashSet<Integer> set;
    Queue<Integer> q;
    public PhoneDirectory(int maxNumbers) {
        this.set = new HashSet<>();
        this.q = new LinkedList<>();

        for(int i=0;i<maxNumbers;i++){
            set.add(i);
            q.add(i);
        }
    }

    // O(1)
    public int get() {
        if(q.isEmpty()) return -1;
        int num = q.poll();
        set.remove(num);
        return num;
    }

    public boolean check(int number) {
        return set.contains(number);
    }

    public void release(int number) {
        if(!set.contains(number)){
            set.add(number);
            q.add(number);
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
