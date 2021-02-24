#Solution 1
class PhoneDirectory:
    #Approach: HashSet
    #Time Complexity: O(1) for all operations   // including initialization
    #Space Complexity: O(maxNumbers)
    
    def __init__(self, maxNumbers: int):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        """
        self.next, self.max = 0, maxNumbers - 1
        self.released = set()

    def get(self) -> int:
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """
        if self.released:
            return self.released.pop()
        if self.next <= self.max:
            self.next += 1
            return self.next - 1
        return -1

    def check(self, number: int) -> bool:
        """
        Check if a number is available or not.
        """
        return self.next <= number or number in self.released

    def release(self, number: int) -> None:
        """
        Recycle or release a number.
        """
        if self.next > number:
            self.released.add(number)
        return

#Solution 2
'''
class PhoneDirectory:
    #Approach: HashSet
    #Time Complexity: O(1) for all operations   // except the one-time initialization
    #Space Complexity: O(maxNumbers)
    
    def __init__(self, maxNumbers: int):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        """
        self.numbers = set(range(maxNumbers))

    def get(self) -> int:
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """
        if self.numbers:
            return self.numbers.pop()
        return -1

    def check(self, number: int) -> bool:
        """
        Check if a number is available or not.
        """
        return number in self.numbers

    def release(self, number: int) -> None:
        """
        Recycle or release a number.
        """
        self.numbers.add(number)
        return
'''
    
# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)