import java.util.LinkedList;
import java.util.Queue;
/*
Design PhoneDirectory
approach: we have to keep track of available numbers and a queue to get an available number in O(1)
 */
public class PhoneDirectory {

    int totalNumbers;
    boolean []numbers;
    Queue<Integer> q;

    public PhoneDirectory(int totalNumbers) {
        this.totalNumbers = totalNumbers;
        this.numbers = new boolean[this.totalNumbers];
        this.q = new LinkedList<>();
        for (int i=0;i<this.totalNumbers;i++) {
            q.add(i);
        }
    }

    //O(1)
    private int get() {
        int number = q.poll();
        numbers[number] = true;
        return number;
    }

    //O(1)
    private boolean check(int n) {
        return !numbers[n];
    }

    //O(1)
    private void release(int number) {
        if (number>=0 && number<this.totalNumbers)
        {
            numbers[number] = false;
            q.add(number);
        }
    }

    public static void main(String []args) {
        // Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
        PhoneDirectory directory = new PhoneDirectory(3);

// It can return any available phone number. Here we assume it returns 0.
        System.out.println(directory.get());

// Assume it returns 1.
        System.out.println(directory.get());

// The number 2 is available, so return true.
        System.out.println(directory.check(2));

// It returns 2, the only number that is left.
        System.out.println(directory.get());

// The number 2 is no longer available, so return false.
        System.out.println(directory.check(2));

// Release number 2 back to the pool.
        directory.release(2);

// Number 2 is available again, return true.
        System.out.println(directory.check(2));
    }
}
