'''
TC: O(1)
SC: O(n)
'''
from collections import deque

class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.numbers = set()
        self.queue = deque()
        
        for i in range(maxNumbers):
            self.numbers.add(i)
            self.queue.append(i)

    def get(self) -> int:
        if not self.queue:
            return -1
        num = self.queue.popleft()
        self.numbers.remove(num)
        return num
        
    def check(self, number: int) -> bool:
        return number in self.numbers
        

    def release(self, number: int) -> None:
        if number not in self.numbers:
            self.queue.append(number)
            self.numbers.add(number)
        


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)