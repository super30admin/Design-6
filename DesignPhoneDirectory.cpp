//Time Complexity O(n)
// Space Complexity O(n)
//Problem successfully executed on leetcode
//No problems faced while coading this.


#include<iostream>
#include<vector>
#include<queue>
#include<map>
#include<unordered_set>
#include<stack>
using namespace std;

queue<int> q;
unordered_set<int> set;
public PhoneDirectory(int maxNos)
    {
        for(int i=0;i<maxNos;i++)
        {
            set.insert(i);
            q.push(i);
        }
    }
    
    public int Get()
    {
        if(q.empty()) return -1;
        int ans=q.front();
        q.pop();
        set.erase(ans);
    }
    
    public bool check(int number)
    {
        if(set.find(number)!=set.end())
        {
            return true;
        }
        return false;
    }
    public void release(int number)
    {
        if(set.find(number)!=set.end())
            return;
        set.insert(number);
        q.push(i);
    }
