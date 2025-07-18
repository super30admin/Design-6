"""
Use a queue to track all available phone numbers
Use a set to keep track of numbers currently in use
On get(), pop from queue and mark as used; on release(), recycle the number
"""
"""
Time Complexity : O(1)
Space Complexity : O(N) — We store up to N elements in the queue and set
"""


from collections import deque

class phoneDirect:

    def __init__(self, maxNumbers: int):
        self.available = deque(range(maxNumbers))  
        self.in_use = set()                     
        self.maxNumbers = maxNumbers

    def get(self) -> int:
        if self.available:
            num = self.available.popleft()
            self.in_use.add(num)
            return num
        return -1

    def check(self, number: int) -> bool:
        return number not in self.in_use and 0 <= number < self.maxNumbers

    def release(self, number: int) -> None:
        if number in self.in_use:
            self.in_use.remove(number)
            self.available.append(number)


if __name__ == "__main__":
    directory = phoneDirect(3)   

    print(directory.get())         
    print(directory.get())         
    print(directory.check(2)) 
    print(directory.get())         
    print(directory.check(2))      
    directory.release(2)            
    print(directory.check(2))       
    print(directory.get())         
