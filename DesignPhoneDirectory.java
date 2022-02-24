package design6;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class DesignPhoneDirectory {
	Set<Integer> set;
    Queue<Integer> queue;
    public DesignPhoneDirectory(int maxNumbers) {
        set = new HashSet<>();
        queue = new LinkedList<>();
        for(int i=0; i<maxNumbers; i++) {
            set.add(i);
            queue.offer(i);
        }
    }
    
    // O(1)
    public int get() {
        if(!queue.isEmpty()) {
            int re = queue.poll();
            set.remove(re);
            return re;
        }
        return -1;
    }
    
    // O(1)
    public boolean check(int number) {
        return set.contains(number);
    }
    
    // O(1)
    public void release(int number) {
        if(!set.contains(number)) {
            set.add(number);
            queue.offer(number);
        }
    }
}
