import java.util.*;
class PhoneDirectory {
    Queue<Integer> q;
    HashSet<Integer> set;
    public PhoneDirectory(int maxNumbers) {
        q = new LinkedList<>();
        set = new HashSet<>();
        for(int i =0;i<maxNumbers;i++){
            q.add(i);
            set.add(i);
        }
    }
    //time complexity : 1
    // space complexity : 1
    // did it run on leetcode : yes
    // any doubts : no
    //https://leetcode.com/problems/design-phone-directory/submissions/
    public int get() {
        if(set.isEmpty()){
            return -1;
        }
        int result = q.poll();
        set.remove(result);
        return result;
    }
    
    //time complexity : 1
    // space complexity : 1
    public boolean check(int number) {
        return set.contains(number);
    }
    
    //time complexity : 1
    // space complexity : 1
    public void release(int number) {
        if(set.contains(number)){
            return;
        }
        q.add(number);
        set.add(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */