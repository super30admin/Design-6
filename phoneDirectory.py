'''
Time Complexity: O(1) for all operations
Space Complexity:O(n) for queue
Did this code successfully run on Leetcode : Yes
Explanation:
'''
class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        """
        self.queue = []
        self.set = set()

        for i in range(0, maxNumbers):
            self.queue.append(i)

    def get(self) -> int:
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """

        if len(self.queue) != 0:
            number = self.queue[0]

            self.queue = self.queue[1:]

            self.set.add(number)

            return number
        else:
            return -1

    def check(self, number: int) -> bool:
        """
        Check if a number is available or not.
        """
        return number not in self.set

    def release(self, number: int) -> None:
        """
        Recycle or release a number.
        """
        if number in self.set:
            self.set.remove(number)
            self.queue.append(number)

# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)