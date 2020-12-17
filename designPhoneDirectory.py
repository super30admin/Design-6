#Time Complexity : O(1)
#Space Complexity : O(n) where n is the given maxNumbers
#Did this code successfully run on Leetcode : Yes

class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        """
        self.queue = deque()
        self.available = set()
        for i in range(maxNumbers):
            self.queue.append(i)
            self.available.add(i)

    def get(self) -> int:
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """
        if self.queue:
            number = self.queue.popleft()
            self.available.remove(number)
            return number
        return -1

    def check(self, number: int) -> bool:
        """
        Check if a number is available or not.
        """
        return number in self.available


    def release(self, number: int) -> None:
        """
        Recycle or release a number.
        """
        if number not in self.available:
            self.available.add(number)
            self.queue.append(number)



# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)
