# Time Complexity : O(1)
# Space Complexity : O(N)
class PhoneDirectory:
    def __init__(self, maxNumbers: int):
        self.q = deque()
        self.hset = set()
        # Allocate numbers and add those to the queue
        # as well as set
        for i in range(maxNumbers):
            # heap allows getting next available number
            self.q.append(i)
            # set allows O(1) check operation
            self.hset.add(i)

    def get(self) -> int:
        # if there are no more numbers in q
        if not self.q: return -1
        re = self.q.popleft()  # O(1)
        self.hset.remove(re)  # O(1)
        return re

    def check(self, number: int) -> bool:
        return number in self.hset

    def release(self, number: int) -> None:
        if number not in self.hset:
            self.hset.add(number)
            self.q.append(number)

# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)