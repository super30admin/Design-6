// Time Complexity : O(N)
// Space Complexity : O(N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class PhoneDirectory {
public:
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    unordered_set<int> set;
    PhoneDirectory(int maxNumbers) {
        for(int i=0;i<maxNumbers;i++){
            set.insert(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    int get() {
        if(!set.empty()){
            int value = *set.begin();
            set.erase(value);
            return value;
        }
        return -1;
    }
    
    /** Check if a number is available or not. */
    bool check(int number) {
        if(set.find(number)!=set.end()){
            return true;
        }
        return false;
    }
    
    /** Recycle or release a number. */
    void release(int number) {
        set.insert(number);
    }
};

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory* obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj->get();
 * bool param_2 = obj->check(number);
 * obj->release(number);
 */
