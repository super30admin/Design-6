/*
 * #379. Design Phone Directory
 * 
 * Design a Phone Directory which supports the following operations:

 

1. get: Provide a number which is not assigned to anyone.
2. check: Check if a number is available or not.
3. release: Recycle or release a number.
 

Example:

// Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
PhoneDirectory directory = new PhoneDirectory(3);

// It can return any available phone number. Here we assume it returns 0.
directory.get();

// Assume it returns 1.
directory.get();

// The number 2 is available, so return true.
directory.check(2);

// It returns 2, the only number that is left.
directory.get();

// The number 2 is no longer available, so return false.
directory.check(2);

// Release number 2 back to the pool.
directory.release(2);

// Number 2 is available again, return true.
directory.check(2);
 

Constraints:

1. 1 <= maxNumbers <= 10^4
2. 0 <= number < maxNumbers
3. The total number of call of the methods is between [0 - 20000]

Note:
The Approach using HashSet will not work if get() operation needed numbers in a particular order
In that case, we would be using a Min Heap to return the available numbers in a specific order (say, 0,1,2,3,4)
Two data structures:
	1. HashSet -> To store used numbers
	2. Min Heap -> To store the available numbers

Operations:
	1. get() -> We will be getting the available numbers in order from a Min Heap, then remove that from Min Heap, add to HashSet in a pool of used numbers, and return that number
	   Time Complexity: Remove from heap -> log N (heapify after removing one number)
	   					Add to HashSet -> O (1)
	2. check(number) -> To see if a given number is available to assign, lookup in HashSet, if present (used), return false, else return true
	   Time Complexity: O (1)
	3. release(number) -> Put the number back to pool of available numbers, means add to Min Heap and remove from HashSet
	   Time Complexity: Add to Min Heap -> O (log N) (heapify after adding one number)
	                    Remove from HashSet -> O (1)
		
 */


// Using HashSet
/*
 * Time Complexity:
 * 	1. get() -> O (1)
 *  2. check(number) -> O (1) -> lookup takes constant time
 *  3. release(number) -> O (1) -> adding to a hashset takes constant time
 * 
 * Space Complexity: O (N) -> Using a HashSet to store 'N' available numbers 
 * 
 * Did this code successfully run on leetcode: Yes
 * 
 * Any problem you faced while coding this: No
 * 
 */

package com.s30.edu.design6;

import java.util.HashSet;

public class PhoneDirectoryUnordered {
	// Create a HashSet
    HashSet<Integer> set;

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    
    public PhoneDirectoryUnordered(int maxNumbers) {
        
        // Initialize the HashSet
        set = new HashSet<>();
        
        // Put all the numbers(all available initially) in HashSet
        for(int i = 0; i < maxNumbers; i++){
            set.add(i);
        }
        
    }
    
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    
    public int get() {
        // Since a set keeps track of all available numbers, we can check if a number is in a set, then return that number and remove from hashset to indicate that it is no more available
        
        // If no number is available, means set is empty, return -1
        if(set.isEmpty()){
            return -1; // No number available to assign
        }
        
        // If number is available -> present in a set, get the next number from a set, no ordering
        int numberAvailable = set.iterator().next();
        
        // Remove the number from a set to indicate that it is no more available
        set.remove(numberAvailable);
        
        // return that available number
        return numberAvailable;
          
    }
    
    
    /** Check if a number is available or not. */
    
    public boolean check(int number) {
        // Check if a number is present in a set -> set of available numbers
        // If yes, return true, else, return false
        return set.contains(number);
        
    }
    
    
    /** Recycle or release a number. */
    
    public void release(int number) {
        
        // Put back the used number in a pool of available numbers -> in a set
        // We are not assuming that release is always a valid operation, means we will check if the number to be released is already present in a set, then don't add, else add it to hashset
        if(!set.contains(number)){
            set.add(number); // put back as an available number
        }
        
    }
    
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
