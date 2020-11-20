class PhoneDirectory {
public:
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    unordered_set<int> us;
    PhoneDirectory(int maxNumbers) {
       for (int i=0;i<maxNumbers;i++) {
           us.insert(i);
       }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    int get() {
        int result;
        if (us.size()==0)
            return -1;
        for (auto x:us) {
           result=x;
           break;
        }
        us.erase(result);
        return result;
    }
    
    /** Check if a number is available or not. */
    bool check(int number) {
        if ( us.find(number)==us.end() ) 
            return false;
        return true;
    }
    
    /** Recycle or release a number. */
    void release(int number) {
        us.insert(number);
    }
};

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory* obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj->get();
 * bool param_2 = obj->check(number);
 * obj->release(number);
 */