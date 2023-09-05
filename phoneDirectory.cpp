//time: O(1) for all the methods.
//space: O(n)

class PhoneDirectory {
public:
    unordered_set<int> set;
    queue<int> q;
    
    PhoneDirectory(int maxNumbers) {
        for (int i = 0; i < maxNumbers; i++) {
            set.insert(i);
            q.push(i);
        }
        
    }
    
    int get() {
        if (q.empty()){
             return -1;
        }
        int result = q.front();
        q.pop();
        set.erase(result);
        return result;
        
    }
    
    bool check(int number) {
         if(set.find(number) != set.end()){
             return true;
         }
         return false;
        
    }
    
    void release(int number) {
        if (set.find(number) == set.end()) {
            set.insert(number);
            q.push(number);
        }
        
    }
};