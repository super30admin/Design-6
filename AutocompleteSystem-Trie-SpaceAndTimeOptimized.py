'''
    Time Complexity:
        O(1) (when searching)
        O(p) (where p = length of the sentence)

    Space Complexity:
        O(n) (where n = total number of sentences)

    Did this code successfully run on LeetCode?:
        Yes

    Problems faced while coding this:
        None

    Approach:
        Use Tries.
        Have a MinHeap of Top 3 suggestions along with the children at each TrieNode.
        Maintain a current pointer in Trie.
            Reset it to root when inserting.
            Set it to None when the lookup character is not in current node's children.
            Set it to curren't children otherwise.
'''

class AutocompleteSystem:
    TOP_SUGGESTIONS = 3

    def __init__(self, sentences: List[str], times: List[int]):
        self.search_string = []
        self.trie = Trie()

        for idx, sentence in enumerate(sentences):
            hotness = times[idx]
            self.__save_sentence(sentence, hotness)


    def input(self, c: str) -> List[str]:
        if c == '#':
            self.__add_search_string()
            return []

        self.search_string.append(c)
        return self.__get_suggestions()


    def __save_sentence(self, sentence, hotness):
        self.trie.insert(sentence, hotness)


    def __add_search_string(self):
        sentence = ''.join(self.search_string)
        self.__save_sentence(sentence, 1)
        self.search_string = []


    def __get_suggestions(self):
        last_char = self.search_string[-1]
        return self.trie.get_sentences(last_char)


class Sentence:
    def __init__(self, sentence, hotness = 0):
        self.sentence = sentence
        self.hotness = hotness


    def __lt__(self, other):
        if self.hotness == other.hotness:
            return self.sentence > other.sentence
        return self.hotness < other.hotness


    def __iadd__(self, hotness):
        self.hotness += hotness
        return self


class TrieNode:
    def __init__(self, char):
        self.char = char
        self.children = {}
        self.top_sentences = []


class Trie:
    def __init__(self):
        self.root = TrieNode('')
        self.curr = self.root
        self.sentences = {}


    def insert(self, sentence, hotness):
        curr = self.root

        if sentence not in self.sentences:
            self.sentences[sentence] = Sentence(sentence)

        sentence_obj = self.sentences[sentence]
        sentence_obj += hotness

        for char in sentence:
            if char not in curr.children:
                curr.children[char] = TrieNode(char)

            curr = curr.children[char]

            contains = False
            for obj in curr.top_sentences:
                if obj.sentence == sentence:
                    contains = True
                    break

            if contains:
                continue

            heappush(curr.top_sentences, sentence_obj)
            if len(curr.top_sentences) > AutocompleteSystem.TOP_SUGGESTIONS:
                heappop(curr.top_sentences)

        self._reset_cursor()


    def get_sentences(self, char):
        if not self.curr:
            return []

        if char not in self.curr.children:
            self.curr = None
            return []

        self.curr = self.curr.children[char]
        sentences = copy.deepcopy(self.curr.top_sentences)
        heapify(sentences)
        suggestions = []

        while sentences:
            sentence = heappop(sentences).sentence
            suggestions.append(sentence)

        suggestions.reverse()
        return suggestions


    def _reset_cursor(self):
        self.curr = self.root


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)
