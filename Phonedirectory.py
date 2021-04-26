'''
T = O(n)
S = O(n)

Approach:
Maintain two linear data stuctures one for sstoreing all the available numbers and the other for storing
the assigned numbers.Building this will be an O(n) operation

Get:
T = O(1)
S = O(1)

Appraoch:
Pop the next element on the available number queue and store the same on the assignedNo array

Check:
T = O(1)
S = O(1)

Approach: 
Check of the number given is not present in the assigned number hashset if not then this no is available
else True

Release:
T = O(1)
S = O(1)

Approach: 
Check if the number about to release is already not assigned.
If if is assigned then add the number back to the the available queue and remove it from the assigned hashset
'''

class PhoneDirectory:
    def __init__(self,maxnumbers):
        self.assignedNo = set()
        self.AvailableNo = collections.deque()
        self.maxnumbers = maxnumbers
        for i in range(maxnumbers):
            self.AvailableNo.append(i)
            
    def get(self):
        if not self.AvailableNo:
            return -1
        number = self.AvailableNo.popleft()
        self.assignedNo.add(number)
        return number

    def check(self,num):
        if num not in self.assignedNo and num < self.maxnumbers:
            return True
        return False
    
    def release(self,num):
        if self.check(num):
            return
        
        self.assignedNo.remove(num)
        self.AvailableNo.append(num)
        
P = PhoneDirectory(5)
C1 = P.get()
print(C1)
C2 = P.get()
C3 = P.get()
C4 = P.get()
C5 = P.get()

print(P.check(8))
