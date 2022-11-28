class TrieNode:
    def __init__(self):
        self.children = [None] * 128
        self.top3 = []


class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.root = TrieNode()
        self.map = {}
        for i, word in enumerate(sentences):
            self.map[word] = self.map.get(word, 0) + times[i]
            self.insert(word)
        self.sb = []

    def insert(self, word):
        curr = self.root
        for char in word:
            idx = ord(char) - ord(' ')
            if curr.children[idx] is None:
                curr.children[idx] = TrieNode()
            curr = curr.children[idx]
            if word not in curr.top3:
                curr.top3.append(word)

            curr.top3.sort(key=lambda x: (-1 * self.map[x], x))
            while len(curr.top3) > 3:
                curr.top3.pop()

    def search(self, search_term):  # return the list
        curr = self.root
        for char in search_term:
            idx = ord(char) - ord(' ')
            if curr.children[idx] == None:
                return []
            curr = curr.children[idx]
        return curr.top3

    def input(self, c: str) -> List[str]:
        if c == "#":
            search_term = "".join(self.sb)
            self.map[search_term] = self.map.get(search_term, 0) + 1
            self.insert(search_term)
            self.sb = []
            return []

        self.sb.append(c)
        return self.search("".join(self.sb))

# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)