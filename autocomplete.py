class TrieNode:
    def __init__(self):
        self.pq=[]
        self.children=[False for i in range(256)]


    
class Autocomplete:
    def __init__(self,sentence,times):
        self.hmap={}
        self.root=TrieNode()
        self.sb=[]
        for i in range(len(sentence)):
            if sentence[i] not in self.hmap.keys():
                self.hmap[sentence[i]]=0
            self.hmap[sentence[i]]+=times[i]
        
            self.insert(sentence[i],times[i])
    def insert(self,st,time):
        
        curr=self.root
        
        for i in range(len(st)):
            c=st[i]
            if not curr.children[ord(c) -ord(' ')]:
                curr.children[ord(c)-ord(' ')]=TrieNode()
            curr=curr.children[ord(c)-ord(' ')]
            
            li=curr.pq
            
            if st not in li:
                
                li.append([st,time])
                
            for i in li:
                sorted(li,key=lambda x:x[1])
                sorted(li)
            if len(li)>3:
                li.pop(len(li)-1)
                
        curr.pq=li






        
    def search(self,prefix):
        curr=self.root
        for i in range(len(prefix)):
            c=prefix[i]
            if not curr.children[ord(c)-ord(' ')]:
                return []
            curr=curr.children[ord(c)-ord(' ')]
        return curr.pq



    def input(self,c):
        st=""
        if c=='#':
            for i in range(len(self.sb)):
                st=st+self.sb[i]
            if st not in self.hmap:
                self.hmap[st]=0
            self.hmap[st]=self.hmap[st]+1
            self.insert(st,1)
            self.sb=[]
            return []
        else:
            self.sb.append(c)
            for i in range(len(self.sb)):
                st=st+self.sb[i]
            return self.search(st)



        
        



def main():
    sentence=["i love you","island","ironman","i love leetcode","blah"]
    times=[5,3,2,2,8]
    obj=Autocomplete(sentence,times)
    print(obj.input("i"))
main()
