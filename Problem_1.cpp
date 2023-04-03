379. Design Phone Directory

class PhoneDirectory {
public:
    unordered_set<int> s;
    queue<int> q;

    PhoneDirectory(int maxNumbers) {
        int capacity = maxNumbers;
        for(int i=0; i< maxNumbers; i++) {
            s.insert(i);
            q.push(i);
        }
    }
    
    int get() {
        if (q.empty()) return -1;
        int x = q.front();
        q.pop();
        s.erase(x);
        return x;
    }
    
    bool check(int number) {
        if (s.find(number) != s.end()) {
            return true;
        }
        return false;
    }
    
    void release(int number) {
        if (s.find(number) != s.end()) {
            return;
        }
        q.push(number);
        s.insert(number);
    }
};

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory* obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj->get();
 * bool param_2 = obj->check(number);
 * obj->release(number);
 */
