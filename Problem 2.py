class TrieNode:
    def __init__(self):
        self.dict = {}
        self.words = []

class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.root = TrieNode()
        self.search = ""
        #dictionary of words
        self.dictionary = defaultdict(int)
        for i in range(len(sentences)):
            self.dictionary[sentences[i]]= times[i] 
        for sentence in sentences:
            self.insertSentintoTrie(sentence)

    def insertSentintoTrie(self, sentence):
        cur = self.root
        for char in sentence:
            if char not in cur.dict:
                cur.dict[char] = TrieNode()
            cur = cur.dict[char]
            if sentence not in cur.words: cur.words.append(sentence)

            cur.words.sort(key= lambda x:(-self.dictionary[x], x))

            if len(cur.words) > 3:
                cur.words.pop()

    def searchPrefix(self):
        cur = self.root
        for char in self.search:
            if char not in cur.dict:
                return []
            cur = cur.dict[char]
        
        
        return cur.words
        
      

    def input(self, c: str) -> List[str]:
        if c == "#":
            self.dictionary[self.search] += 1
            self.insertSentintoTrie(self.search)
            self.search = ""
            return []

        self.search += c
        return self.searchPrefix()

        


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)
