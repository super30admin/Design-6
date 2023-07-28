from collections import defaultdict
import heapq

class AutocompleteSystem:
    class TrieNode:  # Move TrieNode class outside of __init__
        def __init__(self):
            # children dictionary to store child nodes (character to TrieNode mapping)
            self.children = defaultdict(AutocompleteSystem.TrieNode)  # Use AutocompleteSystem.TrieNode here
            # List to store sentence completions starting from the current node
            self.startsWith = []

    def __init__(self, sentences, times):
        # Initialize the Trie, map to store frequencies, and search term
        self.root = self.TrieNode()
        self.map = defaultdict(int)
        self.search = ""

        # Build the Trie and update the frequency map
        for i in range(len(sentences)):
            sentence = sentences[i]
            time = times[i]
            self.map[sentence] += time
            self.insert(sentence)

    def insert(self, word):
        curr = self.root
        for char in word:
            if char not in curr.children:
                curr.children[char] = self.TrieNode()  # Use self.TrieNode() here
            curr = curr.children[char]
            top3 = curr.startsWith
            if word not in top3:
                top3.append(word)
            # Sort the completions based on frequency and lexicographical order
            top3.sort(key=lambda w: (-self.map[w], w))
            if len(top3) > 3:
                top3.pop()

    def search_trie(self, word):
        curr = self.root
        for char in word:
            if char not in curr.children:
                return []
            curr = curr.children[char]
        return curr.startsWith

    def input(self, c):
        if c == '#':
            # Whatever the search term is, add it to cache and reset the search term
            search_term = self.search
            self.search = ""
            self.map[search_term] += 1
            self.insert(search_term)
            return []
        
        # Append the character to the current search term and get completions
        self.search += c
        search_term = self.search
        return self.search_trie(search_term)


# Time Complexity:
# - Initialization: O(N * L * log(K))
#   - N: number of sentences
#   - L: average length of a sentence
#   - K: number of completions (typically, K <= 3)
# - Input function: O(K * L * log(K))
#   - K: number of completions (typically, K <= 3)
#   - L: average length of a sentence

# Space Complexity:
# - Initialization: O(N * L)
#   - N: number of sentences
#   - L: average length of a sentence
# - Input function: O(L)
#   - L: average length of a sentence
# https://www.youtube.com/watch?v=tlCkGtttyjk