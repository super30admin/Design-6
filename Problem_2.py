# class HeapItem:
#     def __init__(self, freq, sentence):
#         self.freq = freq
#         self.sentence = sentence

#     def __lt__(self, other):
#         if self.freq == other.freq:
#             return self.sentence > other.sentence
#         else:
#             return self.freq < other.freq


# class AutocompleteSystem:

#     def __init__(self, sentences: List[str], times: List[int]):
#         self.wordMap = {}
#         self.currInput = ""
#         for i in range(len(times)):
#             self.wordMap[sentences[i]] = times[i]

#     def input(self, c: str) -> List[str]:
#         if c == "#":
#             self.wordMap[self.currInput] = self.wordMap.get(self.currInput, 0) + 1
#             self.currInput = ""
#             return []
#         else:
#             self.currInput += c
#             pq = []
#             for key, val in self.wordMap.items():
#                 if key.startswith(self.currInput):
#                     heapq.heappush(pq, HeapItem(val, key))
#                     if len(pq) > 3:
#                         heapq.heappop(pq)
#             result = []
#             while pq:
#                 heapitem = heapq.heappop(pq)
#                 result.append(heapitem.sentence)
#             result.reverse()
#             return result


# # Your AutocompleteSystem object will be instantiated and called as such:
# # obj = AutocompleteSystem(sentences, times)
# # param_1 = obj.input(c)


class HeapItem:
    def __init__(self, freq, sentence):
        self.freq = freq
        self.sentence = sentence

    def __lt__(self, other):
        if self.freq == other.freq:
            return self.sentence > other.sentence
        else:
            return self.freq < other.freq


class TrieNode:
    def __init__(self):
        self.children = [None] * 256
        self.wordList = []


class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.root = TrieNode()
        self.wordMap = {}
        self.currInput = ""
        for i in range(len(times)):
            self.wordMap[sentences[i]] = times[i]
            self.insertWord(sentences[i])

    def insertWord(self, word):
        curr = self.root
        for c in word:
            idx = ord(c)
            if not curr.children[idx]:
                newNode = TrieNode()
                curr.children[idx] = newNode
            curr = curr.children[idx]
            if word not in curr.wordList:
                curr.wordList.append(word)

    def startWith(self):
        curr = self.root
        for c in self.currInput:
            idx = ord(c)
            if not curr.children[idx]:
                return []
            curr = curr.children[idx]
        return curr.wordList

    def input(self, c: str) -> List[str]:
        if c == "#":
            self.wordMap[self.currInput] = self.wordMap.get(self.currInput, 0) + 1
            self.insertWord(self.currInput)
            self.currInput = ""
            return []
        else:
            self.currInput += c
            sentenceFound = self.startWith()
            pq = []
            for sentence in sentenceFound:
                heapq.heappush(pq, HeapItem(self.wordMap[sentence], sentence))
                if len(pq) > 3:
                    heapq.heappop(pq)
            result = []
            while pq:
                heapitem = heapq.heappop(pq)
                result.append(heapitem.sentence)
            result.reverse()
            return result


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)
