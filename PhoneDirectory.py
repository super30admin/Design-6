# Time Complexity : O(1)
# Space Complexity : O(n)


from collections import deque

class PhoneDirectory:

    def __init__(self, maxNumbers: int):

        self.q = deque()
        self.s = set()

        for i in range(maxNumbers):
            self.q.append(i)

    def get(self) -> int:

        if len(self.q) <= 0:
            return -1

        num = self.q.popleft()
        self.s.add(num)
        return num

    def check(self, number: int) -> bool:

        if number not in self.s:
            return True
        return False        

    def release(self, number: int) -> None:

        if number in self.s:
            self.s.remove(number)
            self.q.append(number)