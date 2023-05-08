# Time Complexity : O(1) for all methods
# Space Complexity : O(maxNumbers)
class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.max_numbers = maxNumbers
        self.available_numbers = set(range(maxNumbers))
        self.used_numbers = set()

    def get(self) -> int:
        if not self.available_numbers:
            return -1
        number = self.available_numbers.pop()
        self.used_numbers.add(number)
        return number

    def check(self, number: int) -> bool:
        return number in self.available_numbers

    def release(self, number: int) -> None:
        if number in self.used_numbers:
            self.used_numbers.remove(number)
            self.available_numbers.add(number)
