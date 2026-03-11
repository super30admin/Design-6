# Time Complexity : O(n * m), n = number of sentences, m = max sentence length for building Trie
# Space Complexity : O(total characters in sentences)
# Did this code successfully run on Leetcode : No
# Any problem you faced while coding this : No

# Explanation:
# 1. Build a Trie with sentences as keys and frequency as values.
# 2. When typing a character, traverse the Trie to find all sentences with current prefix.
# 3. Return top 3 sentences sorted by frequency and lexicographically.

from typing import List
import heapq
from collections import defaultdict

class TrieNode:
    def __init__(self):
        self.children = {}
        self.sentences = defaultdict(int)  # sentence -> frequency

class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.root = TrieNode()
        self.curr_input = ""
        self.curr_node = self.root
        
        for sentence, freq in zip(sentences, times):
            self.add_sentence(sentence, freq)

    def add_sentence(self, sentence: str, freq: int):
        node = self.root
        for c in sentence:
            if c not in node.children:
                node.children[c] = TrieNode()
            node = node.children[c]
            node.sentences[sentence] += freq

    def input(self, c: str) -> List[str]:
        results = []
        if c == "#":
            self.add_sentence(self.curr_input, 1)
            self.curr_input = ""
            self.curr_node = self.root
            return []
        
        self.curr_input += c
        if self.curr_node and c in self.curr_node.children:
            self.curr_node = self.curr_node.children[c]
            heap = [(-freq, sentence) for sentence, freq in self.curr_node.sentences.items()]
            heapq.heapify(heap)
            for _ in range(3):
                if heap:
                    results.append(heapq.heappop(heap)[1])
        else:
            self.curr_node = None
        return results