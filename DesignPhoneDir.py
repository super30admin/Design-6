# Time O(1), Space O(n)
class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.hashset = set()
        for i in range(maxNumbers):
            self.hashset.add(i)

    def get(self) -> int:
        if len(self.hashset):
            return self.hashset.pop()
        return -1
        
        

    def check(self, number: int) -> bool:
        if number in self.hashset:
            return True
        return False
        

    def release(self, number: int) -> None:
        self.hashset.add(number)
        


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)