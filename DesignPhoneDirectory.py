class PhoneDirectory:
    
    # TC: O(n) where n is maxNumbers
    # SC: O(2n) 
    def __init__(self, maxNumbers: int): 
        self.queue = collections.deque()
        self.hashset = set()
        
        for i in range(maxNumbers):
            self.queue.append(i)
            self.hashset.add(i)
    
    # TC: O(1)
    # SC: O(1)
    def get(self) -> int:
        
        if len(self.queue) == 0:
            return -1
        
        popped = self.queue.popleft()
        self.hashset.remove(popped)
        return popped
        
    # TC: O(1)
    # SC: O(1)
    
    def check(self, number: int) -> bool:
        return True if number in self.hashset else False
    
    # TC: O(1)
    def release(self, number: int) -> None:
        if number not in self.hashset:
            self.hashset.add(number)
            self.queue.append(number)