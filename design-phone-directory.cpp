//Time - all methods O(1)
//Space - O(maxNumbers)
class PhoneDirectory {
public:
    PhoneDirectory(int maxNumbers) {
        for (int i = 0; i < maxNumbers; ++i) nums.insert(i);
    }

    int get() {
        if (nums.empty()) return -1;
        int num = *nums.begin(); nums.erase(nums.begin());
        return num;
    }

    bool check(int number) {
        return nums.count(number)>0;
    }

    void release(int number) {
        if (nums.count(number)==0) return;
        nums.erase(number);
    }
    
private:
    unordered_set<int> nums;
};