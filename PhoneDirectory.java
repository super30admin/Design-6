import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// TC : O(n) -> n = maxNumbers. Since we will be putting n numbers in the queue or set
// SC : O(1) -> Queue will always return the element from the front and set will return based on the key
public class PhoneDirectory {

    Queue<Integer> queue;
    Set<Integer> set;

    /**
     *
     * @param maxNumbers - The maximum numbers that can be stored in the phone directory.
     */
    public PhoneDirectory(int maxNumbers) {
        queue = new LinkedList<>();
        set = new HashSet<>();

        for(int i=0; i < maxNumbers; i++){
            queue.add(i);
            set.add(i);
        }
    }

    /**
     * Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
     */
    public int get() {
        if(queue.isEmpty()) return -1;

        int used = queue.poll();
        set.remove(used);
        return used;
    }

    /**
     * Check if a number is available or not.
     */
    public boolean check(int number) {
        return set.contains(number);
    }

    /** 
        Recycle or release a number. 
        If the number is already assigned to someone, do nothing; else, bring it back to our pool of numbers.
    */
    public void release(int number) {
        if(set.contains(number)) return;

        queue.add(number);
        set.add(number);
    }

    public static void main(String[] args) {
        PhoneDirectory directory = new PhoneDirectory(3);
        System.out.println(directory.get());
        System.out.println(directory.get());
        System.out.println(directory.check(2));
        System.out.println(directory.get());
        System.out.println(directory.check(2));
        directory.release(2);
        System.out.println(directory.check(2));
    }
}
