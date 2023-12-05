// Time Complexity : O(1)
// Space Complexity :O(1)
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :
class PhoneDirectory {
    LinkedList<Integer> q;
    HashSet<Integer> set;

    public PhoneDirectory(int maxNumbers) {
        this.q = new LinkedList<>();
        this.set = new HashSet<>();
        for(int i = 0; i < maxNumbers; i++){
            this.q.add(i);
            this.set.add(i);
        }
    }
    
    public int get() {
        if(q.isEmpty())
            return -1;
        int x = q.peek();
        q.poll();
        set.remove(x);
        return x;
    }
    
    public boolean check(int number) {
        if(set.contains(number))
            return true;
        return false;
    }
    
    public void release(int number) {
        if(!set.contains(number))
            {
                q.add(number);
                set.add(number);
            }
    }
}