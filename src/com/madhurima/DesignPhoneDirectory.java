//Time complexity: O(1)
//Space Complexity: O(n)
//Did the code run successfully in LeetCode = yes

package com.madhurima;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class DesignPhoneDirectory {
    Queue<Integer> q;
    HashSet<Integer> set;

    public DesignPhoneDirectory(int maxNumbers) {
        q = new LinkedList<>();
        set = new HashSet<>();
        for(int i = 0; i < maxNumbers; i++){
            set.add(i);
            q.add(i);
        }
    }

    public int get() {
        if(q.isEmpty()){
            return -1;
        }
        int polled = q.poll();
        set.remove(polled);
        return polled;
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
