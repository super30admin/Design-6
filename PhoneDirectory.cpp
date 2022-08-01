// Time Complexity : O(1) 
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes

// Three line explanation of solution in plain english
/* Use a hash set to store all available numbers. For get() return the first element from set,
 * for release() add the number back to set. For check(), check if number is present in hashset.
 */

class PhoneDirectory {
public:
    unordered_set<int> set;
    PhoneDirectory(int maxNumbers) {
        for (int i = 0; i < maxNumbers; i++)
        {
            set.insert(i);
        }
    }
    
    int get() {
        if (set.empty())
        {
            return -1;
        }
        
        int number = *set.begin();
        set.erase(set.begin());
        return number;
    }
    
    bool check(int number) {
        return set.find(number) != set.end();
    }
    
    void release(int number) {
        if (set.find(number) != set.end())
        {
            return;
        }
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