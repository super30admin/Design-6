# time complexity is o(n), where n is the size of the input
# space complexity is o(n), where n is the size of the input
class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        from collections import deque
        self.q = deque()
        self.numSet = set()
        for num in range(maxNumbers):
            self.q.append(num)
            if(num not in self.numSet):
                self.numSet.add(num)

    def get(self) -> int: #time o(1)
        if(len(self.q) > 0):
            temp = self.q.popleft()
            if(temp in self.numSet):
                self.numSet.remove(temp)
            return temp
        else:
            return -1
    
    def check(self, number: int) -> bool: #time o(1)
        if(number in self.numSet):
            return True
        else:
            return False
    
    def release(self, number: int) -> None: #time o(1)
        if(number not in self.numSet):
            self.q.append(number)
            self.numSet.add(number)
        


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)