# Time Complexity : O()
# Space Complexity : O()
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

#mainitaining all the remaining numbers in stack and allocating when needed
# allocated numbers are maintained in hashset
class Solution:

    def __init__(self,capacity):

        self.alloted = {}
        self.queue = []
        self.capacity = capacity

        for i in capacity:
            self.queue.append(i)
    
    def get(self):
        if len(self.queue) == 0:
            return -1
        
        a = self.queue.pop()
        self.alloted[a] = True

        return a
    
    def check(self,num):

        return num in self.alloted
    

    def release(self,num):
        if num not in self.alloted:
            return -1
        del self.alloted[num]
        self.queue.append(num)

    


    