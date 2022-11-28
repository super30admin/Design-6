# Time Complexity : O(1)
# Space Complexity : O(N)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

from collections import deque


class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.q = deque()
        self.hset = set()
        # Allocate numbers and add those to the queue
        # as well as set
        for i in range(maxNumbers):
            # heap allows getting next available number
            self.q.append(i)
            # set allows O(1) check operation
            self.hset.add(i)

    def get(self) -> int:  # O(1)
        # if there are no more numbers in q
        if not self.q: return -1
        re = self.q.popleft()  # O(1)
        self.hset.remove(re)  # O(1)
        return re

    def check(self, number: int) -> bool:  # O(1)
        return number in self.hset

    def release(self, number: int) -> None:  # O(1)
        if number not in self.hset:
            self.hset.add(number)
            self.q.append(number)