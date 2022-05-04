//Time Complexity O(1)
//Space Complexity O(N)
//Leetcode tested

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class PhoneDirectory {
    Queue<Integer> q;
    Set<Integer> set;

    PhoneDirectory(int maxNumbers){
        q = new LinkedList<>();
        set = new HashSet<>();

        for (int i = 0; i < maxNumbers; i++) {
            q.add(i);
            set.add(i);
        }
    }

    public int get(){
        if(q.isEmpty()) return -1;
        int result = q.poll();
        set.remove(result);
        return result;
    }

    public boolean check(int number){
        return set.contains(number);
    }

    public void release(int number){
        if(set.contains(number)) return;
        q.add(number);
        set.add(number);

    }

}
