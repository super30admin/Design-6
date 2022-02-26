"""
Design a search autocomplete system for a search engine. 
Users may input a sentence (at least one word and end with a special character '#').

You are given a string array sentences and an integer array times both of length n 
where sentences[i] is a previously typed sentence and times[i] is the corresponding 
number of times the sentence was typed. For each input character except '#', 
return the top 3 historical hot sentences that have the same prefix 
as the part of the sentence already typed.

Here are the specific rules:

The hot degree for a sentence is defined as the number of times a user typed 
the exactly same sentence before.
The returned top 3 hot sentences should be sorted by hot degree 
(The first is the hottest one). If several sentences have the same hot degree, 
use ASCII-code order (smaller one appears first).
If less than 3 hot sentences exist, return as many as you can.
When the input is a special character, it means the sentence ends, 
and in this case, you need to return an empty list.
Implement the AutocompleteSystem class:

AutocompleteSystem(String[] sentences, int[] times) Initializes the object with the sentences 
and times arrays.
List<String> input(char c) This indicates that the user typed the character c.
Returns an empty array [] if c == '#' and stores the inputted sentence in the system.
Returns the top 3 historical hot sentences that have the same prefix as the part of 
the sentence already typed. If there are fewer than 3 matches, return them all.

"""

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

# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c) 

