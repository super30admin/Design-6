import java.util.ArrayList;
import java.util.List;

// Time Complexity : O(1)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
//379. Design Phone Directory (Medium) - https://leetcode.com/problems/design-phone-directory/
class PhoneDirectory {
    
    private Queue<Integer> queue;
    private HashSet<Integer> set;

    public PhoneDirectory(int maxNumbers) {
        this.queue = new LinkedList<>();
        this.set = new HashSet<>();
        
        for (int i = 0; i < maxNumbers; i++) {
            this.queue.add(i);
            this.set.add(i);
        }
    }
    
    public int get() {
        if (set.isEmpty()) return -1;
        
        int result = queue.poll();
        set.remove(result);
        return result;
    }
    
    public boolean check(int number) {
        return set.contains(number);
    }
    
    public void release(int number) {
        if (!set.contains(number)) {
            queue.add(number);
            set.add(number);
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