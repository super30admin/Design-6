# Time complexity:
# get(): O(1) 
# check(number): O(1)
# release(number): O(1)
# Space complexity:
# O(maxNumbers)
class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.available = set(range(maxNumbers))

    def get(self) -> int:
        if self.available:
            number = self.available.pop()
            return number
        else:
            return -1

    def check(self, number: int) -> bool:
        return number in self.available

    def release(self, number: int) -> None:
        self.available.add(number)
