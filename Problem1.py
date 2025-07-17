"""
This implementation uses a set to track available phone numbers. Initially, all numbers from 0 to maxNumbers - 1 are stored in the set. The get() method pops a number randomly from the set, check() verifies if a number is available, and release() adds a number back to the set.
Time Complexity:
get(): O(1) average
check(): O(1)
release(): O(1)
Space Complexity: O(N), where N is maxNumbers (to store all numbers in the set).
"""
class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.nums = set(range(maxNumbers))
        
    def get(self) -> int:
        if self.nums:
            return self.nums.pop()
        else:
            return -1
        
    def check(self, number: int) -> bool:
        if number in self.nums:
            return True
        else:
            return False
        
    def release(self, number: int) -> None:
        self.nums.add(number)
        
# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)