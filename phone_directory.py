class PhoneDirectory(object):
#O(n)>constructor>but called once
#all other functions are O(1)>called multiple times>so it dominates
#O(n)>set

#here get function>return any random element>thats is why operation works in set in o(1)
#if asked to return least element then maintain PQ for get opearion in o(logn) alongside set to have check operation in o(1) 
    def __init__(self, maxNumbers):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        :type maxNumbers: int
        """
        self.myset=set()
        for i in range(maxNumbers):
            self.myset.add(i)

    def get(self):
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        :rtype: int
        """
        if len(self.myset)!=0:
            val=self.myset.pop()
            return val
        return -1
            

    def check(self, number):
        """
        Check if a number is available or not.
        :type number: int
        :rtype: bool
        """
        if number in self.myset:
            return True
        return False
            

    def release(self, number):
        """
        Recycle or release a number.
        :type number: int
        :rtype: None
        """
        self.myset.add(number)


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)