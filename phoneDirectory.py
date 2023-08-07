class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.buf = [None] * maxNumbers
        for i in range(0, maxNumbers):
            self.buf[i] = (i+1) % maxNumbers
        self.next = 0
    def get(self) -> int:
        if(self.buf[self.next] == -1):
            return -1
        
        # Remember the number we want to return
        to_return = self.next
        
        # Figure out the next available number
        self.next = self.buf[self.next] # update next ptr
        
        # Mark the number as used
        self.buf[to_return] = -1 # mark number as used
        
        return to_return
    def check(self, number: int) -> bool:
        return self.buf[number] != -1

    def release(self, number: int) -> None:
        if(not self.check(number)):
            self.buf[number] = self.next
            self.next = number


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)
# T.C.=S.C=O(1)