// Time Complexity : O(1)
// Space Complexity :O(2n)
// Did this code successfully run on Leetcode : Yes
// Three line explanation of solution in plain english
// take a linear DS to get in O(1) and hashset to check in O(1) time, while innitailize store the values to the queue and hashset, and give the first value from the q, and remove it from the HS. in the release add the number back to the queue, and hashSet
// Your code here along with comments explaining your approach
class PhoneDirectory {

    Queue<Integer> q;
    HashSet<Integer> hs;
    public PhoneDirectory(int maxNumbers) {
        q = new LinkedList<>();
        hs = new HashSet<>();
        for(int i = 0; i < maxNumbers; i++){
            q.add(i); hs.add(i);
        }
    }

    public int get() {
        if(q.isEmpty()) return -1;
        int val = q.poll();
        hs.remove(val);
        return val;
    }

    public boolean check(int number) {
        return (hs.contains(number));
    }

    public void release(int number) {
        if(hs.contains(number)) return;
        hs.add(number);
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