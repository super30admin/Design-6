# Time:- get:-O(1),check:- O(1), release:- O(1)
# Space:- O(2n)
# Approach:- Using a set and array to store the numbers in our pool. get removes a number from the array and also removes it from the set to maintain consistency in O(1)
# check() checks if the number is in the hashset or not in O(1)
# Release checks if the number is there or not in the hashset and then appends to the array 
# and adds it to the set as well in O(1).
class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        """
        self.my_set=set()
        self.q=[]
        for i in range(maxNumbers):
            self.my_set.add(i)
            self.q.append(i)
            
        
        

    def get(self) -> int:
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """
        self.numbertobereleased=None
        if len(self.q)==0:
            return -1
        self.numbertobereleased=self.q.pop()
        self.my_set.remove(self.numbertobereleased)
        return self.numbertobereleased