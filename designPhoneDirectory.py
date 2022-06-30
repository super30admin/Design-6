'''
Time Complexity :O(1) 
Space Complexity :O(n) ---> for queue 
Did this code successfully run on Leetcode : yes
Any problem you faced while coding this :no
'''

from collections import deque
class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        
        # initlize a queue
        self.queue = deque([])

        # enque the elements to the queue
        for i in range(0,maxNumbers):
            self.queue.append(i)
        
        # initlize a set
        self.allocated = set()
        
    def get(self) -> int:
        
        # chk if my queue is not empty
        if len(self.queue) != 0:
            # deque the element from the queue
            ele = self.queue.popleft()
            
            # add the element to the set
            self.allocated.add(ele)
            
            # return the ele
            return ele
        
        # return default
        return -1

    def check(self, number: int) -> bool:
        '''
        If the number is avaialble in the set, return False else return True
        '''        
        if number in self.allocated:
            return False
        return True
        
    def release(self, number: int) -> None:
        '''
        Remove the element from the set and allocate it back into the queue
        '''
        if number in self.allocated:
            # remove the number from the set
            self.allocated.remove(number)
        
            # enque the number back to the queue
            self.queue.append(number)


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)