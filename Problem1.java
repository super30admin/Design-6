//time complexity-O(1)
//Space complexity-O(n)
//Ran on leetcode-Yes
//Solution with comments-
class PhoneDirectory {

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    HashSet<Integer> set;
    public PhoneDirectory(int maxNumbers) {
        set = new HashSet<>();
        for(int i=0;i<maxNumbers;i++)//adding all elemets to set
            set.add(i); 
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        int num=-1;
        if(set.size()!=0){
        num=set.iterator().next();//return the first avavialble number in set
        set.remove(num);
       
        }
        return num;
        
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
         return (set.contains(number));
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if(!set.contains(number)){
            set.add(number);
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