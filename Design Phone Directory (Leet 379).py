'''
Using Hashset and Queue
Time: O(1), all functions
Space: O(1)
'''

class PhoneDirectory:
    
    def __init__(self, maxNumbers: int):
        self.hs = set()
        self.li = list()
        
        for i in range(maxNumbers):
            self.hs.add(i)
            self.li.append(i)
    
        
    def get(self) -> int:
        if len(self.li) > 0:
            num = self.li[0]
            self.li.pop(0)
            self.hs.remove(num)
            return num
        return -1
        

    def check(self, number: int) -> bool:
        return number in self.hs
        

    def release(self, number: int) -> None:
        if number not in self.hs:
            self.hs.add(number)
            self.li.append(number)
        


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)