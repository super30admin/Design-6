# Time Complexity : O()
# Space Complexity : O()
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No


# using heap to keep track of top 3

# using trie and for each node storing the top three repeated strings
# and returning when input is provided
from heapq import heapify,heappush as push, heappop as pop

class TrieNode:
    def __init__(self):
        self.map = {}
        self.children = {}
    
class AutocompleteSystem:
    def __init__(self,sentences,times):
        self.root = TrieNode()
        self.input = ""

        for i in range(len(sentences)):
            self.insert(sentences[i],times[i])

    def insert(self,word, times):
        curr = self.root

        for i in word:
            if i not in curr.children:
                curr.children[i] = TrieNode()
            curr = curr.children[i]
            if word not in curr.map:
                curr.map[word] = 0
            curr.map[word] += times
    
    def search(self,prefix):
        curr = self.root

        for i in prefix:
            if i not in curr.children:
                return {}
            curr = curr.children[i]
        return curr.map
    
    def input(self,c):

        if c=="#":
            self.insert(self.input,1)
            self.input = ""
        pq = []
        heapify(pq)
        self.input += c
        count = self.search(self.input)

        for key in count:
            if key.startswith(self.input):
                push(pq,(count.times,key))
                if len(pq) > 3:
                    pop(pq)
        result = []
        while len(pq) != 0:
            result.append(pop(pq))
        return result





            


        


