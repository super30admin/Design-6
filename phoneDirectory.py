'''
We can make use of list and haspmap
list for storing and hashmap for searching so that time remain constant

Time Complexity --> O(1)
Space Complexity --> O(n) for hashmap and list
'''
class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.map = {}
        self.ls = []
        for i in range(maxNumbers):
            self.map[i] = 1
            self.ls.append(i)
        

    def get(self) -> int:
        #return any number avalible
        if len(self.ls)!=0:
            k = self.ls[-1]
            #print(k)
            del(self.map[k])
            self.ls.pop()
            return k
        else:
            return -1

        

    def check(self, number: int) -> bool:
        #check from the hashmap
        if number in self.map:
            return True
        else:
            return False
        

    def release(self, number: int) -> None:
        if number not in self.map:
            self.ls.append(number)
            self.map[number] = 1
        return
        


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)