class PhoneDirectory(object):
    
    def __init__(self, maxNumbers):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        :type maxNumbers: int
        """
        self.hmap = collections.defaultdict(int)
        self.size = maxNumbers
        for i in range(maxNumbers):
            self.hmap[i] = 0

    def get(self):
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        :rtype: int
        """
        for k, v in self.hmap.items():
            if v == 0:
                self.hmap[k] = 1
                return k
        return -1
        

    def check(self, number):
        """
        Check if a number is available or not.
        :type number: int
        :rtype: bool
        """
        if number not in self.hmap:
            return False
        if self.hmap[number] == 0:
            return True
        return False

    def release(self, number):
        """
        Recycle or release a number.
        :type number: int
        :rtype: None
        """
        self.hmap[number] = 0
        


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)