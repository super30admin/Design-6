'''
Brute Force (Using dict, to find the prefix and return the top 3 elements using min heap)
Time: O(n)
Space: O(n)
'''


import heapq as hq

class cl:
    def __init__(self, st, val):
        self.val = val
        self.st = st
    
    def __lt__(self, other):
        if self.val == other.val:
            return self.st > other.st
        return self.val < other.val
            

class AutocompleteSystem:
    hm = dict()
    search = ''
    def __init__(self, sentences: List[str], times: List[int]):
        self.hm = dict()
        self.search = ''
        for i in range(len(sentences)):
            self.hm[sentences[i]] = times[i]
        
        
        

    def input(self, c: str) -> List[str]:
        if c == '#':
            
            if self.search != '':
                if self.search not in self.hm:
                    self.hm[self.search] = 0
                self.hm[self.search] += 1
            else:
                return []
            
            self.search = ''
            return []
        else:
            self.search += c
        
        heap = list()
        
        for key in self.hm:
            
            if key[:len(self.search)] == self.search:
                obj = cl(key, self.hm[key])
                hq.heappush(heap, obj)
                if len(heap) > 3:
                    hq.heappop(heap)
            
        res = list()
        while len(heap) != 0:
            res.insert(0,hq.heappop(heap).st)
        return res
        


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)


#################################################################################################################

'''
Using Tries instead of searching in hashmp using the prefix (sitll hash set is used for freq)
Tries with children array
Time: O(1)
Space: O(n)
'''

import heapq as hq

class cl:
    def __init__(self, st, val):
        self.val = val
        self.st = st
    
    def __lt__(self, other):
        if self.val == other.val:
            return self.st > other.st
        return self.val < other.val
            
class TrieNode:
    def __init__(self):
        self.children = [None for _ in range(256)]
        self.li = list()

    def insert(self, word, root):
        curr = root
        for i in range(len(word)):
            temp = ord(word[i]) - ord(' ')
            if curr.children[temp] is None:
                curr.children[temp] = TrieNode()
            curr = curr.children[temp]
            curr.li.append(word)

    def startswith(self, word, root):
        curr = root
        for i in range(len(word)):
            temp = ord(word[i]) - ord(' ')
            if curr.children[temp] is None:
                return []
            curr = curr.children[temp]
        return curr.li
class AutocompleteSystem:
    hm = dict()
    search = ''
    root = TrieNode()

    def __init__(self, sentences: List[str], times: List[int]):
        self.hm = dict()
        self.search = ''
        self.root = TrieNode()
        
        for i in range(len(sentences)):
            self.hm[sentences[i]] = times[i]
            self.root.insert(sentences[i], self.root)
        
        

    def input(self, c: str) -> List[str]:
        if c == '#':
            
            if self.search != '':
                if self.search not in self.hm:
                    self.hm[self.search] = 0
                    self.root.insert(self.search, self.root)
                self.hm[self.search] += 1
            else:
                return []
            
            self.search = ''
            return []
        else:
            self.search += c
        
        heap = list()
        
        sta_wi_wor = self.root.startswith(self.search, self.root)
        for stt in sta_wi_wor:
            obj = cl(stt, self.hm[stt])
            hq.heappush(heap, obj)
            if len(heap) > 3:
                hq.heappop(heap)
            
        res = list()
        while len(heap) != 0:
            res.insert(0,hq.heappop(heap).st)
        return res
        


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)


#################################################################################################################

'''
Using Tries instead of searching in hashmp using the prefix (sitll hash set is used for freq)
Tries with children hashmap (works exactly same)
Time: O(1)
Space: O(n)
'''

import heapq as hq

class cl:
    def __init__(self, st, val):
        self.val = val
        self.st = st
    
    def __lt__(self, other):
        if self.val == other.val:
            return self.st > other.st
        return self.val < other.val
            
class TrieNode:
    def __init__(self):
        self.children = dict()
        self.li = list()

    def insert(self, word, root):
        curr = root
        for i in range(len(word)):
            temp = word[i]
            if temp not in curr.children:
                curr.children[temp] = TrieNode()
            curr = curr.children[temp]
            curr.li.append(word)

    def startswith(self, word, root):
        curr = root
        for i in range(len(word)):
            temp = word[i]
            if temp not in curr.children:
                return []
            curr = curr.children[temp]
        return curr.li
class AutocompleteSystem:
    hm = dict()
    search = ''
    root = TrieNode()

    def __init__(self, sentences: List[str], times: List[int]):
        self.hm = dict()
        self.search = ''
        self.root = TrieNode()
        
        for i in range(len(sentences)):
            self.hm[sentences[i]] = times[i]
            self.root.insert(sentences[i], self.root)
        
        

    def input(self, c: str) -> List[str]:
        if c == '#':
            
            if self.search != '':
                if self.search not in self.hm:
                    self.hm[self.search] = 0
                    self.root.insert(self.search, self.root)
                self.hm[self.search] += 1
            else:
                return []
            
            self.search = ''
            return []
        else:
            self.search += c
        
        heap = list()
        
        sta_wi_wor = self.root.startswith(self.search, self.root)
        for stt in sta_wi_wor:
            obj = cl(stt, self.hm[stt])
            hq.heappush(heap, obj)
            if len(heap) > 3:
                hq.heappop(heap)
            
        res = list()
        while len(heap) != 0:
            res.insert(0,hq.heappop(heap).st)
        return res
        


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)

######################################################################################

'''
Efficient Approach
Using heap in every node
'''