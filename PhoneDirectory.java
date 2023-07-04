import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// TC : O(n) -> n = maxNumbers. Since we will be putting n numbers in the queue or set
// SC : O(1) -> Queue will always return the element from the front and set will return based on the key
public class PhoneDirectory {

    Queue<Integer> available;
    Set<Integer> used;
    public PhoneDirectory(int maxNumbers) {
        this.available = new LinkedList<>();
        this.used = new HashSet<>();

        for(int i=0; i < maxNumbers; i++) {
            available.add(i);
        }
    }
    
    public int get() {
        if(available.isEmpty()) return -1;

        int number = available.poll();
        used.add(number);
        return number;
    }
    
    public boolean check(int number) {
        return !used.contains(number);
    }
    
    public void release(int number) {
        if(!used.contains(number)) return;

        used.remove(number);
        available.add(number);
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
