from typing import List, Optional
import heapq
# class AutocompleteSystem:

#     def __init__(self, sentences: List[str], times: List[int]):
#         self.map = {}
#         self.search = ""
#         for i in range(len(sentences)):
#             self.map[sentences[i]] = self.map.get(sentences[i], 0) + times[i]
        
#     def input(self, c: str) -> List[str]:
#         if c == "#":
#             self.map[self.search]= self.map.get(self.search, 0)+1
#             self.search = ""
#             return []

#         matches = []
#         self.search+=c
#         for sentence in self.map:
#             if sentence.startswith(self.search):
#                 matches.append(sentence)

#         matches.sort(key = lambda x: (-self.map[x],x))
#         result = []
#         for i in range(min(3,len(matches))):
#             result.append(matches[i])
#         return result

# # Your AutocompleteSystem object will be instantiated and called as such:
# # obj = AutocompleteSystem(sentences, times)
# # param_1 = obj.input(c)

# class HeapNode:
#     def __init__(self, sentence, freq):
#         self.freq = freq
#         self.sentence = sentence

#     def __lt__(self, other):
#         if self.freq == other.freq:
#             return self.sentence>other.sentence
#         return self.freq<other.freq

# class AutocompleteSystem:

#     def __init__(self, sentences: List[str], times: List[int]):
#         self.map = {}
#         self.search = ""
#         for i in range(len(sentences)):
#             self.map[sentences[i]] = self.map.get(sentences[i], 0) + times[i]
        
#     def input(self, c: str) -> List[str]:
#         if c == "#":
#             self.map[self.search]= self.map.get(self.search, 0)+1
#             self.search = ""
#             return []

#         self.search+=c
#         pq = []
#         for sentence in self.map:
#             if sentence.startswith(self.search):
#                 heapq.heappush(pq, HeapNode(sentence,self.map[sentence]))
#                 if len(pq)>3:
#                     heapq.heappop(pq)

#         res = []
#         while pq:
#             res.append(heapq.heappop(pq).sentence)
#         res.reverse()
#         return res

# class HeapNode:
#     def __init__(self, sentence, freq):
#         self.freq = freq
#         self.sentence = sentence

#     def __lt__(self, other):
#         if self.freq == other.freq:
#             return self.sentence>other.sentence
#         return self.freq<other.freq

# class TrieNode:
#     def __init__(self):
#         self.children = [None]*256
#         self.startswith = []

# class AutocompleteSystem:

#     def __init__(self, sentences: List[str], times: List[int]):
#         self.map = {}
#         self.search = ""
#         self.root = TrieNode()
#         for i in range(len(sentences)):
#             if sentences[i] not in self.map:
#                 self.insert(sentences[i])
#             self.map[sentences[i]] = self.map.get(sentences[i], 0) + times[i]

#     def insert(self,word):
#         curr = self.root
#         for c in word:
#             idx = ord(c) - ord(' ')
#             if not curr.children[idx]:
#                 curr.children[idx] = TrieNode()
#             curr = curr.children[idx]
#             curr.startswith.append(word)

#     def searchPrefix(self,prefix):
#         curr = self.root
#         for c in prefix:
#             idx = ord(c) - ord(' ')
#             if not curr.children[idx]:
#                 return []
#             curr = curr.children[idx]
#         return curr.startswith
        
#     def input(self, c: str) -> List[str]:
#         if c == "#":
#             if self.search not in self.map:
#                 self.insert(self.search)
#             self.map[self.search]= self.map.get(self.search, 0)+1
#             self.search = ""
#             return []

#         self.search+=c
#         pq = []
#         for sentence in self.searchPrefix(self.search):
#             heapq.heappush(pq, HeapNode(sentence,self.map[sentence]))
#             if len(pq)>3:
#                 heapq.heappop(pq)

#         res = []
#         while pq:
#             res.append(heapq.heappop(pq).sentence)
#         res.reverse()
#         return res

class HeapNode:
    def __init__(self, sentence, freq):
        self.freq = freq
        self.sentence = sentence

    def __lt__(self, other):
        if self.freq == other.freq:
            return self.sentence>other.sentence
        return self.freq<other.freq

class TrieNode:
    def __init__(self):
        self.children = [None]*256
        self.top3Results = []

class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.map = {}
        self.search = ""
        self.root = TrieNode()
        for i in range(len(sentences)):
            self.map[sentences[i]] = self.map.get(sentences[i], 0) + times[i]
            self.insert(sentences[i])

    def insert(self,word):
        curr = self.root
        for c in word:
            idx = ord(c) - ord(' ')
            if not curr.children[idx]:
                curr.children[idx] = TrieNode()
            curr = curr.children[idx]
            lst = curr.top3Results
            if word not in lst:
                lst.append(word)
            lst.sort(key = lambda x: (-self.map[x], x))
            if len(lst)>3:
                lst.pop()

    def searchPrefix(self,prefix):
        curr = self.root
        for c in prefix:
            idx = ord(c) - ord(' ')
            if not curr.children[idx]:
                return []
            curr = curr.children[idx]
        return curr.top3Results
        
    def input(self, c: str) -> List[str]:
        if c == "#":
            self.map[self.search]= self.map.get(self.search, 0)+1
            self.insert(self.search)
            self.search = ""
            return []

        self.search+=c
        return self.searchPrefix(self.search)



