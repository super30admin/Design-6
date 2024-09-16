package com.s30.edu.design6;

import java.util.HashSet;
import java.util.PriorityQueue;

/***************** Using Min Heap and HashSet -> get() needs numbers in a particular order ******************/

/*
* Time Complexity: 
 * 	Operations:
	1. get() -> We will be getting the available numbers in order from a Min Heap, then remove that from Min Heap, add to HashSet in a pool of used numbers, and return that number
	   Time Complexity: Remove from heap -> log N (heapify after removing one number)
	   					Add to HashSet -> O (1)
	2. check(number) -> To see if a given number is available to assign, lookup in HashSet, if not present (available), return true, else return false
	   Time Complexity: O (1)
	3. release(number) -> Put the number back to pool of available numbers, means add to Min Heap and remove from HashSet
	   Time Complexity: Add to Min Heap -> O (log N) (heapify after adding one number)
	                    Remove from HashSet -> O (1)
*  
*  Space Compelxity: O (N + M) -> 'N' used numbers stored in HashSet, 'M' available numbers stored in a Min Heap
*  
*  Did this code successfully run on leetcode: Yes
* 
* Any problem you faced while coding this: No
* 
*/

public class PhoneDirectoryOrdered {
	// Create a HashSet
    HashSet<Integer> set;
    
    // Create a priority queue
    PriorityQueue<Integer> minHeap;

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    
    public PhoneDirectoryOrdered(int maxNumbers) {
        
        // Initialize the HashSet
        set = new HashSet<>();
        
        // Initialize a Priority queue
        minHeap = new PriorityQueue<Integer>();  // Since, we have integers, in-built comparator will be used
    
        // Add all the available numbersinitially all available) in Min Heap
        for(int i = 0; i < maxNumbers; i++){
            minHeap.add(i);
        }
        
    }
    
     /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    
    public int get() {
        
       // If no number is available, means heap is empty, return -1
       if(minHeap.isEmpty()){
           return -1;
       }
        
       // If number is available -> present in a heap, remove it from heap and add to the set of used numbers, return the available number
       int numberAvailable = minHeap.remove();
       set.add(numberAvailable);
       return numberAvailable; 
          
    }
    
    
    /** Check if a number is available or not. */
    
    public boolean check(int number) {
        // Check if a number is not present in a set, means it is available -> set of used numbers
        // If not present, return true, else, return false
        return !set.contains(number);
        
    }
    
    
    /** Recycle or release a number. */
    
    public void release(int number) {
        // Put back the used number in a pool of available numbers -> add to a heap
        // We are not assuming that release is always a valid operation, means we will check if the number to be released is present in a set(used), If present, add to heap and remove from set of used numbers
        if(set.contains(number)){
            minHeap.add(number);
            set.remove(number);
        } 
        
    }
    
}
