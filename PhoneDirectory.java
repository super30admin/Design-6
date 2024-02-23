/*
* Did this code successfully run on Leetcode : YES
* 
* Any problem you faced while coding this : NO
* 
* Time Complexity: 
    get = O(1)
    release = O(1)
    check = O(1)
* 
* Space Complexity: 
    get = O(1)
    release = O(1)
    check = O(1)
* 
*/

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class PhoneDirectory {
    Queue<Integer> directory;

    HashSet<Integer> availability;

    public PhoneDirectory(int maxNumbers) {
        this.directory = new LinkedList<>();

        this.availability = new HashSet<>();

        for (int index = 0; index < maxNumbers; index++) {
            directory.add(index);
            availability.add(index);
        }
    }

    public int get() {
        if (directory.isEmpty()) {
            return -1;
        }

        int number = directory.poll();

        availability.remove(number);

        return number;
    }

    public boolean check(int number) {
        return availability.contains(number);
    }

    public void release(int number) {
        if (!availability.contains(number)) {
            directory.add(number);

            availability.add(number);
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
