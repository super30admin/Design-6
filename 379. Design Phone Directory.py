##########379. Design Phone Directory###################################################################################
// Time Complexity : get -> O(1), check -> O(1), release -> O(1)
// Space Complexity : O(n) for queue and set
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : no problem

// Your code here along with comments explaining your approach in three sentences only
We have used 2 data structure hashes and a queue, hashes maintain all the unused number and queue also hold all unused number when we use get we fetch 1 element form queue and remove element form set also, to perform check we just check in set and to release first check if number is preset in set if not preset add the number to queue and set

class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        #self.maxNumbers=maxNumbers
        self.setchk=set(range(maxNumbers))
        self.queueno=collections.deque()
        for i in range(maxNumbers):
            self.queueno.append(i)
        

    def get(self) -> int:
        if len(self.setchk)==0:
            return -1
        else:
            noget=self.queueno.popleft()
            self.setchk.remove(noget)
            return noget
        

    def check(self, number: int) -> bool:
        if number in self.setchk:
            return True
            
            
        

    def release(self, number: int) -> None:
        if number not in self.setchk:
            self.setchk.add(number)
            self.queueno.appendleft(number)
        


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)
        

        
        
