import java.util.HashSet;
import java.util.Set;

//Time Complexity: O(1)
//SPace complexity: O(N)
public class DesignPhoneDirectory {

    private Set<Integer> slotsAvailable;

    public DesignPhoneDirectory(int maxNumbers) {
        slotsAvailable = new HashSet<>();
        for (int i = 0; i < maxNumbers; ++i) {
            slotsAvailable.add(i);
        }
    }

    public int get() {
        if (slotsAvailable.isEmpty()) {
            return -1;
        }

        int slot = slotsAvailable.iterator().next();
        slotsAvailable.remove(slot);
        return slot;
    }

    public boolean check(int number) {
        return slotsAvailable.contains(number);
    }

    public void release(int number) {
        slotsAvailable.add(number);
    }

}
