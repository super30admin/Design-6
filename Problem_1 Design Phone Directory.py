# // Time Complexity : O(1)
# // Space Complexity : O(n)
# // Did this code successfully run on Leetcode : Yes
# // Any problem you faced while coding this : No
#
#
# // Your code here along with comments explaining your approach

class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        """
        self.occupied = set()
        self.available = deque()
        for i in range(maxNumbers):
            self.available.append(i)

    def get(self) -> int:
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """
        if len(self.available) > 0:
            number = self.available.popleft()
            self.occupied.add(number)
            return number
        return -1

    def check(self, number: int) -> bool:
        """
        Check if a number is available or not.
        """
        return number not in self.occupied

    def release(self, number: int) -> None:
        """
        Recycle or release a number.
        """
        if number in self.occupied:
            self.occupied.remove(number)
            self.available.append(number)

# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)