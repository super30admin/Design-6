'''
Leet code- 379 - Design a phone directory
time complexity - O(1)
space complexity - O(N)

'''

class PhoneDirectory():
    global directory
    def __init__(self, maxNumbers):
        
        self.directory=set()
        for i in range(maxNumbers):
            directory.add(i)
            print(directory)
    def get(self):
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        :rtype: int
        """
        if len(directory)==0:
            return -1
        res=directory.pop()
        return res
        print(directory)
    
    def check(self, number):
        """
        Check if a number is available or not.
        :type number: int
        :rtype: bool
        """
        return True if number in directory else False
        print(directory)
    
    def release(self, number):
        """
        Recycle or release a number.
        :type number: int
        :rtype: void
        """
        directory.add(number)
        print(directory)
        