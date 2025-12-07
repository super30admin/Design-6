import java.util.ArrayDeque;

import java.util.HashSet;
import java.util.Set;

/**
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
 * Time Complexity: O(1) for all operations.
 * Space Complexity: O(n) where n is the maximum number of phone numbers.
 * 
 */

public class PhoneDirectory {

    private Set<Integer> numberAllocation;
    private ArrayDeque<Integer> q;

    PhoneDirectory(int maxNumbers) {
        this.numberAllocation = new HashSet<>();
        q = new ArrayDeque<>(maxNumbers);
        for (int i = 0; i < maxNumbers; i++) {
            q.add(i);
        }
    }

    public int get() { // o(1)
        if (!q.isEmpty()) {
            int number = q.poll();
            numberAllocation.add(number);
            return number;
        }
        return -1;
    }

    public boolean check(int number) { // o(1)
        return numberAllocation.contains(number);
    }

    public void release(int number) { // O(1)
        if (numberAllocation.contains(number)) {
            numberAllocation.remove(number);
            q.addLast(number);
        }

    }

    public static void main(String[] args) {
        PhoneDirectory directory = new PhoneDirectory(100);
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