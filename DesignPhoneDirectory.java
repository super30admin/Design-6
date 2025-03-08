//TC: O(maxNumbers) for initialization, O(1) for all other methods
//SC: O(maxNumbers)
//approach - hashSet

import java.util.HashSet;
import java.util.Set;

public class DesignPhoneDirectory {
    Set<Integer> set;

//maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public DesignPhoneDirectory(int maxNumbers) {
        set= new HashSet<Integer>();
        for(int i=0; i<maxNumbers; i++)set.add(i);
    }

    /** Provide a number which is not assigned to anyone. Return -1 if none is available. */
    public int get() {
        if(set.size()==0)return -1;
        int num = set.iterator().next();
        set.remove(num);
        return num;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return set.contains(number);
    }

    /** Recycle or release a number. */
    public void release(int number) {
        set.add(number);
    }
}
