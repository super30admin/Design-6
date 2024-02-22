class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.slots_available = set(range(maxNumbers))
        
    def get(self) -> int:
        if not self.slots_available:
            return -1
        
        return self.slots_available.pop()
        

    def check(self, number: int) -> bool:
        
        return number in self.slots_available
        

    def release(self, number: int) -> None:
        
        self.slots_available.add(number)
        


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)
