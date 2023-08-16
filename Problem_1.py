"""
Problem : 1

Time Complexity : O(1)
Space Complexity : O(n)

Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No

Initializing a queue of size maxNumber with numbers ranging from 0 to maxNumbers, and also adding this numbers to a hashset
whenever a new number is requested, checking if we have number left, if yes, taking out the first number in the queue and also removing
the number from hashset, if a number is released, just adding that number again the hashset and in the queue

"""


# Design Phone Directory

class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.q=collections.deque()
        self.hashset=set()
        for i in range(0,maxNumbers):
            self.hashset.add(i)
            self.q.append(i)
        

    def get(self) -> int:
        if self.q:
            re=self.q.popleft()
            self.hashset.remove(re)
            return re
        else:
            return -1
        

    def check(self, number: int) -> bool:
        return True if number in self.hashset else False
        

    def release(self, number: int) -> None:
        if number not in self.hashset:
            self.hashset.add(number)
            self.q.append(number)
        


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)