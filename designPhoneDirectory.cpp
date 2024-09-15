//time complexity:O(1)
//space complexity:O(n)
//executed on leetcode: yes
//approach:using set
//any issues faced? no

class PhoneDirectory {
public:
    set<int>set;
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    PhoneDirectory(int maxNumbers) {
        for(int i=0; i<maxNumbers; i++)
        {
            set.insert(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    int get() {
        int ans=-1;
        for(int i:set)
        {
            ans=i;
            break;
        }
        set.erase(ans);
        return ans;
    }
    
    /** Check if a number is available or not. */
    bool check(int number) {
        return set.find(number)!=set.end();
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