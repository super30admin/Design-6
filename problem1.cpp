// Time Complexity : O(1) (O(maxNumber) for constructor)
// Space Complexity : O(maxNumbers)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach

class PhoneDirectory {
public:
    queue<int>q;
    unordered_set<int>set;
    PhoneDirectory(int maxNumbers) {
        for(int i = 0;i<maxNumbers;i++){
            q.push(i);
            set.insert(i);
        }
    }
    
    int get() {
        if(q.empty()) return -1;
        int p = q.front();
        set.erase(p);
        q.pop();
        return p;
    }
    
    bool check(int number) {
        if(set.count(number)>0) return true;
        return false;
    }
    
    void release(int number) {
        if(set.count(number)==0){
            set.insert(number);
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