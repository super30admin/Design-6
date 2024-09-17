package design;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Phone_directory {
    HashSet<Integer> set;
    Queue<Integer> q;
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        set= new HashSet<>();
        q= new LinkedList<>();
        
        for(int i=0;i<maxNumbers;i++){
            set.add(i);
            q.add(i);
        }
        
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(!q.isEmpty()){
            int n=q.poll();
            set.remove(n);
            return n;
        }
        return -1;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return set.contains(number);
        
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if(!set.contains(number)){
            set.add(number);
            q.add(number);
        }
    }
}
