/*
class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.max = maxNumbers
        self.hashset = set()
        
        for i in range(maxNumbers):
            self.hashset.add(i)
        

    def get(self) -> int:
        if len(self.hashset) > 0:
            # return self.hashset.pop()                  # this is inbuilt method in python which is not there in java but I have implemented it similar to java
            value = next(iter(self.hashset))
            self.hashset.remove(value)
            return value
        return -1
        

    def check(self, number: int) -> bool:
        if number not in self.hashset:
            return False
        
        return True
        

    def release(self, number: int) -> None:
        if number >= self.max or number in self.hashset:
            return
        
        self.hashset.add(number)
        

*/

// Time - O(n) but constructor only runs once so it is amortized O(1)
// Space - O(n)
// logic - put everything in hashset and then get, release and check function became O(1)
class PhoneDirectory {
    int max;
    HashSet<Integer> set;
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        max = maxNumbers;
        set = new HashSet<>();
        
        for (int i=0; i<max; i++){
            set.add(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (!set.isEmpty()){
        int value = set.iterator().next();
        set.remove(value);
        return value;
        }
        return -1;
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        if (set.contains(number))
            return true;
        return false;
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if (number >= max || set.contains(number))
            return;
        
        set.add(number);
    }
}