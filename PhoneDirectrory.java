// Time Complexity :  O(n) for iterator and O(1) for everything else
// Space Complexity : O(n) 
// Did this code successfully run on Leetcode : Yes Its working
// Any problem you faced while coding this : No
class PhoneDirectory {

    /** Initialize your data structure here
    @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    HashSet<Integer> numbers;
    public PhoneDirectory(int maxNumbers) {
        numbers=new HashSet<>();
        for(int i = 0 ; i <maxNumbers;i++)
        {
            numbers.add(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(!numbers.isEmpty())
        {
            for(Integer num:numbers)
            {
                numbers.remove(num);
                return num;
            }
        }
   
        return -1;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return numbers.contains(number);
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        numbers.add(number);
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */