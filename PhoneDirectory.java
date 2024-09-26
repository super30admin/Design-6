// Time Complexity - O(1) all functions
// Space Complexity - O(n) for maintaining n numbers in a set & queue
import java.util.HashSet;
import java.util.Queue;

class PhoneDirectory {
    HashSet<Integer> set;
    Queue<Integer> q;
    public PhoneDirectory(int maxNumbers) {
        set = new HashSet<>();
        q = new LinkedList<>();
        for(int i=0;i<maxNumbers;i++) { // adding all elements to set and queue
            set.add(i); q.add(i);
        }
    }
    
    public int get() {
        if(!q.isEmpty()) { 
            int n = q.poll();
            set.remove(n);
            return n;
        }
        return -1;
    }
    
    public boolean check(int number) {
        return set.contains(number);
    }
    
    public void release(int number) {
        if(!set.contains(number)) {
            set.add(number); q.add(number);
        }
    }
}