# Time complexity : O(nk) where k = 3
# Space complexity : O(n * L * 4)
# Program ran on leetcode successfully

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
                self.freq[tempSearch] = 1
            else:
                self.freq[tempSearch] += 1
            
            self.insert(tempSearch)
            self.inputstr = ''
            return []
        self.inputStr += c
        prefix = self.inputStr
        return self.search(prefix)
    

    def insert(self, sentence):
            curr = self.root
            for i in range(len(sentence)):
                c = sentence[i]
                if curr.children[ord(c) - ord(' ')] == None:
                    curr.children[ord(c) - ord(' ')] = TrieNode()
                curr = curr.children[ord(c) - ord(' ')]
                if sentence not in curr.lst:
                    curr.lst.append(sentence)
                
                curr.lst.sort(key = lambda x : (-self.countMap[x],x))
                if len(curr.lst) > 3:
                    curr.lst.pop()
                
    
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