class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.hset=set()
        self.heap=list(range(maxNumbers))
        self.maxNumbers=maxNumbers

    def get(self) -> int:
        if len(self.heap)<1:
            return -1
        curr=heapq.heappop(self.heap)
        self.hset.add(curr)
        return curr

    def check(self, number: int) -> bool:
        return number not in self.hset
        

    def release(self, number: int) -> None:
        if number in self.hset:
            self.hset.remove(number)
            heapq.heappush(self.heap, number)