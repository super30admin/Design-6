#Time Complexity: O(1)
#Space Complexity: O(1)
class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.hashset = set()
        self.q = deque()
        for i in range(maxNumbers):
            self.hashset.add(i)
            self.q.append(i)
            
    def get(self) -> int:
        if self.q:
            num = self.q.popleft()
            self.hashset.remove(num)
            return num
        else:
            return -1
            
        

    def check(self, number: int) -> bool:
        return number in self.hashset
        

    def release(self, number: int) -> None:
        if number not in self.hashset:
            self.hashset.add(number)
            self.q.append(number)
        

