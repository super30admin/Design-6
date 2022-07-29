// Time Complexity : O(1)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

import java.util.*;

class PhoneDirectory {
    // here I will use HashSet for checking number is already occupied or not and
    // queue for get not assigned number
    HashSet<Integer> set;
    Queue<Integer> q;

    public PhoneDirectory(int maxNumbers) {
        set = new HashSet<>();
        q = new LinkedList<>();
        // first add all the numbers in the queue
        for (int i = 0; i < maxNumbers; i++) {
            q.add(i);
        }
    }

    public int get() {
        // check if queue is empty than there is no number left so we return -1
        if (q.isEmpty())
            return -1;
        // get number from the queue
        int num = q.poll();
        // add number in the hashSet as we are going to check this number is occupied or
        // not
        set.add(num);
        return num;
    }

    public boolean check(int number) {
        // check in hashSet if there is no such number in hashset we return true
        // else return false;
        return !set.contains(number);
    }

    public void release(int number) {
        // here we will check if number is already occupied then we are going to realese
        // this number
        if (set.contains(number)) {
            set.remove(number);
            q.add(number);
        }

    }

    public static void main(String[] args) {
        PhoneDirectory phoneDirectory = new PhoneDirectory(10);
        phoneDirectory.get(); // It can return any available phone number. Here we assume it returns 0.
        phoneDirectory.get(); // Assume it returns 1.
        phoneDirectory.check(2); // The number 2 is available, so return true.
        phoneDirectory.get(); // It returns 2, the only number that is left.
        phoneDirectory.check(2); // The number 2 is no longer available, so return false.
        phoneDirectory.release(2); // Release number 2 back to the pool.
        phoneDirectory.check(2); // Number 2 is available again, return true.
    }
}
