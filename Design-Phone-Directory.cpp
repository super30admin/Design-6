/*
Time Complexity: O(N)

Space Complexity: O(2N)

Did this code successfully run on Leetcode : Yes

Approach: Using a queue and a hashset to store and retrieve numbers.

Prob: 379. Design Phone Directory

*/

#include <bits/stdc++.h>
using namespace std;

class PhoneDirectory {
private:
    queue<int> q;
    unordered_set<int> s; 
public:
    PhoneDirectory(int maxNumbers) {
        for(int i=0;i<maxNumbers;i++){
            q.push(i);
            s.insert(i);
        }
    }
    
    int get() {
        if(q.empty()) return -1;
        int re = q.front();
        q.pop();
        s.erase(re);
        return re;
    }
    
    bool check(int number) {
        return s.find(number) != s.end();
    }
    
    void release(int number) {
        if(s.find(number) == s.end()){
            q.push(number);
            s.insert(number);
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