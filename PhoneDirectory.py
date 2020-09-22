'''
Solution:
1.  The main idea is to maintain two Data structures -- one is to maintain assigned numbers 
    in a HashSet and the other is to maintain available numbers in a Queue.
2.  Assign new numbers from available Queue and mark it assigned in the HashSet.
3.  If released => remove from HashSet and place it in Queue for availabilty.

Time Complexity:    O(1) all operations
Space Complexity:   O(N)


--- Passed all testcases successfully on Leetcode.
'''


from collections import deque

class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        """
        #   initializations
        self.queue = deque([])
        self.assigned = set()
        
        #   add all numbers as available to the Queue
        for i in range(maxNumbers):
            self.queue.append(i)
        

    def __isEmpty(self) -> int:

        #   check the presence in Queue
        return (len(self.queue) <= 0)
    
    def get(self) -> int:
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """

        #   if empty => -1
        if self.__isEmpty():
            return -1
        
        #   else => take the first available number from Queue and put it in HashSet
        firstEntry = self.queue.popleft()
        self.assigned.add(firstEntry)
        return firstEntry

    def check(self, number: int) -> bool:
        """
        Check if a number is available or not.
        """
        #   check the presence in HashSet and revert the bool value
        return not (number in self.assigned)
        

    def release(self, number: int) -> None:
        """
        Recycle or release a number.
        """
        
        #   remove from HashSet and put it in Queue
        if (number in self.assigned):
            self.queue.append(number)
            self.assigned.remove(number)


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)