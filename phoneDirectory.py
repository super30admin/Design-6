"""
Time Complexity: Average O(1) for all the methods
"""
from queue import deque


class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.maxNumbers = maxNumbers
        self.queue = deque()
        self.hashSet = set()
        self.populateNumbers()

    def populateNumbers(self):
        for i in range(0, self.maxNumbers):
            self.queue.append(i)
            self.hashSet.add(i)

    def get(self) -> int:
        if self.queue:
            numberToBeReturned = self.queue.popleft()
            self.hashSet.remove(numberToBeReturned)
            return numberToBeReturned
        return -1

    def check(self, number: int) -> bool:
        if number in self.hashSet:
            return True
        return False

    def release(self, number: int) -> None:
        if number not in self.hashSet:
            self.hashSet.add(number)
            self.queue.append(number)


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)
