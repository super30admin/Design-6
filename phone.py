# Time Complexity : O(1) amortized for get, check and release
# Space Complexity : O(N) where N is the size of queue and set till maxNumbers
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Your code here along with comments explaining your approach:
# I am creating a set to store the seen numbers and a queue to store the available numbers
# In the constructor, I am adding all the numbers from 0 to maxNumbers to the queueso that they are available to be assigned
# In the get function, I am checking if the queue is empty, if it is then if is empty that means there are no available numbers so I return -1
# If the queue is not empty, I pop the leftmost number from the queue and add it to the seen set and return that number
# In the check function, I am checking if the number is in the seen set, if it is then I return False as the number is not available
# If the number is not in the seen set, I return True as the number is available
# In the release function, I am checking if the number is in the seen set, if it is then I remove it from the seen set and append it back to the queue so that it is available again.

from collections import deque

class PhoneDirectory:
            
    def __init__(self, maxNumbers: int):
        self.seen = set()
        self.q = deque()
        for i in range(maxNumbers):
            self.q.append(i)
    def get(self) -> int:
        if not self.q:
            return -1
        curr = self.q.popleft()
        self.seen.add(curr)
        return curr
    
    def check(self, number: int) -> bool:
        if number in self.seen:
            return False
        return True
        

    def release(self, number: int) -> None:
        if number in self.seen:
            self.seen.remove(number)
            self.q.append(number)
        


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)