# Explain your approach briefly:
# The PhoneDirectory class is implemented using a set to keep track of available phone numbers. 
# We maintain a set of available numbers for efficient access and removal.
# - get(): Retrieves a number from the available pool and removes it.
# - check(): Checks if a number exists in the available pool.
# - release(): Adds the number back to the available pool.

# Time Complexity:
# - get(): O(1)
# - check(): O(1)
# - release(): O(1) 
# Space Complexity: O(maxNumbers)
# Did this code successfully run on Leetcode: Yes
# Any problem you faced while coding this: No

class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        """
        Initialize the PhoneDirectory with a maximum number of slots.
        Use a set to store available numbers for O(1) operations.
        """
        self.available = set(range(maxNumbers))  # All numbers initially available

    def get(self) -> int:
        """
        Provide a number that is not assigned to anyone. Returns -1 if no number is available.
        """
        if not self.available:
            return -1  # No numbers available
        return self.available.pop()  # Retrieve and remove a number from the set

    def check(self, number: int) -> bool:
        """
        Check if a given number is available.
        """
        return number in self.available  # True if the number exists in the set

    def release(self, number: int) -> None:
        """
        Release a previously assigned number back to the available pool.
        """
        self.available.add(number)  # Add the number back to the set if not already present

# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)
