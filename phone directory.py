# // Time Complexity : O(1)
# // Space Complexity : O(1) ?
# // Did this code successfully run on Leetcode : Yes
# // Any problem you faced while coding this : No


# // Your code here along with comments explaining your approach
# use set and priority queue
from heapq import heappush as insert
from heapq import heappop as pop

class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        """
        # make two data structures heapq and set
        # Store the available numbers in it
        # in heapq store the next minimum avalaible
        self.available = set()
        self.heap =[]
        for i in range(maxNumbers):
            insert(self.heap, i)
            self.available.add(i)

    def get(self) -> int:
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """
        if len(self.heap) == 0:
            return -1
        else:
            x = pop(self.heap)
            self.available.remove(x)
            return x

    def check(self, number: int) -> bool:
        """
        Check if a number is available or not.
        """
        if number in self.available:
            return True
        return False
        
        

    def release(self, number: int) -> None:
        """
        Recycle or release a number.
        """
        if number not in self.available:
            self.available.add(number)
            insert(self.heap,number)
            
            


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)