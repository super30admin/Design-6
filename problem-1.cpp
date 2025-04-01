// https://leetcode.com/problems/design-phone-directory/description/

// Time Complexity
// get(): O(1)
// check(): O(1)
// release(): O(1)

// Space Complexity
// get(): O(1)
// check(): O(1)
// release(): O(1)

class PhoneDirectory {
    vector<bool> mp;
    queue<int> q;
public:
    PhoneDirectory(int maxNumbers) {
        mp = vector<bool>(maxNumbers, false);
        for(int i=0; i<maxNumbers; i++){
            q.push(i);
        }
    }
    
    int get() {
        if(!q.empty()){
            int temp = q.front();
            q.pop();
            mp[temp] = true;
            return temp;
        }
        return -1;
    }
    
    bool check(int number) {
        return !mp[number];
    }
    
    void release(int number) {
        if(mp[number]){
            mp[number] = false;
            q.push(number);
        }
    }
};
