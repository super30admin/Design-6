# Time Complexity : O(1) --> UserLevel; O(n) --> constructor.
# Space Complexity : O(1) --> UserLevel; O(n) --> constructor.
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
#
#
# Your code here along with comments explaining your approach
from collections import deque


class PhoneDirectory:
    def __init__(self, maxNumbers):
        self.dictu = set()
        self.queue = deque()
        for i in range(maxNumbers):
            self.dictu.add(i)
            self.queue.append(i)

    def get(self):
        if len(self.dictu) == 0:
            return -1
        get = self.queue.popleft()
        self.dictu.remove(get)
        return get

    def check(self, number):
        return number in self.dictu

    def release(self, number):
        if number not in self.dictu:
            self.queue.append(number)
            self.dictu.add(number)


check = PhoneDirectory(4)
print(check.get())
print(check.get())
print(check.check(2))
print(check.get())
print(check.check(2))
print(check.release(2))
print(check.check(2))
print(check.get())
