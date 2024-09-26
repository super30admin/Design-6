"""
Time complexity O(1)
Space complexity O(N) where N us MaxNumbers


"""
from collections import deque 
class PhoneDirectory:
    
    def __init__(self, maxNumbers):
        self.q=deque()
        self.available = set()
        
        for i in range(maxNumbers):
            self.available.add(i)
            self.q.append(i)
        
        
    def get(self) -> int:
    #Gives a number that is not assigned to anyone. return -1 if not available
        if self.q:
            num=self.q.popleft()
            self.available.remove(num)
            return num
        return -1


    
    def check(self, number: int) -> bool:
        """
        Check if a number is available or not
        
        """
        return number in self.available
        
    
    
    def release(self, number: int) -> None:
        #put the number back to q and set for others to use 
        if number not in self.available:
            self.available.add(number)
            self.q.append(number)
        

        

        
        
# Your PhoneDirectory object will be instantiated and called as such:
maxNumbers=10
number=5
obj = PhoneDirectory(maxNumbers)
param_1 = obj.get()
print(param_1)
param_2 = obj.check(number)
print(param_2)
obj.release(number)


        
        
        

        
        