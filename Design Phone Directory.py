from collections import deque

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
        self.de = deque()       #could have used a stack too

    def get(self) -> int:
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """
        if self.released:
            popped = self.de.popleft()
            self.released.remove(popped)
            return popped
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
        if self.next > number and number not in self.released:
            self.de.append(number)
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
        self.de = deque(range(maxNumbers))    #could have used a stack too

    def get(self) -> int:
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """
        if self.numbers:
            popped = self.de.popleft()
            self.numbers.remove(popped)
            return popped
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
        if number not in self.numbers:
            self.de.append(number)
            self.numbers.add(number)
        return
'''

#Solution 3
'''
class PhoneDirectory:
    #Approach: HashSet
    #Time Complexity: O(n) get; O(1) init, check, release
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
'''

#Solution 4
'''
class PhoneDirectory:
    #Approach: HashSet
    #Time Complexity: O(n) get, init; O(1) check, release
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