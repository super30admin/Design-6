class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.q = deque(range(maxNumbers))
        self.used = set()

    def get(self) -> int:
        if not self.q:
            return -1
        
        num = self.q.popleft()
        self.used.add(num)
        return num

    def check(self, number: int) -> bool:
        return not number in self.used

    def release(self, number: int) -> None:
        if number in self.used: 
            self.used.remove(number)
            self.q.append(number)



# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)
