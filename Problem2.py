#I created a system to provide autocomplete suggestions based on user input. The class uses a Trie data structure to store sentences and their frequencies, which allows for efficient prefix-based querying. During initialization, sentences and their corresponding frequencies are inserted into the Trie using the _insert method. The input method handles character inputs and updates the current state of the Trie based on the input. If the input is not "#", it traverses the Trie to collect sentences that match the current prefix and returns the top 3 most frequent ones. If the input is "#", it finalizes the current sentence and resets the state. The time complexity of inserting a sentence into the Trie is O(L), where L is the length of the sentence, and querying the Trie has a time complexity of O(NlogK), where N is the number of sentences under the current prefix and K is the number of sentences with the same frequency. The space complexity is O(S), where S is the total length of all sentences stored in the Trie plus the space used for storing the Trie nodes and sentence frequencies.

class AutocompleteSystem:
    def __init__(self, sentences: List[str], times: List[int]):
        self.trie = {}
        for sentence, time in zip(sentences, times):
            self._insert(sentence,time)
        self.curr = self.trie
        self.currSentence = []

    def _insert(self, sentence,time = 1):
        curr = self.trie
        for idx,c in enumerate(sentence):
            curr[c] = curr.get(c,{})
            curr = curr[c]
            curr["sentences"] = curr.get("sentences",{})
            curr["sentences"][sentence] = curr["sentences"].get(sentence,0)+time

    def input(self, c: str) -> List[str]:
        ans = []
        if c != "#":
            self.currSentence.append(c)
            if c in self.curr:
                self.curr = self.curr[c]
                items = [(-time, sentence) for sentence,time in self.curr["sentences"].items()]
                ans = [elt[1] for elt in heapq.nsmallest(3, items)]
            else:
                self.curr = {}
        elif c == "#":
            self._insert(''.join(self.currSentence))
            self.curr = self.trie
            self.currSentence = []
        return ans
        
        


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)