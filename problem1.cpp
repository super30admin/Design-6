#include <iostream>
#include <algorithm>
#include <unordered_set>
#include <queue>

using namespace std;

/*Approach

1. Store all the messages in the hashset and pair of messages with their corresponding timestamp in queue
2. For every print request, check if an older message exists in queue [Remove from queue & hashset if not within last 10 seconds]
3. If a similar message exists in hashset return false, else add it to the hashset and return true.

*/

class Logger{

    private:
        unordered_set<string> cache;
        queue<pair<int, string>> last10;

    public: 
        Logger(){

        }

        bool shouldPrintMessage(int timestamp, string message)
        {
            while(!last10.empty()) //we have previous messages within last 10 seconds
            {
                auto key = last10.front(); //key stores the timestamp 
                
                if(timestamp - key.first >= 10) 
                {
                    last10.pop(); 
                    cache.erase(key.second); //remove from hash set
                }

                else
                    break;
            }

            if(cache.count(message)) 
                return false;
            
            cache.insert(message);
            last10.push(make_pair(timestamp, message));
            return true;
        }
};