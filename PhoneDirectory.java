package Design6;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class PhoneDirectory {

    HashSet<Integer> slots;
    Queue<Integer> available;
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        slots = new HashSet<>();
        available = new LinkedList<>();
        for(int i = 0 ; i < maxNumbers ; i++) available.add(i);
    }

    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(available.isEmpty()) return -1;
        int n = available.poll();
        slots.add(n);
        return n;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !slots.contains(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if(slots.contains(number)){
            slots.remove(number);
            available.add(number);
        }
    }
}
