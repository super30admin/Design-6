# Time complexity: O(n)
# Space complexity: O(n)
# Approach: create a queue of size n ands tore all the elements from 0 to n to store the available numbers. Create a hashset to store the numbers that are not available and taken
# when get method is called, pop the element from queue and put it in hashset.
# when check is called, it the number is in hashset, then return False since its noy available , else return True
# when release is called, remove the number from hashset and put it in queue.



class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.maxNumbers = maxNumbers
        self.queue = []
        for num in range(0,self.maxNumbers):
            self.queue.append(num)
        print(self.queue)
        self.hashset = set()
        

    def get(self) -> int:
        if len(self.queue) == 0:
            return -1
        popped = self.queue.pop(0)
        self.hashset.add(popped)
        return popped
        

    def check(self, number: int) -> bool:
        return True if number not in self.hashset else False
            
        

    def release(self, number: int) -> None:
        if number in self.hashset:
            self.hashset.remove(number)
            self.queue.append(number)
        


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)