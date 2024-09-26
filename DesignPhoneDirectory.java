//Problem 125: Design Phone Directory
//TC:O(1)
//SC:O(maxNumbers)

/*
Data Structures Used: Set and Queue. 
Set is used for checking whether number exists or not in O(1)
Queue is used for getting the number in O(1), because we can not get first number from a 'set' in O(1). Iterator on set is O(n), i.e Queue is used
*/

import java.util.*;
class PhoneDirectory {

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    private Set<Integer> set;
    private Queue<Integer> q;//for getting the number in O(1)
    public PhoneDirectory(int maxNumbers) {
        set = new HashSet<>();
        q = new LinkedList<>();//for getting the number in O(1)
        for(int i=0;i<maxNumbers;i++){
            set.add(i);q.offer(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        //Note: If we will iterate over set even to get the first element its never will be O(1), because under the hood hashset is array of array and first value can be stored anywhere because of Hashing. Thats why for getting the element in O(1) use Queue;
        if(set.size()>0){
            int num = q.poll();
            set.remove(num);
            return num;
        }
        return -1;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return set.contains(number);
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if(!set.contains(number)){
         set.add(number);
         q.add(number);   
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