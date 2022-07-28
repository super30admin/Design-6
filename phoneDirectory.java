//Time Complexity: O(n)
//Space Complexity: O(n)
import java.util.*;
class PhoneDirectory {

    Queue<Integer> q;
    HashSet<Integer> hs;
    public PhoneDirectory(int maxNumbers) {
        this.q = new LinkedList<>();
        this.hs = new HashSet<>();
        for(int i=0;i<maxNumbers;i++){
            hs.add(i);
            q.add(i);
        }
    }
    
    public int get() {
        if(q.isEmpty()) return -1;
        int re = q.poll();
        hs.remove(re);
        return re;
    }
    
    public boolean check(int number) {
        return hs.contains(number);
    }
    
    public void release(int number) {
        if(!hs.contains(number)){
            q.add(number);
            hs.add(number);
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