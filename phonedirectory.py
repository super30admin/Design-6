#time complexity is o(n)
#space complexity is o(n)

class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        """
        self.directory=set()
        for i in range(maxNumbers):
            self.directory.add(i)
        print(self.directory)
        

    def get(self) -> int:
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """
        if len(self.directory)==0:
            return -1
        else:
            return self.directory.pop()
        

    def check(self, number: int) -> bool:
        """
        Check if a number is available or not.
        """
        if number in self.directory:
            return True
        else:
            return False
        
        

    def release(self, number: int) -> None:
        """
        Recycle or release a number.
        """
        self.directory.add(number)
        
        


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)