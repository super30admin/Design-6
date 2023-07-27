'''
Problem: Design Phone Directory
Time Complexity: O(1), where n is maxNumbers
Space Complexity: O(n), for stack and set
Did this code successfully run on Leetcode: Yes
Any problem you faced while coding this: No
Your code here along with comments explaining your approach:
        maintain a set for occupied slots
        and a stack to always give next available number
'''

class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.occupiedslots = set()
        self.stack = []
        for i in range(maxNumbers-1, -1, -1):
            self.stack.append(i)

    def get(self) -> int:
        if self.stack:
            slot = self.stack.pop()
            self.occupiedslots.add(slot)
            return slot
        return -1

    def check(self, number: int) -> bool:
        if number in self.occupiedslots:
            return False
        return True
        
    def release(self, number: int) -> None:
        if number in self.occupiedslots:
            self.occupiedslots.remove(number)
            self.stack.append(number)

        


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)