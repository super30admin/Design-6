class PhoneDirectory(object):

    def __init__(self, maxNumbers):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        :type maxNumbers: int
        """
        #two ds: queue for doing get operation in O(1)--> all ops O(1)
        #space: O(n)
        self.queue = deque()
        self.numSet = set()
        for i in xrange(maxNumbers):
            self.queue.append(i)
            self.numSet.add(i)

    def get(self):
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        :rtype: int
        """
        if not self.queue:
            return -1
        newNum = self.queue.popleft()
        self.numSet.remove(newNum)
        return newNum
        

    def check(self, number):
        """
        Check if a number is available or not.
        :type number: int
        :rtype: bool
        """
        return number in self.numSet
        

    def release(self, number):
        """
        Recycle or release a number.
        :type number: int
        :rtype: None
        """
        if number in self.queue:
            return
        self.queue.append(number)
        self.numSet.add(number)


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)
