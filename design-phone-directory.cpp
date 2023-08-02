// Time Complexity : O(1)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes

#include <unordered_set>
  
using namespace std;

class PhoneDirectory {
private:
    std::unordered_set<int> availableNumbers;

public:
    PhoneDirectory(int maxNumbers) {
        for (int i = 0; i < maxNumbers; i++) {
            availableNumbers.insert(i);
        }
    }

    int get() {
        if (availableNumbers.empty())
            return -1;

        int num = *availableNumbers.begin();
        availableNumbers.erase(num);
        return num;
    }

    bool check(int number) {
        return availableNumbers.find(number) != availableNumbers.end();
    }

    void release(int number) {
        availableNumbers.insert(number);
    }
};
