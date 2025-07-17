"""
I implemented this autocomplete system using a Trie where each node keeps the top 3 most frequent sentences passing through it. I used a hashmap to store sentence frequencies. When the user finishes a sentence (with #), I update the count and re-insert it into the Trie. The input() function builds the current prefix and returns suggestions by traversing the Trie.
Time Complexity: O(L) per insertion.
Space Complexity: O(N*L), where N is the number of nodes and L is sentence length. 
"""
class TrieNode:
    def __init__(self):
        self.children = {}
        self.li = []

class AutocompleteSystem:

    def __init__(self, sentences: list[str], times: list[int]):
        self.root = TrieNode()
        self.map = defaultdict(int)
        self.inp = ""

        for sentence, time in zip(sentences, times):
            self.map[sentence] += time
            self.insert(sentence)

    def insert(self, sentence: str):
        curr = self.root
        for char in sentence:
            if char not in curr.children:
                curr.children[char] = TrieNode()
            curr = curr.children[char]
            if sentence not in curr.li:
                curr.li.append(sentence)
            curr.li.sort(key=lambda x: (-self.map[x], x))
            if len(curr.li) > 3:
                curr.li.pop()

    def search(self, prefix: str) -> list[str]:
        curr = self.root
        for char in prefix:
            if char not in curr.children:
                return []
            curr = curr.children[char]
        return curr.li

    def input(self, c: str) -> list[str]:
        if c == '#':
            self.map[self.inp] += 1
            self.insert(self.inp)
            self.inp = ""
            return []
        self.inp += c
        return self.search(self.inp)