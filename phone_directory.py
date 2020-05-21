"""
// Time Complexity : O(1) n is length of shorter array
// Space Complexity : O(k) #space for assigned set
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : 

// Your code here along with comments explaining your approach
Algorithm Explanation
Idea is to keep track of maximum number such that if get is called next non assigned number
is returned
We use set to keep track of which number have been assigned and which is also useful
in case of getting back the number(release operation)
"""
import random
class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        """
        #self.phone_numbers = set(range(maxNumbers))
        self.maxNumbers = maxNumbers
        self.max_number = 0
        self.assigned = set()

    def get(self) -> int:
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """
        # if not self.assigned:
        #     return self.phone_numbers.pop()
        number_to_be_returned = self.max_number
        if number_to_be_returned < self.maxNumbers:
            self.assigned.add(number_to_be_returned)
        if number_to_be_returned+1 not in self.assigned and self.max_number < self.maxNumbers:
            self.max_number = self.max_number + 1
        return number_to_be_returned if number_to_be_returned < self.maxNumbers  else -1
        

    def check(self, number: int) -> bool:
        """
        Check if a number is available or not.
        """
        return number not in self.assigned
        

    def release(self, number: int) -> None:
        """
        Recycle or release a number.
        
        Cases
        1) If release is called for a number before any number getting assigned, do nothing
        2) If release is called for a number which was assigned previously, remove the number from the assigned set, such that get can fetch you the number next time get is called
        3) If max_number > maxNumbers , set max_number to ele to be released
        
        """
        #base case - releasing before assiging, do nothing
        if self.max_number == 0:
            return
        
        if self.max_number >= self.maxNumbers:
            self.max_number = number
        
        #elif number in self.assigned:
        self.assigned.discard(number)
        
        # if self.assigned and number not in self.assigned:
        #     self.max_number = number
        #     self.assigned.discard(number)
        # #self.phone_numbers.add(number)


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)