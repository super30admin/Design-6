# Time Complexity : O(1) 
# Space Complexity :O(n), where n will be the number of phone numbers in the PhoneDirectory
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
# Your code here along with comments explaining your approach

class PhoneDirectory:
    q = None 
    directory = None
    def __init__(self, num):
        self.q = []
        self.directory = set()
        for i in range(num):
            self.q.append(i)
            self.directory.add(i)
        print(self.q)
    def get(self): 
        if self.q:
            key = self.q.pop(0)
            self.directory.remove(key)
            return key
        return -1
    
    def check(self, number):
        if number in self.directory:
            return True 
        return False
    
    def release(self, number):
        if number not in self.directory:
            self.directory.add(number)
            self.q.append(number)

if __name__ == "__main__":
    s = PhoneDirectory(3)
    print(s.get())     # 0
    print(s.get())     # 1 
    print(s.check(2))  # True 
    print(s.get())     # 2 
    print(s.check(2))   # False
    print(s.release(2)) # None 
    print(s.check(2))   # True
    