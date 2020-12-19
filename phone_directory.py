#inserting nums in set and queue - O(1) - limited numbers
#check- lookup in sets- O(1) if we use only queue it would ne O(n)
#realease - add - O(1)
#get - O(1)


class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        """
        from collections import deque
        self.q=deque()
        # self.sets=set()
        for i in range(maxNumbers):
            # self.sets.add(i)
            self.q.append(i)

    def get(self) -> int:
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """
        if self.q:
            x=self.q.popleft()
            # print(x)
            # print(self.sets)
            # self.sets.remove(x)
            return x
        return -1

    def check(self, number: int) -> bool:
        """
        Check if a number is available or not.
        """
        if number in self.q:
            return True
        return False

    def release(self, number: int) -> None:
        """
        Recycle or release a number.
        """
        if number not in self.q:
            self.q.append(number)
            # self.sets.add(number)

# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)