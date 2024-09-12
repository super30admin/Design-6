"""
Time Complexity:    O(N + MlogK) where 
                    -   N is the length of the subquery or substring
                    -   M is the number of sentences starting with given subquery
                    -   K is the size of the Heap=>3
Space Complexity:   O(T * M) where
                    -   T is the size of the Trie Data structure (number of Trie Nodes)
                    -   M is the average length of frequency HashMap for each Trie Node

Here, we are maintaining a dictionary at every trienode in order tpo prevent a whole traversal and find the sentences
with that prefix. We are using a priority queue to get top k elements
"""

from collections import defaultdict
from heapq import heappush as insert
from heapq import heappop as remove


class TrieNode:
    def __init__(self, word=None):
        self.children = collections.defaultdict(lambda: TrieNode)
        self.map = collections.defaultdict(int)


class Pair:
    def __init__(self, sentence, count):
        self.sentence = sentence
        self.count = count

    def __lt__(self, other):
        if self.count == other.count:
            return self.sentence > other.sentence
        return self.count < other.count


class AutocompleteSystem(object):
    def __init__(self, sentences, times):
        self.root = TrieNode()
        self.ip = ""
        self.cursor = self.root
        for i in range(len(sentences)):
            self.insert(sentences[i], times[i])

    def insert(self, sentence, count):
        curr = self.root
        for s in sentence:
            if s not in curr.children:
                curr.children[s] = TrieNode()
            curr = curr.children[s]
            if sentence in curr.map:
                curr.map[sentence] += count
            else:
                curr.map[sentence] = count

    def search(self, prefix):
        curr = self.root
        for p in prefix:
            if p not in curr.children:
                return []
            curr = curr.children[p]
        return curr.map

    def getValues(self, countMap):
        heap = []
        for key in countMap:
            count = countMap[key]
            p = Pair(key, count)
            insert(heap, p)
            if len(heap) > 3:
                remove(heap)
        stack = []
        while len(heap) > 0:
            stack.append(remove(heap).sentence)
        return stack[::-1]

    def input(self, c):
        if c == '#':
            self.insert(self.ip, 1)
            self.ip = ""
            self.cursor = self.root
            return []
        self.ip += c
        countMap = self.search(self.ip)
        result = self.getValues(countMap)
        return result
            


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)