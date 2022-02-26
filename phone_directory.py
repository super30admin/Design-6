#O(1) time for all operations
'''
Use a queue to keep track of the order of the elements and a hashset for fast lookup and deletion
'''
class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        """
        Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory.
        """
        self.numbers_q=collections.deque()
        self.numbers_set=set()
        for i in range(maxNumbers):
            self.numbers_q.append(i)
            self.numbers_set.add(i)
            

    def get(self) -> int:
        """
        Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available.
        """
        if not self.numbers_q: return -1
        num=self.numbers_q.popleft()
        self.numbers_set.remove(num)
        return num
        
    def check(self, number: int) -> bool:
        """
        Check if a number is available or not.
        """
        return (number in self.numbers_set)
        

    def release(self, number: int) -> None:
        """
        Recycle or release a number.
        """
        if number not in self.numbers_set:
            self.numbers_q.append(number)
            self.numbers_set.add(number)
