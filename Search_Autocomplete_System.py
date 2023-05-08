# Time Complexity : O(n + m log m), where n is the length of the prefix and m is the total number of sentences in the Trie.
# Space Complexity : O(m), where m is the total number of characters in all sentences
from typing import List


class AutocompleteSystem:
    def __init__(self, sentences: List[str], times: List[int]):
        self.root = TrieNode()
        self.keyword = ""
        self.search_results = []
        
        for i in range(len(sentences)):
            self.insert(sentences[i], times[i])

    def insert(self, sentence: str, times: int) -> None:
        node = self.root
        
        for char in sentence:
            if char not in node.children:
                node.children[char] = TrieNode()
            node = node.children[char]
        
        node.is_sentence = True
        node.frequency += times

    def search(self, prefix: str) -> List[str]:
        node = self.root
        self.keyword = prefix
        self.search_results = []

        for char in prefix:
            if char not in node.children:
                return []
            node = node.children[char]
        
        self.dfs(node, prefix)
        
        return [sentence[1] for sentence in sorted(self.search_results, key=lambda x: (-x[0], x[1]))][:3]

    def dfs(self, node, sentence):
        if node.is_sentence:
            self.search_results.append((node.frequency, sentence))
        
        for char in node.children:
            self.dfs(node.children[char], sentence + char)


class TrieNode:
    def __init__(self):
        self.children = {}
        self.is_sentence = False
        self.frequency = 0
