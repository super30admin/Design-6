// Time Complexity : O(1) for all ops
// Space Complexity : O(n)
// Did this code succesfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// 1. Maintain an unordered set of all numbers
// 2. For get, return and delete element at begin() of set
// 3. For check, use find function. For release, insert number into set  

class PhoneDirectory {
public:
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    unordered_set<int> dir;
    PhoneDirectory(int maxNumbers) {
        for(int i=0;i<maxNumbers;i++)
            dir.insert(i);
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    int get() {
        if(dir.size()==0)
            return -1;
        else{
            int num = *dir.begin()
            dir.erase(num); 
            return num;
        }
    }
    
    /** Check if a number is available or not. */
    bool check(int number) {
        if(dir.find(number)==dir.end())
            return false;
        else
            return true;
    }
    
    /** Recycle or release a number. */
    void release(int number) {
        dir.insert(number);
    }
};

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory* obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj->get();
 * bool param_2 = obj->check(number);
 * obj->release(number);
 */