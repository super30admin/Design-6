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
            
  Time is O(1)
  Space is O(n)
