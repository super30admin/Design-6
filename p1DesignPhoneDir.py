
# init:     Time: O(n), Space: O(n)
# get:      Time: O(1), Space: O(1)
# check:    Time: O(1), Space: O(1)
# release:  Time: O(1), Space: O(1)

class PhoneDirectory(object):
    def __init__(self, maxNum):
        self.curr = 0
        self.numbers = range(maxNum)
        self.used = [False] * maxNum
    def get(self):
        if self.curr == len(self.numbers): return -1
        number = self.numbers[self.curr]
        self.curr +=1
        self.used[number] = True
    def check(self, number):
        return True if number in self.numbers and not self.used[number] else False
    def release(self,number):
        if not self.check(number):
            return
        self.used[number] = False
        self.curr -=1
        self.numbers[self.curr] = number

obj = PhoneDirectory(4)
obj.get()
print(obj.check(0))