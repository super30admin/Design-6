""""// Time Complexity : O(1)
// Space Complexity :O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :
"""
from collections import deque
class Phonedirectory:
    def __init__(self,maxnum):
        self.q=deque()
        self.hset=set()
        for i in range(maxnum):
            self.q.append(i)
            self.hset.add(i)

    def get(self):
        if len(self.q)==0:
            return -1
        num=self.q.popleft()
        self.hset.remove(num)
        return num

    def check(self,num):
        if num in self.hset:
            return True
        return False

    def release(self,num):
        if num not in self.hset:
            self.hset.add(num)
            self.q.append(num)




directory=Phonedirectory(3)
print(directory.get())
print(directory.check(2))
print(directory.get())
print(directory.check(2))
directory.release(2)
print(directory.check(2))
