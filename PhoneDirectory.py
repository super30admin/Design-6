'''
    Time Complexity:
        O(n) (for the constructor)
        O(1) (for all the other operations)

    Space Complexity:
        O(n)

    Did this code successfully run on LeetCode?:
        Yes

    Problems faced while coding this:
        None

    Approach:
        Maintain a Queue of available numbers and a Set of assigned numbers.
        Get -> Pop a number from available (O(1)) and add it to assigned (O(1)).
        Check -> Check if the number is not in assigned.
        Release -> Remove from assigned (O(1)) and push it available (O(1)).
'''

class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        """
        self.available_numbers = collections.deque()
        self.assigned_numbers = set()

        for number in range(maxNumbers):
            self.available_numbers.append(number)


    def get(self) -> int:
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """
        if not self.available_numbers:
            return -1

        number = self.available_numbers.popleft()
        self.assigned_numbers.add(number)
        return number


    def check(self, number: int) -> bool:
        """
        Check if a number is available or not.
        """
        return number not in self.assigned_numbers


    def release(self, number: int) -> None:
        """
        Recycle or release a number.
        """
        if self.check(number):
            return

        self.assigned_numbers.remove(number)
        self.available_numbers.append(number)



# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)
