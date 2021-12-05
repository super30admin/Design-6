// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

class PhoneDirectory {
    HashSet<Integer> set;
    Queue<Integer> q;
    public PhoneDirectory(int maxNumbers) {
        set = new HashSet<>();
        q = new LinkedList<>();
        for(int i=0; i<maxNumbers; i++){
            q.add(i);
            set.add(i);
        }
    }
    
    public int get() { // provides a num if not asssigned to anyone
        if(set.isEmpty()) return -1;
        int result = q.poll();
        set.remove(result);
        return result;
    }
    
    public boolean check(int number) {
        return set.contains(number);
    }
    
    public void release(int number) {
        if(set.contains(number)) return;
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