from typing import collections
class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.q = collections.deque()
        self.s = set()
        for i in range(maxNumbers):
            self.q.append(i)
            self.s.add(i)
    
    def get(self) -> int:
        if not self.s: return -1
        curr = self.q.popleft()
        if curr in self.s:
            self.s.remove(curr)
        return curr

    def check(self, number: int) -> bool:
        return True if number in self.s else False
        
    def release(self, number: int) -> None:
        if number not in self.s:
            self.s.add(number)
            self.q.append(number)

# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)