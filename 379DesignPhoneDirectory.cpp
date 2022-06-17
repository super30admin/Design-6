/* 
    Time Complexity                              :  O(1) for all the three methods get, check and release
    Space Complexity                             :  O(2*N) to store the numbers in q and their corresponding bool expression in the map mp.
    Did this code successfully run on Leetcode   :  Yes
    Any problem you faced while coding this      :  No
*/

#include <bits/stdc++.h> 
using namespace std; 

// https://leetcode.com/problems/design-phone-directory/

class PhoneDirectory {
private:
    queue<int> q;
    unordered_map<int,bool> mp;
public:
    PhoneDirectory(int maxNumbers) {
        for(int i=0;i<maxNumbers;i++) {
            q.push(i);
            mp[i] = true;
        }
    }
    
    int get() {
        if(q.empty()) 
            return -1;
        
        int val = q.front();
        q.pop();
        mp.erase(val);
        return val;
    }
    
    bool check(int number) {
        if(mp.find(number) == mp.end())
            return false;
        return true;
    }
    
    void release(int number) {
        if(check(number)) return;
        q.push(number);
        mp[number] = true;
    }
};

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory* obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj->get();
 * bool param_2 = obj->check(number);
 * obj->release(number);
 */