# Maintain a queue with all max numbers given, set will be used for those number assigned only. 
# Get - if numbers are available in queue pop from it and return it to user, also need to be added to set to mark as assigned
# Check - if assigned or not, check in set
# Release - only if present in set(ie assigned), remove from set and add back to queue denoting it is available
class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        """

        # maintain a queue for all numbers given
        # maintain set for only those which have been assigned

        self.numbers_available = deque()
        self.assigned_numbers = set()

        for number in range(maxNumbers):
            self.numbers_available.append(number)


    def get(self) -> int: # O(1)
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """
        if not self.numbers_available:
            return -1

        # pop from queue and add to assigned set

        number = self.numbers_available.popleft()
        self.assigned_numbers.add(number)
        return number


    def check(self, number: int) -> bool: 
        """
        Check if a number is available or not.
        """
        if number not in self.assigned_numbers:
            return False
        
        return True
        


    def release(self, number: int) -> None: # O(1)
        """
        Recycle or release a number.
        """
        if not self.check(number):
            return

        self.assigned_numbers.remove(number)
        self.numbers_available.append(number)