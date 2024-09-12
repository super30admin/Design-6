class Trie():
    def __init__(self):
        self.root = TrieNode('')
        self.curr = self.root
        self.sentences = {}
        
    def insert(self, sentence:str, time:int)->None:
        curr = self.root
        if sentence not in self.sentences:
            self.sentences[sentence] = Sentence(sentence)
        sentence_obj = self.sentences[sentence]
        sentence_obj += time
        for char in sentence:
            if char not in curr.child:
                curr.child[char] = TrieNode(char)
            curr = curr.child[char]
            contains = False
            for obj in curr.topSentences:
                if obj.sentence == sentence:
                    contains = True
                    break
            if contains:
                continue
            heappush(curr.topSentences, sentence_obj)
            if len(curr.topSentences) > 3:
                heappop(curr.topSentences)
        self.curr = self.root

    def get_sentences(self, char):
        if not self.curr:
            return []
        if char not in self.curr.child:
            self.curr = None
            return []
        self.curr = self.curr.child[char]
        sentences = copy.deepcopy(self.curr.topSentences)
        heapify(sentences)
        suggestions = []
        while sentences:
            sentence = heappop(sentences).sentence
            suggestions.append(sentence)
        suggestions.reverse()
        return suggestions
    
class TrieNode():
    def __init__(self,char):
        self.char = char
        self.child = {}
        self.topSentences = []
        
class Sentence:
    def __init__(self, sentence, time = 0):
        self.sentence = sentence
        self.time = time


    def __lt__(self, other):
        if self.time == other.time:
            return self.sentence > other.sentence
        return self.time < other.time


    def __iadd__(self, time):
        self.time += time
        return self

class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.search_string = []
        self.trie = Trie()

        for idx, sentence in enumerate(sentences):
            time = times[idx]
            self.trie.insert(sentence, time)

    def input(self, c: str) -> List[str]:
        if c == '#':
            sentence = ''.join(self.search_string)
            self.trie.insert(sentence, 1)
            self.search_string = []
            return []

        self.search_string.append(c)
        last_char = self.search_string[-1]
        return self.trie.get_sentences(last_char)

# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)