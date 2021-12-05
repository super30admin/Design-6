# // Time Complexity :O(1) 
# // Space Complexity :O(1) 
# // Did this code successfully run on Leetcode :no premium acc
# // Any problem you faced while coding this :no



class Phonedirectory:
    def __init__(self,maxnum):
        self.q=[]
        self.hset=[]
        for i in range(1,maxnum):
            self.q.append(i)
            self.hset.append(1)
    def get(self):
        
        num=self.q[0]
        self.hset[num-1]=0
        return self.q.pop(0)
    def check(self,num):
        return self.hset[num-1]==1
    def release(self,num):
        self.q.append(num)
        self.hset[num-1]=1
    


def main():
    directory=Phonedirectory(3)
    print(directory.get())
    print(directory.check(2))
    print(directory.get())
    print(directory.check(2))
    directory.release(2)
    print(directory.check(2))

main()
































