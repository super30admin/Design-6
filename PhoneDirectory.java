//Time Complexity :O(1) for get and check functions
//Space Complexity :O(N)
//Did this code successfully run on Leetcode :yes
//Any problem you faced while coding this : Nope.


//Your code here along with comments explaining your approach

class PhoneDirectory {

    HashSet<Integer> set;
    Queue<Integer> removed;
    int size, capacity;
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        set = new HashSet<>();
        removed = new LinkedList<>();
        size = 0;
        capacity = maxNumbers;
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(size == capacity){return -1;}
        if(removed.size() > 0){
                int r = removed.poll();
                set.add(r);
                size++;
                return r;
        }else{
            if(check(size)){
                set.add(size);
                size++;
                return size-1;
            }
        }
        return -1;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        if(size == capacity || set.contains(number)){return false;}
        return true;
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if(set.contains(number)){
            set.remove(number);
            removed.add(number);
            size--;
        }
    }
}