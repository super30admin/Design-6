/*
Intuition: The idea is to insert all the possible numbers in the set and queue.
Set will help us to find a number in O(1)
Queue will help us to get the number in O(1)
///////////////////////////////////////////
Get Method(): We can provide any phone number to the user, With the help of the queue.
Time Complexity : O(1)
///////////////////////////////////////////
Release Method(): We simply add it in the queue and set.
Time Complexity : O(1)
///////////////////////////////////////////
Check Method(): We can check with the help of the set.
Time Complexity : O(1)
///////////////////////////////////////////
Toal Space Complexity : O(N), We are maintainig a queue and a set.

*/
class PhoneDirectory {
public:
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    queue<int> queue;
    set<int> set;
    PhoneDirectory(int maxNumbers) {
        for ( int i = 0; i < maxNumbers; i++){
            queue.push(i);
            set.insert(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    int get() {
        if ( queue.size() == 0) return -1;
        int num = queue.front();
        queue.pop();
        set.erase(num);
        return num;
    }
    
    /** Check if a number is available or not. */
    bool check(int number) {
        return set.find(number) != set.end();
    }
    
    /** Recycle or release a number. */
    void release(int number) {
        if ( set.find(number) != set.end()) return;
        queue.push(number);
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