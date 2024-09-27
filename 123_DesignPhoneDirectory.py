class PhoneDirectory():
    def __init__(self, maxNumbers):
        self.directory = set()

        for i in range(maxNumbers):
            self.directory.add(i)
        print(self.directory)
    def get(self):
        if len(self.directory) == 0:
            return -1
        else:
            return self.directory.pop()
    def check(self,number):
        # print(self.directory)
        if number in self.directory:
            return True
        else:
            return False
    def release(self,number):
        self.directory.add(number)
        # print(self.directory)

dir = PhoneDirectory(3)
print(dir.get())
print(dir.get())
print(dir.check(2))
print(dir.get())
print(dir.check(2))
print(dir.release(2))
print(dir.check(2))


