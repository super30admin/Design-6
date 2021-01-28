"""
Approach: using HashSet

Here i will maitain of a HashSet of size maxNumbers. So whenever we need do 

get() -> we iterate over the hashset and return the very first number that is encountered
check() -> we just check for the number in hashset
release() -> we add the number back to hashset

TC: O(1)
SC: O(n)

n = maxNumbers
"""

class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        """
        self.hset = {i for i in range(maxNumbers)}
        
    def get(self) -> int:
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """
        if len(self.hset) != 0:
            e = next(iter(self.hset))
            self.hset.remove(e)
            return e
        
        return -1
        

    def check(self, number: int) -> bool:
        """
        Check if a number is available or not.
        """
        if number in self.hset:
            return True
        
        return False

    def release(self, number: int) -> None:
        """
        Recycle or release a number.
        """
        self.hset.add(number)
        


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)