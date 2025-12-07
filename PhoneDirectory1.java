import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Design a Phone Directory
 * Problem Description:
 * You need to design a phone directory system that manages a pool of phone
 * numbers. The directory starts with a fixed number of available slots (from 0
 * to maxNumbers - 1), and you need to implement operations to allocate, check
 * availability, and release phone numbers.
 * 
 * The PhoneDirectory class should support the following operations:
 * 
 * Constructor PhoneDirectory(int maxNumbers): Initialize the directory with
 * maxNumbers available slots. Initially, all numbers from 0 to maxNumbers - 1
 * are available for allocation.
 * 
 * Get a number get(): Allocate and return any available phone number. Once a
 * number is allocated, it becomes unavailable. If no numbers are available,
 * return -1.
 * 
 * Check availability check(int number): Check if a specific phone number is
 * currently available (not allocated). Return true if the number is available,
 * false if it's already allocated.
 * 
 * Release a number release(int number): Return a previously allocated phone
 * number back to the pool of available numbers, making it available for future
 * allocation again.
 * 
 * Time Complexity: O(n) for get, o(1) for release and o(1) for check
 * Space Complexity: O(n)
 * 
 */

public class PhoneDirectory1 {
    int currentNumber = 0;
    int maxNumbers;
    private Set<Integer> numberAllocation;

    PhoneDirectory1(int maxNumbers) {
        this.maxNumbers = maxNumbers;
        this.numberAllocation = new HashSet<>();

    }

    public int get() { // o(n)
        if (numberAllocation.size() != maxNumbers) {
            if (!numberAllocation.contains(currentNumber) && currentNumber < maxNumbers) {
                int ret = currentNumber;
                numberAllocation.add(currentNumber);
                currentNumber++;
                return ret;
            } else {
                List<Integer> list = new ArrayList<>(numberAllocation);
                for (int i = 0; i < list.size(); i++) {
                    if (i != list.get(i)) {
                        currentNumber = i;
                        numberAllocation.add(i);
                        return i;
                    }
                }
            }

        }
        return -1;

    }

    public boolean check(int number) { // o(1)
        return numberAllocation.contains(number);
    }

    public void release(int number) { // O(1)
        if (numberAllocation.contains(number)) {
            numberAllocation.remove(number);
        }
    }

    public static void main(String[] args) {
        PhoneDirectory1 directory = new PhoneDirectory1(100);
        System.out.println(directory.get());
        System.out.println(directory.get());
        System.out.println(directory.check(1));
        System.out.println(directory.check(0));
        directory.release(1);
        System.out.println(directory.check(1));

        System.out.println(directory.get());
        System.out.println(directory.get());
        System.out.println(directory.get());
        System.out.println(directory.get());
        System.out.println(directory.get());
        System.out.println(directory.get());

        directory.release(5);
        System.out.println(directory.check(5));

        for (int i = 0; i < 95; i++) {
            System.out.println(directory.get());
        }

        directory.release(7);
        directory.release(8);
        System.out.println(directory.get());
        System.out.println(directory.get());
        System.out.println(directory.get());

    }
}
