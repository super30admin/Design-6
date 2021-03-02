class PhoneDirectory:
    
    """
    Description: Design a phone directory to perform 3 different operations
    
    Time Complexity: O(n) for brute force, O(1) for optimum {both works in LC}
    Space Complexity: O(n) -> to store list of available numbers
    
    Approach: 
    Brute force: Using a hash set with available numbers in __init__
      - to get available number, pick one number and remove from the hash set
      - to check, check if number exists in the hash set
      - to release, add the number back to the hash set
       
    Optimized: Use a hash set for assigned numbers and a list of available numbers
      - to get available number, pop item from list and add to assigned set
      - to check, check if the number exists in the list of available numbers
      - to release, add the number back to the list and remove from the set 
    """
    
    from collections import deque
    def __init__(self, maxNumbers: int):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        """
        self.available = [num for num in range(maxNumbers)]
        self.assigned = set()

    def get(self) -> int:
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """
        if len(self.available) == 0: 
            return -1
        else:
            answer = self.available.pop(0)
            self.assigned.add(answer)
        return answer

    def check(self, number: int) -> bool:
        """
        Check if a number is available or not.
        """
        return number in self.available

    def release(self, number: int) -> None:
        """
        Recycle or release a number.
        """
        if number in self.assigned:
            self.available.append(number)
            self.assigned.remove(number)
        
# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)
