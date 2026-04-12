# Time Complexity : insert(word): O(L × log 3) = O(L), where L = length of word. Sorting 3 elements is constant. input(c): O(P) = O(1), where P = length of current prefix.
# Space Complexity : O(M × L), where M = number of unique sentences and L = average length.
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
# Approach : We build a Trie where each node keeps track of top 3 hot sentences seen so far.
# When typing a character, we walk down the Trie and return the node’s top 3 list.
# If we hit #, we record the full sentence and update the Trie with it.

class AutocompleteSystem:
    class TrieNode:
        def __init__(self):
            self.children = [None] * 256
            self.top3Results = []

    def __init__(self, sentences, times):
        self.map = {}
        self.search = ""
        self.root = self.TrieNode()
        for i in range(len(sentences)):
            self.map[sentences[i]] = self.map.get(sentences[i], 0) + times[i]
            self.insert(sentences[i])

    def insert(self, word):
        curr = self.root
        for c in word:
            idx = ord(c) - ord(' ')
            if curr.children[idx] is None:
                curr.children[idx] = self.TrieNode()
            curr = curr.children[idx]
            lst = curr.top3Results
            if word not in lst:
                lst.append(word)
            lst.sort(key=lambda x: (-self.map[x], x))
            if len(lst) > 3:
                lst.pop()

    def searchPrefix(self, prefix):
        curr = self.root
        for c in prefix:
            idx = ord(c) - ord(' ')
            if curr.children[idx] is None:
                return []
            curr = curr.children[idx]
        return curr.top3Results

    def input(self, c):
        if c == '#':
            self.map[self.search] = self.map.get(self.search, 0) + 1
            self.insert(self.search)
            self.search = ""
            return []
        self.search += c
        return self.searchPrefix(self.search)

# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)