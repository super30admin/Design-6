class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.stack = []
        self._set = set()

        for i in range(0, maxNumbers):
            self.stack.append(i)
            self._set.add(i)

    def get(self) -> int:
        if len(self.stack) == 0:
            return -1

        number = self.stack.pop()
        self._set.remove(number)

        return number

    def check(self, number: int) -> bool:
        return number in self._set

    def release(self, number: int) -> None:
        if number in self._set:
            return

        self.stack.append(number)
        self._set.add(number)

# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)