# Time Complexity : O(L) where L is the length of the input string
# Space Complexity : O(M * L) where M is the number of unique sentences and L is the average length of the sentences
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Your code here along with comments explaining your approach:
# I am using a Trie data structure to store the sentences and their frequencies so that I can efficiently retrieve the top 3 sentences for a given prefix
# Each TrieNode contains an array of children nodes and a list of top 3 sentences that pass through that node
# In the constructor, I am inserting all the sentences into the Trie and updating their frequencies in a map
# The insert function inserts a sentence into the Trie and updates the top 3 sentences at each node
# The searchP function retrieves the top 3 sentences for a given prefix by traversing the Trie
# The input function processes each character input, updating the current search string and returning the top 3 sentences for the current prefix or updating the Trie if the input is "#"   



from typing import List
class TrieNode:
    def __init__(self):
        self.children = [None] * 256
        self.top3 = []
class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.root = TrieNode()
        self.search = ""
        self.map = {}
        for i in range(len(sentences)):
            self.map[sentences[i]]  = self.map.get(sentences[i],0) + times[i]
            self.insert(sentences[i])
    def insert(self,word):
        curr=self.root  
        for i in word:
            idx = ord(i) - ord(' ')
            if curr.children[idx] is None:
                curr.children[idx] = TrieNode()
            curr=curr.children[idx]
            l = curr.top3
            if word not in l:
                l.append(word)
            l.sort(key=lambda x:(-self.map[x],x))
            if len(l)>3:
                l.pop()
    def searchP(self,prefix):
        curr = self.root
        for i in prefix:
            idx = ord(i) - ord(' ')
            if curr.children[idx] is None:
                return []
            curr = curr.children[idx]
        return curr.top3

    def input(self, c: str) -> List[str]:
        if c == "#":
            self.map[self.search] = self.map.get(self.search,0) + 1
            self.insert(self.search)
            self.search = ""
            return []
        self.search += c
        return self.searchP(self.search)
        


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)