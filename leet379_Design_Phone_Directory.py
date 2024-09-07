class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.maxNumbers = maxNumbers
        self.openSlots = {}
        self.inuseSlots = {}
        self.curNum = 0
        

    def get(self) -> int:
        if self.openSlots:
            for key in self.openSlots:
                del self.openSlots[key]
                self.inuseSlots[key] = True
                return key
        elif self.curNum < self.maxNumbers:
            newNum = self.curNum
            self.curNum += 1
            self.inuseSlots[newNum] = True
            return newNum
        else:
            return -1
        

    def check(self, number: int) -> bool:
        if number in self.inuseSlots:
            return False
        else:
            return True
        

    def release(self, number: int) -> None:
        if number in self.inuseSlots:
            del self.inuseSlots[number]
            self.openSlots[number] = True
        

if __name__ == "__main__":
# Your PhoneDirectory object will be instantiated and called as such:
    maxNumbers = 3
    number = 2
    obj = PhoneDirectory(maxNumbers)
    param_1 = obj.get()
    param_2 = obj.check(number)
    obj.release(number)