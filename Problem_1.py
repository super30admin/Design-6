from collections import deque


class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.maxNumbers = maxNumbers
        self.bookedNumber = set()
        self.dq = deque()
        for i in range(maxNumbers):
            self.dq.append(i)

    def get(self) -> int:
        if not self.dq:
            return -1
        newNumber = self.dq.popleft()
        self.bookedNumber.add(newNumber)
        return newNumber

    def check(self, number: int) -> bool:
        return number not in self.bookedNumber

    def release(self, number: int) -> None:
        if self.check(number):
            return
        self.bookedNumber.remove(number)
        self.dq.append(number)


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)
