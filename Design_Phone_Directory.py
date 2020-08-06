Time complexity --> o(1) for get,check and release.
space complexity --> o(1) for get,check and release.

// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None


// Your code here along with comments explaining your approach

we use a queue and a set here.we are storing all the unassigned numbers to the queue and the assigned numbers to the set.
for get we pop the first element from the queue and sent it to the set.
for check we see if the number is present in the set or not.if yes then number is assigned.
for release we remove it from the set and add it to the queue indicating number is not assigned.

from collections import deque
class PhoneDirectory(object):

    def __init__(self, maxNumbers):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        :type maxNumbers: int
        """
        self.queue=deque()
        self.s=set()
        for i in range(maxNumbers):
            self.queue.append(i)
        

    def get(self):
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        :rtype: int
        """
        if len(self.queue)==0:
            return -1
        ele=self.queue.popleft()
        self.s.add(ele)
        return ele

    def check(self, number):
        """
        Check if a number is available or not.
        :type number: int
        :rtype: bool
        """
        if number in self.s:
            return False
        return True
        

    def release(self, number):
        """
        Recycle or release a number.
        :type number: int
        :rtype: None
        """
        if number in self.s:
            self.s.remove(number)
            self.queue.append(number)
        # print(self.s,self.queue)
        


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)