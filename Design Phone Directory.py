# // Time Complexity :O(1) 
# // Space Complexity :O(1) 
# // Did this code successfully run on Leetcode :no premium acc
# // Any problem you faced while coding this :no



class Phonedirectory:
    def __init__(self,maxnum):
        self.q=[]
        self.hset=set()
        for i in range(1,maxnum):
            self.q.append(i)
            self.hset.add(i)
    def get(self):
        
        num=self.q[0]
        self.hset.remove(num)
        return self.q.pop(0)
    def check(self,num):
        return self.hset.__contains__(num)
    def release(self,num):
        self.q.append(num)
        self.hset.add(num)
    


def main():
    directory=Phonedirectory(8)
    print(directory.hset)
    print(directory.get())
    print(directory.check(2))
    print(directory.get())
    print(directory.check(2))
    directory.release(2)
    print(directory.check(2))

main()