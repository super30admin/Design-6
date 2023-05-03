# Compare class for heap
# Time O(N * log(k)) where n is the number of sentences in trie and k is the size of the minheap
class Compare:
    def __init__(self, time, sentence):
        self.time = time
        self.sentence = sentence

    def __lt__(self, other):
        if self.time == other.time:
            return self.sentence > other.sentence
        else:
            return self.time < other.time


class TrieNode:

    def __init__(self):
        self.children = {}
        self.cache = collections.defaultdict(int)


class Trie:
    def __init__(self) -> None:
        self.trieRoot = TrieNode()
        # searchRoot store the position of the last input charactor
        self.searchRoot = self.trieRoot

    def build(self, sentence, time):
        trie = self.trieRoot
        for c in sentence:
            if c not in trie.children.keys():
                trie.children[c] = TrieNode()
            trie = trie.children[c]
            # Update cache when add new sentence
            trie.cache[sentence] += time

    def search(self, c):
        # Reset searchRoot when input is end (c == '#')
        if c == '#':
            self.searchRoot = self.trieRoot
            return {}
        # searchRoot set to None when we cannot find any sentence match the current prefix
        # Thus, return empty cache since no any match.
        if self.searchRoot == None:
            return {}
        trie = self.searchRoot
        if c not in trie.children.keys():
            self.searchRoot = None
            return {}
        else:
            trie = trie.children[c]
            # update searchRoot pointed to the current charactor
            self.searchRoot = trie
        return trie.cache


class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.trie = Trie()
        # build Trie
        for i in range(len(sentences)):
            self.trie.build(sentences[i], times[i])
        # All charactors that user enter before user enter '#'
        self.curWord = ""

    def input(self, c: str) -> List[str]:
        cache = self.trie.search(c)
        if c == "#":
            self.trie.build(self.curWord, 1)
            self.curWord = ""
        else:
            self.curWord += c
        # Use heap to find top 3 hot keys in cache
        heap = []
        for sentence, count in cache.items():
            # Refer to rule in Compare class
            heapq.heappush(heap, Compare(count, sentence))
            if len(heap) > 3:
                heapq.heappop(heap)
        output = []
        while heap:
            output.append(heapq.heappop(heap).sentence)
        # reverse the output since we want the output sorted by hot degree
        return output[::-1]

# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)