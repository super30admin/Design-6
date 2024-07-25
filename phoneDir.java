import java.util.*;

class PhoneDirectory {
    // Sc: O(n)
    Set<Integer> set;

    public PhoneDirectory(int maxNumbers) {
        set = new HashSet<>();
        for (int i = 0; i < maxNumbers; i++) {
            set.add(i);
        }
    }

    public int get() { // Tc: O(1)
        if (set.isEmpty())
            return -1;
        int slot = set.iterator().next();
        set.remove(slot);
        return slot;

    }

    public boolean check(int number) { // Tc: O(1)

        return set.contains(number);

    }

    public void release(int number) { // Tc: O(1)
        set.add(number);

    }
}

/*
 * 
 * //Using a boolean array
 * class PhoneDirectory {
 * // Sc: O(n)
 * boolean[] avail;
 * 
 * public PhoneDirectory(int maxNumbers) {
 * avail = new boolean[maxNumbers];
 * for (int i = 0; i < avail.length; i++) {
 * avail[i] = true;
 * }
 * 
 * }
 * 
 * public int get() { // Tc: O(n)
 * for (int i = 0; i < avail.length; i++) {
 * if (avail[i]) {
 * avail[i] = false;
 * return i;
 * }
 * }
 * return -1;
 * }
 * 
 * public boolean check(int number) { // Tc: O(1)
 * return avail[number];
 * 
 * }
 * 
 * public void release(int number) { // Tc: O(1)
 * avail[number] = true;
 * 
 * }
 * }
 * 
 * /**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */