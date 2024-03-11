#Time Complexity : Initialization: O(N * M), Input: O(M), Autocomplete Suggestions: O(M + K * M)
#Space Complexity :O(N * M + K)
#Did this code successfully run on Leetcode :No
#Any problem you faced while coding this : No


from ast import List
import heapq


class TrieNode:
    def __init__(self):
        self.children = {}
        self.is_word = False
        self.word = None
        self.hotness = 0

class AutocompleteSystem:
    def __init__(self, sentences: List[str], times: List[int]):
        self.root = TrieNode()
        self.keyword = ''
        self.reset()

        for i in range(len(sentences)):
            self.insert(sentences[i], times[i])

    def insert(self, sentence, hotness):
        node = self.root
        for char in sentence:
            if char not in node.children:
                node.children[char] = TrieNode()
            node = node.children[char]
        node.is_word = True
        node.word = sentence
        node.hotness -= hotness

    def dfs(self, node):
        if node.is_word:
            self.queue.append((node.hotness, node.word))
        for child in node.children.values():
            self.dfs(child)

    def search(self, prefix):
        node = self.root
        for char in prefix:
            if char not in node.children:
                return []
            node = node.children[char]

        self.queue = []
        self.dfs(node)
        return [sentence for hotness, sentence in heapq.nsmallest(3, self.queue)]

    def input(self, c: str) -> List[str]:
        if c == '#':
            self.insert(self.keyword, self.time)
            self.reset()
            return []
        else:
            self.keyword += c
            result = self.search(self.keyword)
            return result

    def reset(self):
        self.keyword = ''
        self.time = 1
