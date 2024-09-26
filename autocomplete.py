#import heapq
class Node:
    def __init__(self):
        self.a = [None for i in range(0,256)]
        self.words = {}

class Trie:
    def __init__(self):
        self.node = Node()
    def insert(self,s,n):
        temp = self.node
        for i in s:
            if(temp.a[ord(i)]==None):
                temp.a[ord(i)] = Node()
            temp = temp.a[ord(i)]
            if(s in temp.words.keys()):
                temp.words[s] -= n
            else:
                temp.words[s] = -1*n
            
class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.root = Trie()
        self.history = ""
        self.last = self.root.node
        for i in range(0,len(sentences)):
            self.root.insert(sentences[i], times[i])
        

    def input(self, c: str) -> List[str]:
        if(c=="#"):
            #self.root = Trie()
            self.root.insert(self.history, 1)
            self.last = self.root.node
            self.history = ""
            return []
        else:
            self.history += c
            if(self.last!=None):
                self.last = self.last.a[ord(c)]
                if(self.last==None):
                    return []
                h = []
                for i in self.last.words.keys():
                    h.append((self.last.words[i],i))
                
                h.sort()
                o = []
                for i in h:
                    if(len(o)==3):
                        break
                    o.append(i[1])
                return o
            else:
                return []
            
