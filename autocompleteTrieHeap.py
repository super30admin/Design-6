# Time complexity : O(nk) where k = number of all strings starting with prefix
# Space complexity : O(n)
# Program ran on leetcode successfully

import heapq
from typing import List


class TrieNode:
    def __init__(self,):
        self.children = [None for _ in range(256)]
        self.lst = []
              
class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.sentences = sentences
        self.times = times
        self.countMap = {}
        self.inputStr = ''
        self.root = TrieNode()
        
        for i in range(len(self.sentences)):
            sentence = sentences[i]
            if sentence in self.countMap:
                self.countMap[sentence] = self.countMap[sentence] + times[i]
            else:
                self.countMap[sentence] = times[i]
                self.insert(sentence)
            

    def input(self, c: str) -> List[str]:

        if c == '#':
            tempSearch = self.inputStr
            if tempSearch not in self.countMap:
                self.insert(tempSearch)
                self.freq[tempSearch] = 1
            else:
                self.freq[tempSearch] += 1
            
            self.inputstr = ''
            return []
        self.inputStr += c
        top3 = []
        prefix = self.inputStr
        starts_with = self.search(prefix)
        for s in starts_with:
            heapq.heappush(top3, self.Distance(s,self.countMap))
            if len(top3) > 3:
                heapq.heappop(top3)    
        result = []
        while top3:
            result.insert(0,heapq.heappop(top3).string)
        
        return result


    class Distance:
        def __init__(self, string, strMap):
            self.string = string
            self.strMap = strMap
        
        def __lt__(self, other):
            
            if self.strMap[self.string] == self.strMap[other.string]:
                return self.string > other.string
            return self.strMap[self.string] < self.strMap[other.string]
        def __gt__(self, other):
            if self.strMap[self.string] == self.strMap[other.string]:
                return self.string < other.string
            return self.strMap[self.string] > self.strMap[other.string]

    def insert(self, sentence):
            curr = self.root
            for i in range(len(sentence)):
                c = sentence[i]
                if curr.children[ord(c) - ord(' ')] == None:
                    curr.children[ord(c) - ord(' ')] = TrieNode()
                curr = curr.children[ord(c) - ord(' ')]
                curr.lst.append(sentence)
        
    def search(self, prefix):
        curr = self.root
        for i in range(len(prefix)):
            c = prefix[i]
            if curr.children[ord(c) - ord(' ')] == None:
                return []
            curr = curr.children[ord(c) - ord(' ')]
        return curr.lst
        
    


sentences = ["ironman", "janam", "ironman2", "ironman3"]
c = "ir"
times = [4, 2, 3, 4]
obj = AutocompleteSystem(sentences, times)
param_1 = obj.input(c)
print(param_1)