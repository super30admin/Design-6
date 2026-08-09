## Problem1 Design Phone Directory (https://leetcode.com/problems/design-phone-directory/)
class PhoneDirectory:
    """
    A phone directory system that manages available phone numbers.
    Numbers are allocated from a pool of available numbers ranging from 0 to maxNumbers-1.
    """

    def __init__(self, max_numbers: int) -> None:
        """
        Initialize the phone directory with a pool of available numbers.

        Args:
            max_numbers: The maximum count of phone numbers (0 to max_numbers-1)
        """
        # Create a set containing all available phone numbers from 0 to max_numbers-1
        self.available = set(range(max_numbers))

    def get(self) -> int:
        """
        Allocate and return an available phone number.

        Returns:
            An available phone number if one exists, otherwise -1
        """
        # Check if there are any available numbers
        if not self.available:
            return -1

        # Remove and return an arbitrary number from the available set
        return self.available.pop()

    def check(self, number: int) -> bool:
        """
        Check if a specific phone number is available.

        Args:
            number: The phone number to check

        Returns:
            True if the number is available, False otherwise
        """
        # Check if the number exists in the available set
        return number in self.available

    def release(self, number: int) -> None:
        """
        Release a phone number back to the available pool.

        Args:
            number: The phone number to release
        """
        # Add the number back to the available set
        # Note: If number is already available, set.add() will have no effect
        self.available.add(number)


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)
