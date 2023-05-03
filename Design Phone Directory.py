class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        li = [i for i in range(maxNumbers)]
        self.hset = set(li)
        self.dq = collections.deque(li)

    def get(self) -> int:  # Time Complexity: O(1)
        if not self.dq:
            return -1
        num = self.dq.popleft()
        if num in self.hset:
            self.hset.remove(num)
        return num

    def check(self, number: int) -> bool:  # Time Complexity: O(1)
        if number in self.hset:
            return True
        return False

    def release(self, number: int) -> None:  # Time Complexity: O(1)
        if number not in self.hset:
            self.hset.add(number)
            self.dq.append(number)

# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)