"""
Use a Trie where each node stores a map of sentence - frequency for quick prefix match
As characters stream in, traverse the trie and collect matching sentences
On #, update sentence frequency and reset the current input
"""
"""
Time Complexity:
insert() - O(L²)  L = length of sentence (due to update at each level)
input() - O(P + N log N) where P = prefix length, N = # of matched sentences
Space Complexity:  O(N * L) for storing sentences in Trie with counts
"""

from collections import defaultdict
import heapq

class TrieNode:
    def __init__(self):
        self.children = dict()
        self.counts = defaultdict(int)

class autocompleteSystem:

    def __init__(self, sentences: list[str], times: list[int]):
        self.root = TrieNode()
        self.curr_node = self.root
        self.curr_input = ""

        for sentence, time in zip(sentences, times):
            self.insert(sentence, time)

    def insert(self, sentence: str, freq: int):
        node = self.root
        for char in sentence:
            if char not in node.children:
                node.children[char] = TrieNode()
            node = node.children[char]
            node.counts[sentence] += freq

    def input(self, c: str) -> list[str]:
        if c == '#':
            self.insert(self.curr_input, 1)
            self.curr_input = ""
            self.curr_node = self.root
            return []

        self.curr_input += c
        if self.curr_node and c in self.curr_node.children:
            self.curr_node = self.curr_node.children[c]
        else:
            self.curr_node = None
            return []

        heap = []
        for sentence, freq in self.curr_node.counts.items():
            heapq.heappush(heap, (-freq, sentence))
        res = []
        for _ in range(3):
            if heap:
                res.append(heapq.heappop(heap)[1])
        return res
if __name__ == "__main__":
    system = autocompleteSystem(
        ["i love you", "island", "iroman", "i love leetcode"], 
        [5, 3, 2, 2]
    )

    print(system.input('i'))  
    print(system.input(' ')) 
    print(system.input('a'))  
    print(system.input('#')) 
