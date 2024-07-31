#I created a system to manage phone numbers efficiently within a specified range. The __init__ method initializes the directory with a maximum number of available phone numbers and uses a set released to track numbers that have been released and are available for reuse. The get method retrieves an available number either from the released set or by incrementing an index if no numbers are currently available. The check method verifies if a number is available, either by checking if it's in the released set or if it is within the range of allocated numbers. The release method adds a number back to the released set if it is within the valid range. All operations (get, check, and release) have a time complexity of O(1), and the space complexity is O(N), where N is the number of released numbers stored in the released set.

class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.released = set()
        self.idx = 0
        self.limit = maxNumbers
    
    # O(1)
    def get(self) -> int:
        if self.released:
            return self.released.pop()
        if self.idx == self.limit:
            return -1
        self.idx += 1
        return self.idx - 1

    # O(1)
    def check(self, number: int) -> bool:
        if number in self.released:
            return True
        if number >= self.idx:
            return True
        return False
        
    # O(1)
    def release(self, number: int) -> None:
        if number < self.idx:
            self.released.add(number)