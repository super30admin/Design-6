#All TC on leetcode could not be tested as its premium problem

class PhoneDirectory:

    def __init__(self, maxSlots):
        self.stack = []
        self.s = set()
        for i in range(maxSlots):
            self.stack.append(i)
            self.s.add(i)

    #Here we return the num available from top of the stack
    #Time complexity - O(1)
    #Space complexity - O(1)
    def get(self):
        if len(self.s)<=0:
            return -1
        num = self.stack.pop()
        self.s.remove(num)
        return num

    #Here we check if the num is available in set
    # Time complexity - O(1)
    # Space complexity - O(1)
    def check(self, num):
        return num in self.s

    # Here we check if the num is not available in set and then add to to set and stack
    # Time complexity - O(1)
    # Space complexity - O(1)
    def release(self, num):
        if num not in self.s:
            self.s.add(num)
            self.stack.append(num)


pd = PhoneDirectory(3)
print(pd.get())
print(pd.get())
print(pd.check(0))
print(pd.get())
print(pd.check(0))
print(pd.release(1))
print(pd.check(1))