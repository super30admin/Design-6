// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None

import java.util.*;

class PhoneDir {
    Queue<Integer> q;
    HashSet<Integer> set;
    public PhoneDir(int maxNumbers) {
        this.q = new LinkedList<>();
        this.set = new HashSet();
        for(int i=0;i<maxNumbers;i++){
            q.add(i);
            set.add(i);
        }
    }

    public int get() {
        if(q.isEmpty()){
            return -1;
        }
        int re = q.poll();
        set.remove(re);
        return re;
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