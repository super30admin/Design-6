# Space complexity : O(maxNumbers)
# Leetcode : Solved and submitted

from collections import deque
class PhoneDirectory:

    def __init__(self, maxNumbers: int):
       # maintain a queue and set for available numbers to avoid duplicacy
        self.maxNumbers = maxNumbers
        self.nums = set()
        self.q = deque([])
        for i in range(maxNumbers):
            self.nums.add(i)
            self.q.append(i)

    # Time - O(1)
    def get(self) -> int:
        # if the len of the q is 0, which means we do not have any available numbers
        if len(self.q) == 0:
            return -1
        # pop the first number from queue and also from the set
        num = self.q.popleft()
        #if num in self.nums:
        self.nums.remove(num)
        # return the number
        return num

    # Time - O(n)
    def check(self, number: int) -> bool:
        # checking if the number is present in the set or not, wbich means if it is use or not
        if number not in self.nums:
            return False
        return True

    # Time - O(1)
    def release(self, number: int) -> None:
       # release the number from use, and add it to the queue and set so that it can be availble to use
        if number not in self.nums:
            self.nums.add(number)
            self.q.append(number)


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)
