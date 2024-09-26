# Time - get,check,release - O(1) all three
# Space - O(N)
# using hashset datastructure



class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        """
        self.available = set(range(maxNumbers))

    def get(self) -> int:
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """
        if self.available:
            return self.available.pop()
        else:
            return -1
        

    def check(self, number: int) -> bool:
        """
        Check if a number is available or not.
        """
        if number in self.available:
            return True
        else:
            False
        

    def release(self, number: int) -> None:
        """
        Recycle or release a number.
        """
        self.available.add(number)
