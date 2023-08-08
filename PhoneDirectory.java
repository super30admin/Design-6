import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;

public class PhoneDirectory {

    // HASHSET AND QUEUE - TIME O(1) AND SPACE O(N)

    HashSet<Integer> set;
    Queue<Integer> q;

    // Constructor - O(N) INITIALIZATION
    public PhoneDirectory(int maxNumbers) {

        set = new HashSet<>();        // O(N) space
        q = new LinkedList<>();       // O(N) space

        // build Hash set and queue of initially available numbers
        for(int i = 0; i < maxNumbers; i++) {     // O(N)

            set.add(i);
            q.add(i);
        }
    }

    public int get() {                     // O(1)

        // if queue (or set can be checked) does not have anymore numbers
        if(q.isEmpty())    return -1;

        // remove number from set, which is removed from head/ front of queue and assigned as a phone number
        int assign = q.poll();

        set.remove(assign);

        return assign;

    }

    public boolean check(int number) {      // O(1)

        // number is available if set contains it
        return set.contains(number);
    }

    public void release(int number) {       // O(1)

        // if released number is not in set already, add it to both set and queue as done while adding numbers initially
        if(!set.contains(number)) {

            set.add(number);
            q.add(number);
        }
    }

    public static void main(String[] args) {

        PhoneDirectory obj = new PhoneDirectory(3);

        System.out.println("Phone Directory built");

        System.out.println(obj.get());

        System.out.println(obj.get());

        System.out.println(obj.check(2));

        System.out.println(obj.get());

        System.out.println(obj.check(2));

        obj.release(2);
        System.out.println("Released");

        System.out.println(obj.check(2));

    }
}

/*
TIME COMPLEXITY = O(1) for all three operations

SPACE COMPLEXITY = O(N) for hash set and queue
*/

/*
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
