# Time Complexity : O(n)
# Space Complexity : O(n)
# Did this code successfully run on leetcode : Yes
# Any problem you faced while coding this : No

from typing import List

class TrieNode:

    def __init__(self):
        self._list = []
        self.children = {}

class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.hashmap = {}
        self._input = ""
        self.root = TrieNode()
        for i in range(0,len(sentences)):
            self.addTrieNode(self.root,sentences[i],times[i])

    def compare(self,x,y):
        number1 = self.hashmap.get(x)
        number2 = self.hashmap.get(y)
        if number1 == number2:
            if x < y:
                return -1
            else:
                return 1
        if number1 > number2:
            return -1
        else:
            return 1

    def addTrieNode(self,root,sentence,time):
        curr = root
        self.hashmap[sentence] = self.hashmap.get(sentence,0) + time

        for c in sentence:
            if c not in curr.children:
                curr.children[c] = TrieNode()

            curr = curr.children[c]
            if sentence not in curr._list:
                curr._list.append(sentence)
            curr._list=sorted(curr._list, key=functools.cmp_to_key(self.compare))
            if len(curr._list) > 3:
                curr._list.pop()

    def search(self,_input):
        curr = self.root
        for char in _input:
            if char not in curr.children:
                return []
            else:
                curr = curr.children[char]
        return curr._list

    def input(self, c: str) -> List[str]:
        if c == "#":
            self.addTrieNode(self.root,self._input,1)
            self._input = ""
            return []

        else:
            self._input =  self._input+"" +c
            self._input.replace(" ", "")
            return self.search(self._input)
