# Time Complexity: O(1)
# Space Complexity: O(n)
class PhoneDirectory:
    # Defining the set and with all numbers till max numbers
    def __init__(self, maxNumbers: int):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        """

        self.set = set()
        for i in range(maxNumbers):
            self.set.add(i)

    # It will check if the ste is not empty and if not then return top element
    def get(self) -> int:
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """
        if not self.set:
            return -1
        return self.set.pop()

    #  we will chekc if number is present in set if so then return true or false
    def check(self, number: int) -> bool:
        """
        Check if a number is available or not.
        """
        if number in self.set:
            return True

        return False

    # add given number in the set
    def release(self, number: int) -> None:
        """
        Recycle or release a number.
        """
        self.set.add(number)

# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)