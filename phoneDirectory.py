class PhoneDirectory:

    # In this problem I used hashmap and list, I store all numbers in list md mark them as 0 in hashmap. Once they get assigned I chage value to one and pop them from list. Once thy are unassigned they are added back to list and hashmp val is changed to 0.
    #Time Complxity : O(1)
    #Space Complexity: O(n)
    def __init__(self, maxNumbers: int):

        self.lis = list()
        self.numDict = dict()
        for i in range(maxNumbers):
            self.lis.append(i)
            self.numDict[i] = 0
        

    def get(self) -> int:

        if len(self.lis)!=0:
            num = self.lis.pop()
            self.numDict[num] = 1
            return num
        
        else:
            return -1
        
        

    def check(self, number: int) -> bool:

        if self.numDict[number] == 1:
            return True
        else:
            return False
        

    def release(self, number: int) -> None:

        self.lis.append(number)
        self.numDict[number] = 0