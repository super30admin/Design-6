class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.avialablemap = collections.defaultdict(bool)
        self.queue = collections.deque()
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        """
        for idx in range(0, maxNumbers):
            self.avialablemap[idx] = True
            self.queue.append(idx)

    def get(self) -> int:
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """
        if self.queue.__len__() == 0: return -1
        telephone = self.queue.popleft()
        self.avialablemap[telephone] = False
        return telephone

    def check(self, number: int) -> bool:
        """
        Check if a number is available or not.
        """
        return self.avialablemap[number]

    def release(self, number: int) -> None:
        """
        Recycle or release a number.
        """
        if self.avialablemap[number] == False:
            self.queue.append(number)
            self.avialablemap[number] = True

# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)