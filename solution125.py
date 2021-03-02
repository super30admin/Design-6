class PhoneDirectory:
    def __init__(self, maxNumbers: int):                            
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        """
        self.s=set()
        self.arr=[i for i in range(maxNumbers)]
        
    def get(self) -> int:                                                       #O(1)
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """
        if self.arr:                                                            #if number is available add it to the set to indicate it is being used and remove from array
            result=self.arr.pop(0)
            self.s.add(result)
            return result                                                       #return the number that is being used
        return -1
        
    def check(self, number: int) -> bool:                                       #O(1)
        """
        Check if a number is available or not.
        """
        return number in self.arr                                           #if number in array return true else trurn false

    def release(self, number: int) -> None:                                     #O(1)
        """
        Recycle or release a number.
        """
        if number in self.s:                                                #if number is released add it back to array and remove from set s
            self.s.remove(number)
            self.arr.append(number)
        
# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)