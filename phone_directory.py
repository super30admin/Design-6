# Time Complexity : O(1) for all operations
# Space Complexity : O(n) n is max numbers
# Did this code successfully run on Leetcode : Yes
# I use a queue to store all the numbers. For get, I remove number from queue and add to set. For check I check if number is in set, If found return false.
# for release, I remove number from set and add to the queue

from collections import deque
class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        """
        self.occupied = set()
        self.available = deque()
        for i in range(maxNumbers):
            self.available.append(i)
        

    def get(self) -> int:
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """
        if len(self.available) > 0:
            number = self.available.popleft()
            self.occupied.add(number)
            return number
        return -1
        

    def check(self, number: int) -> bool:
        """
        Check if a number is available or not.
        """
        return number not in self.occupied

    def release(self, number: int) -> None:
        """
        Recycle or release a number.
        """
        if number in self.occupied:
            self.occupied.remove(number)
            self.available.append(number)
