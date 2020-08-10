#Time Complexity - O(1)
#Space Complexity - O(1)
from collections import deque
class PhoneDirectory(object):

    def __init__(self, maxNumbers):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        :type maxNumbers: int
        """
        self.size = maxNumbers
        self.q = deque()
        self.vis = set()
        for i in range(self.size):
            self.q.append(i)

    def get(self):
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        :rtype: int
        """
        if self.size > 0:
            self.size = self.size - 1
            num = self.q.popleft()
            self.vis.add(num)
            return num
        return -1
        

    def check(self, number):
        """
        Check if a number is available or not.
        :type number: int
        :rtype: bool
        """
        return number not in self.vis

    def release(self, number):
        """
        Recycle or release a number.
        :type number: int
        :rtype: None
        """
        if number in self.vis:
            self.vis.remove(number)
            self.q.append(number)
            self.size = self.size + 1


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)