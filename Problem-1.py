# Time Complexity : O(log n) for get/return operations using a heap
# Space Complexity : O(n), to store available numbers
# Did this code successfully run on Leetcode : No
# Any problem you faced while coding this : No

# Explanation:
# 1. Use a min-heap to track available phone numbers for O(log n) allocation.
# 2. Use a set to track which numbers are in use.
# 3. get() returns the smallest available number.
# 4. check() verifies if a number is available.
# 5. release() returns a number back to the pool.

import heapq

class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.available = list(range(maxNumbers))
        heapq.heapify(self.available)
        self.in_use = set()

    def get(self) -> int:
        if not self.available:
            return -1
        num = heapq.heappop(self.available)
        self.in_use.add(num)
        return num

    def check(self, number: int) -> bool:
        return number not in self.in_use

    def release(self, number: int) -> None:
        if number in self.in_use:
            self.in_use.remove(number)
            heapq.heappush(self.available, number)