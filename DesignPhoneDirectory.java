//Time Complexity-O(1)
//Space Complexity-O(maxNumbers)
class PhoneDirectory {
HashSet<Integer>phoneDirectorySet=new HashSet<>();
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        for(int i=0;i<maxNumbers;i++)
        {
            phoneDirectorySet.add(i);
        }
        
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(phoneDirectorySet.isEmpty())
        {
            return -1;
        }
        int temp= phoneDirectorySet.iterator().next();
        phoneDirectorySet.remove(temp);
        return temp;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return phoneDirectorySet.contains(number);
        
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if(!phoneDirectorySet.contains(number))
        {
            phoneDirectorySet.add(number);
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