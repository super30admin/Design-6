# Runs on Leetcode
      # Runtime - O(1) for all operations
      # Space - O(n), used one array and one queue

class PhoneDirectory:
    import collections
    def __init__(self, maxNumbers: int):
        if not maxNumbers:
            return
        self.maxNumbers = maxNumbers
        self.phone_numbers = [1 for i in range(maxNumbers)]
        self.available = collections.deque([i for i in range(maxNumbers)])

    def get(self) -> int:
        if not self.available:
            return -1
        else:
            temp = self.available.popleft()
            self.phone_numbers[temp] = 0
            return temp

    def check(self, number: int) -> bool:
        if 0 <= number < self.maxNumbers:
            return self.phone_numbers[number]

    def release(self, number: int) -> None:
        if 0 <= number < self.maxNumbers and self.phone_numbers[number] == 0:
            self.phone_numbers[number] = 1
            self.available.append(number)
        else:
            return
