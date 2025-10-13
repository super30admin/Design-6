#include<iostream>
#include<bits/stdc++.h>
#include<vector>
using namespace std;
/*
All numbers start out in a queue and a set that tracks the unused ones.
When we call get(), a number is removed from both the queue and the set.
When we release(), the number goes back into both the queue and the unused set—unless it’s already unused
*/
class PhoneDirectory{
    queue<int> q;
    unordered_set<int> unusedSet;
public:
    PhoneDirectory(int maxNumbers){
        for(int i = 0; i < maxNumbers; i++){
            q.push(i);
            unusedSet.insert(i);
        }
    }

    int get(){
        if(q.empty()) return -1;

        int num = q.front();
        q.pop();
        unusedSet.erase(num);
        return num;
    }

    bool check(int number){
        return unusedSet.find(number) != unusedSet.end();
    }

    void release(int number){
        if(unusedSet.find(number) != unusedSet.end()) return;

        unusedSet.insert(number);
        q.push(number);
    }
    
};
