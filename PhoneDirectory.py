# Time Complexity : get() : O(1), check() : O(1), release() : O(1)
# Space Complexity : O(n) for queue and set storing up to maxNumbers
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
# Approach : All numbers start out in a queue and a set that tracks the unused ones.
# When we call get(), a number is removed from both the queue and the set.
# When we release(), the number goes back into both the queue and the unused set—unless it’s already unused.

class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.q = deque()
        self.unused = set()

        for i in range(maxNumbers):
            self.q.append(i)
            self.unused.add(i)

    def get(self) -> int:
        if not self.q:
            return -1
        num = self.q.popleft()
        self.unused.remove(num)
        return num

    def check(self, number: int) -> bool:
        return number in self.unused

    def release(self, number: int) -> None:
        if number in self.unused:
            return
        self.unused.add(number)
        self.q.append(number)

# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)