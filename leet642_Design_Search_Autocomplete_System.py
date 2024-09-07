import heapq

# Tries Solution
class node:
    def __init__(self,label,priority):
        self.label = label
        self.priority = priority
    
    def __lt__(self,other):
        if self.priority == other.priority:
            return self.label > other.label
        else:
            return self.priority < other.priority

class AutocompleteSystem:
    class trieNode:
        def __init__(self):
            self.children = [None]*100
            self.startsWith = []

    def insert(self,prefix,root):
        curr = root

        for c in prefix:
            if curr.children[ord(c) - ord(" ")] == None:
                curr.children[ord(c) - ord(" ")] = self.trieNode()
            
            curr = curr.children[ord(c) - ord(" ")]
            curr.startsWith.append(prefix)
    
    def search(self,prefix,root):
        curr = root
        for c in prefix:
            if curr.children[ord(c) - ord(" ")] == None:
                return []
            
            curr = curr.children[ord(c) - ord(" ")]
        return curr.startsWith

    def __init__(self, sentences, times):
        self.searchStr = ""
        self.cache = {}
        self.k = 3
        self.root = self.trieNode()

        n = len(times)
        for i in range(0,n):
            if sentences[i] in self.cache:
                self.cache[sentences[i]] += times[i]
            else:
                self.insert(sentences[i],self.root)
                self.cache[sentences[i]] = times[i]

    def input(self, c):
        if c == "#":
            if self.searchStr != "":
                if self.searchStr in self.cache:
                    self.cache[self.searchStr] += 1
                else:
                    self.insert(self.searchStr,self.root)
                    self.cache[self.searchStr] = 1

                self.searchStr = ""
            
            return []
        
        self.searchStr += c
        self.heap = []
        items = self.search(self.searchStr,self.root)
        for item in items:
            node1 = node(item,self.cache[item])
            heapq.heappush(self.heap,node1)
            if len(self.heap) > 3:
                heapq.heappop(self.heap)

        result = []
        while len(self.heap) != 0:
            temp = heapq.heappop(self.heap)
            result.insert(0,temp.label)
        
        return result   

# Almost Bruteforce solution
# class node:
#     def __init__(self,label,priority):
#         self.label = label
#         self.priority = priority
    
#     def __lt__(self,other):
#         if self.priority == other.priority:
#             return self.label > other.label
#         else:
#             return self.priority < other.priority

# class AutocompleteSystem:

#     def __init__(self, sentences: List[str], times: List[int]):
#         self.searchStr = ""
#         self.cache = {}
#         self.k = 3

#         n = len(times)
#         for i in range(0,n):
#             if sentences[i] in self.cache:
#                 self.cache[sentences[i]] += times[i]
#             else:
#                 self.cache[sentences[i]] = times[i]
        

#     def input(self, c: str) -> List[str]:
#         if c == "#":
#             if self.searchStr != "":
#                 if self.searchStr in self.cache:
#                     self.cache[self.searchStr] += 1
#                 else:
#                     self.cache[self.searchStr] = 1

#                 self.searchStr = ""
            
#             return []
        
#         self.searchStr += c
#         self.heap = []
#         for key,value in self.cache.items():
#             if key.startswith(self.searchStr):
#                 node1 = node(key,value)
#                 heapq.heappush(self.heap,node1)
#                 if len(self.heap) > 3:
#                     heapq.heappop(self.heap)
        
#         result = []
#         while len(self.heap) != 0:
#             temp = heapq.heappop(self.heap)
#             result.insert(0,temp.label)
        
#         return result       

# Your AutocompleteSystem object will be instantiated and called as such:
if __name__ == "__main__":
    sentences = ["i love you","island","iroman","i love leetcode"]
    times = [5,3,2,2]
    obj = AutocompleteSystem(sentences, times)
    inputs = ["i"," ","a","#"]
    for c in inputs:
        print(obj.input(c))