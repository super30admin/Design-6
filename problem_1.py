# TC - O(1)
# SC - O(1)

class PhoneDirectory:
    def __init__(self, maxNumbers: int):
        self.hashset = set()
        self.q = collections.deque()
        for i in range(maxNumbers):
            self.q.append(i)
            self.hashset.add(i)

    def get(self) -> int:
        if not self.q :
            return -1 
        else :
           val = self.q.popleft()
           self.hashset.remove(val)
           return val
        
    def check(self, number: int) -> bool:
        return number in self.hashset
        
    def release(self, number: int) -> None:
        if number not in self.hashset:
            self.hashset.add(number)
            self.q.append(number)
        
# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)