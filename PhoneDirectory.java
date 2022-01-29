import java.util.*;

class PhoneDirectory {
    Queue<Integer> q;
    HashSet<Integer> set;
    public PhoneDirectory(int maxNumbers) {
        q = new LinkedList<>();
        set = new HashSet<>();
        for(int i = 0; i < maxNumbers; i++){
            q.add(i);
            set.add(i);
            
        }
    }
    
    public int get() {
        if(q.isEmpty()) return -1;
        int result = q.poll();
        set.remove(result);
        return result;
    }
    
    public boolean check(int number) {
        return set.contains(number);
    }
    
    public void release(int number) {
        if(set.contains(number)){
            return;
        }
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