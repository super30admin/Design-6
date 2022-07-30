# time complexity is o(n + l), where n is the size of the input and l is the size of the word
# space complexity is o(n), where n is the size of the input

class AutocompleteSystem:
    class TrieNode:
            def __init__(self):
                self.children = [None for i in range(256)]
                self.li = list()
                
    class inpNode:
            def __init__(self, inp, val):
                self.key = inp
                self.val = val
                
            def __lt__(self, other):
                if(self.val == other.val):
                    return self.key < other.key
                return self.val > other.val
    
    def __init__(self, sentences: List[str], times: List[int]):
        self.root = self.TrieNode()        
        self.l = list()
        self.d = dict()
        for i in range(len(sentences)):
            if(sentences[i] not in self.d):
                self.d[sentences[i]] = 0
            self.d[sentences[i]] += times[i]
            self.trieInsert(sentences[i])
    
    def trieInsert(self, word):
        curr = self.root
        for letter in word:
            if(curr.children[ord(letter) - ord(' ')] == None):
                curr.children[ord(letter) - ord(' ')] = self.TrieNode()
            curr = curr.children[ord(letter) - ord(' ')]
            if(word not in curr.li):
                curr.li.append(word)
            
            curr.li = sorted(curr.li, key = lambda x:(-self.d[x], x))
            while(len(curr.li) > 3):
                curr.li.pop()
        return curr.li
        
    def trieSearch(self, word): #time o(l), where l is lenght of the input chars(c)
        curr = self.root
        for letter in word:
            if(curr.children[ord(letter) - ord(' ')] != None):
                curr = curr.children[ord(letter) - ord(' ')]
            else:
                return []
        # curr = curr.children[ord(letter) - ord(' ')]
        
        return curr.li
        
    def input(self, c: str) -> List[str]:
        if(c == '#'):
            sb = ''.join(self.l)
            if(sb not in self.d):
                self.d[sb] = 0
                # self.trieInsert(sb)
            self.d[sb] += 1
            self.trieInsert(sb)
            self.l = list()
        else:
            self.l.append(c)
            sb = ''.join(self.l)
            return self.trieSearch(sb)
    
#     def search(self, userInput):
#         from heapq import heappush
#         from heapq import heappop
#         res = list()
#         heap = list()
#         class inpNode:
#             def __init__(self, inp, val):
#                 self.key = inp
#                 self.val = val
                
#             def __lt__(self, other):
#                 if(self.val == other.val):
#                     return self.key > other.key
#                 return self.val < other.val
            
#         for inp in userInput:
#             # if(inp.startswith(userInput)):
#             tempNode = inpNode(inp, self.d[inp])
#             heappush(heap, tempNode)
                
#             if(len(heap) > 3):
#                 heappop(heap)
                
#         while(len(heap) > 0):
#             nd = heappop(heap)
#             res.insert(0, nd.key)
#         return res
            
    
    
    
    
    
    
    
#     class TrieNode:
#             def __init__(self):
#                 self.children = [None for i in range(256)]
#                 self.li = list()
#     def __init__(self, sentences: List[str], times: List[int]):
#         self.root = self.TrieNode()        
#         self.l = list()
#         self.d = dict()
#         for i in range(len(sentences)):
#             self.trieInsert(sentences[i])
#             if(sentences[i] not in self.d):
#                 self.d[sentences[i]] = 0
#             self.d[sentences[i]] += times[i]
    
#     def trieInsert(self, word):
#         curr = self.root
#         for letter in word:
#             if(curr.children[ord(letter) - ord(' ')] == None):
#                 curr.children[ord(letter) - ord(' ')] = self.TrieNode()
#             curr = curr.children[ord(letter) - ord(' ')]
#             curr.li.append(word)
        
#     def trieSearch(self, word):
#         curr = self.root
#         for letter in word:
#             if(curr.children[ord(letter) - ord(' ')] != None):
#                 curr = curr.children[ord(letter) - ord(' ')]
#             else:
#                 return []
#         # curr = curr.children[ord(letter) - ord(' ')]
        
#         return self.search(curr.li)
        
#     def input(self, c: str) -> List[str]:
#         if(c == '#'):
#             sb = ''.join(self.l)
#             if(sb not in self.d):
#                 self.d[sb] = 0
#                 self.trieInsert(sb)
#             self.d[sb] += 1
#             self.l = list()
#         else:
#             self.l.append(c)
#             sb = ''.join(self.l)
#             return self.trieSearch(sb)
    
#     def search(self, userInput):
#         from heapq import heappush
#         from heapq import heappop
#         res = list()
#         heap = list()
#         class inpNode:
#             def __init__(self, inp, val):
#                 self.key = inp
#                 self.val = val
                
#             def __lt__(self, other):
#                 if(self.val == other.val):
#                     return self.key > other.key
#                 return self.val < other.val
            
#         for inp in userInput:
#             # if(inp.startswith(userInput)):
#             tempNode = inpNode(inp, self.d[inp])
#             heappush(heap, tempNode)
                
#             if(len(heap) > 3):
#                 heappop(heap)
                
#         while(len(heap) > 0):
#             nd = heappop(heap)
#             res.insert(0, nd.key)
#         return res
            
    
    
    
    
# time complexity is o(n*l*log(k)), n is size of the input, l is size of the sentence, k is hot degree
# space is o(n), n is size of the input
#     def __init__(self, sentences: List[str], times: List[int]):
#         self.l = list()
#         self.d = dict()
#         for i in range(len(sentences)): #time o(n), space o(n)
#             if(sentences[i] not in self.d):
#                 self.d[sentences[i]] = 0
#             self.d[sentences[i]] += times[i]

#     def input(self, c: str) -> List[str]:
#         if(c == '#'):
#             sb = ''.join(self.l)
#             if(sb not in self.d):
#                 self.d[sb] = 0
#             self.d[sb] += 1
#             self.l = list()
#         else:
#             self.l.append(c)
#             sb = ''.join(self.l)
#             return self.search(sb)
    
#     def search(self, userInput):
#         from heapq import heappush
#         from heapq import heappop
#         res = list()
#         heap = list()
#         class inpNode:
#             def __init__(self, inp, val):
#                 self.key = inp
#                 self.val = val
                
#             def __lt__(self, other):
#                 if(self.val == other.val):
#                     return self.key > other.key
#                 return self.val < other.val
            
#         for inp in self.d: #time is o(n*l*log(k))
#             if(inp.startswith(userInput)):
#                 tempNode = inpNode(inp, self.d[inp])
#                 heappush(heap, tempNode)
                
#             if(len(heap) > 3):
#                 heappop(heap)
                
#         while(len(heap) > 0):
#             nd = heappop(heap)
#             res.insert(0, nd.key)
#         return res
            
            
            
        
            
        


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)