#Time Complexity : O(1)
#Space Complexity :O(N)
#Did this code successfully run on Leetcode :No
#Any problem you faced while coding this : No

from collections import deque

class PhoneDirectory:
    def __init__(self, maxNumbers: int):
        self.maxNumbers = maxNumbers
        self.available = set(range(maxNumbers))
        self.used = set()
        self.released = deque()

    def get(self) -> int:
        if not self.available:
            return -1
        num = self.available.pop()
        self.used.add(num)
        return num

    def check(self, number: int) -> bool:
        return number in self.available

    def release(self, number: int) -> None:
        if number in self.used:
            self.used.remove(number)
            self.released.append(number)
        elif number in self.released:
            self.released.remove(number)
            self.available.add(number)