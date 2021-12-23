# 379. Design Phone Directory
# https://leetcode.com/problems/design-phone-directory/

# Space Complexity: O(n)

from collections import deque
class PhoneDirectory:
    def __init__(self, maxNumbers: int):
        self.new = deque()
        self.used = set()
        for i in range(maxNumbers):
            self.new.append(i)

    def get(self) -> int:
    # Time Complexity: O(1)
        if len(self.new) > 0:
            num = self.new.popleft()
            self.used.add(num)
            return num
        return -1

    def check(self, number: int) -> bool:
    # Time Complexity: O(1)
        if number not in self.used:
            return True
        return False        

    def release(self, number: int) -> None:
    # Time Complexity: O(1)
        if number in self.used:
            self.used.remove(number)
            self.new.append(number)
        

# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)