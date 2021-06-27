// Time Complexity :O(1) excluding the time taken in constructor
// Space Complexity : O(n) Including the space taken fby Hashset and queue.  
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class PhoneDirectory {
    set<int> hSet;
    queue<int> q;
public:
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    PhoneDirectory(int maxNumbers) {
        for(int i = 0; i < maxNumbers;i++){
            q.push(i);
            hSet.insert(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    int get() {
        if(q.empty()) return -1;
        int val = q.front();
        q.pop();
        hSet.erase(val);
        return val;
    }
    
    /** Check if a number is available or not. */
    bool check(int number) {
        return hSet.find(number) != hSet.end();
    }
    
    /** Recycle or release a number. */
    void release(int number) {
        if(hSet.find(number) == hSet.end()){
            hSet.insert(number);
            q.push(number);
        }
    }
};

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory* obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj->get();
 * bool param_2 = obj->check(number);
 * obj->release(number);
 */