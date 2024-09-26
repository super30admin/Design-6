# Time Complexity : O(1)
# Space Complexity : O(N)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No


# Your code here along with comments explaining your approach

class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        """
        self.set = set()
        self.queue = deque([])
        for i in range(maxNumbers):
            self.set.add(i)
            self.queue.append(i)

    def get(self) -> int:
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """
        if self.queue:
            res = self.queue.popleft()
            self.set.remove(res)
            return res
        return -1

    def check(self, number: int) -> bool:
        """
        Check if a number is available or not.
        """
        if number in self.set:
            return True
        return False

    def release(self, number: int) -> None:
        """
        Recycle or release a number.
        """
        if number not in self.set:
            self.set.add(number)
            self.queue.append(number)


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)