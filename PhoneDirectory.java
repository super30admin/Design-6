import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class PhoneDirectory {
    Queue<Integer> q;
    HashSet<Integer> set;

    public PhoneDirectory(int maxNumbers) {
        this.set = new HashSet<>();
        this.q = new LinkedList<>();
        for (int i = 0; i < maxNumbers; i++) {
            set.add(i);
            q.add(i);
        }
    }

    public int get() {
        if (q.isEmpty())
            return -1;
        int re = q.poll();
        set.remove(re);
        return re;
    }

    public boolean check(int number) {
        // return q.contains(number);
        return set.contains(number);
    }

    public void release(int number) {
        if (!set.contains(number)) {
            set.add(number);
            q.add(number);
        }
    }

    public static void main(String[] args) {
        PhoneDirectory phoneDirectory = new PhoneDirectory(5);

        System.out.println("Available phone numbers: ");
        for (int i = 0; i < 5; i++) {
            System.out.print(phoneDirectory.get() + " ");
        }
        System.out.println();

        int numberToCheck = 2;
        System.out.println("Is " + numberToCheck + " available: " + phoneDirectory.check(numberToCheck));

        int numberToRelease = 1;
        phoneDirectory.release(numberToRelease);
        System.out.println("Released number: " + numberToRelease);

        System.out.println("Available phone numbers after releasing: ");
        for (int i = 0; i < 3; i++) {
            System.out.print(phoneDirectory.get() + " ");
        }
        System.out.println();

        int newNumberToCheck = 4;
        System.out.println("Is " + newNumberToCheck + " available: " + phoneDirectory.check(newNumberToCheck));
    }
}