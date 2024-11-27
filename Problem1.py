# 379. Design Phone Directory
class PhoneDirectory:
    def __init__(self, maxNumbers: int):
        self.used = set()
        self.q = deque()

        for i in range(0,maxNumbers):
            self.q.append(i)
        
    def get(self) -> int:
        if len(self.q) == 0: return -1
        num = self.q.popleft()
        self.used.add(num)
        return num
        
    def check(self, number: int) -> bool:
        if number in self.used: return False
        return True
        
    def release(self, number: int) -> None:
        if number in self.used:
            self.used.remove(number)
            self.q.append(number)

        


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)