## Problem2 Design Autocomplete System (https://leetcode.com/problems/design-search-autocomplete-system/)
class Sentence:

    def __init__(self, sentence: str, freq: int):
        self.sentence: str = sentence
        self.freq = freq

    def __lt__(self, other):
        """
        Defines custom ordering for sorting sentences in matches:
        1. Higher frequency comes first (freq > other.freq).
        2. On frequency tie, lexicographically smaller string comes first (sentence < other.sentence).
        
        Time Complexity: O(1) for frequency comparison, up to O(L) for string comparison, 
                         where L is the length of the sentence.
        Space Complexity: O(1)
        """
        if self.freq == other.freq:
            return self.sentence < other.sentence
        return self.freq > other.freq


class TrieNode:

    def __init__(self):
        """
        Time Complexity: O(1)
        Space Complexity: O(1) auxiliary space (holds up to 3 Sentence objects in matches)
        """
        self.children: dict[str, TrieNode] = {}
        self.matches: list[Sentence] = []


class AutocompleteSystem:

    def __init__(self, sentences: list[str], times: list[int]):
        """
        Initializes the autocomplete system with historical sentences and their frequencies.
        
        Time Complexity: O(N * L^2) 
          - Where N is the number of historical sentences and L is the average sentence length.
          - Inserting each sentence updates L nodes, and sorting at each node takes O(1) time 
            because matches contains at most 4 items. String comparisons during sort take O(L).
        Space Complexity: O(N * L)
          - To store all characters across N sentences in the Trie.
        """
        self.root = TrieNode()
        self.count = {}
        self.current_input = []
        self.curr = self.root

        for sentence, time in zip(sentences, times):
            self.count[sentence] = time
            self.addToTrie(sentence)

    def addToTrie(self, sentence: str) -> None:
        """
        Inserts or updates a sentence in the Trie and maintains the top 3 matches at each prefix node.
        
        Time Complexity: O(L^2)
          - Traverses L nodes corresponding to characters in sentence.
          - At each node: filtering, appending, sorting 4 elements, and popping takes O(L) time 
            due to string comparison overhead.
        Space Complexity: O(L)
          - Creates up to L new TrieNodes if the path doesn't already exist.
        """
        node = self.root
        freq = self.count[sentence]

        for c in sentence:
            if c not in node.children:
                node.children[c] = TrieNode()
            node = node.children[c]

            # Filter out existing occurrence if updating frequency
            node.matches = [m for m in node.matches if m.sentence != sentence]
            node.matches.append(Sentence(sentence, freq))
            
            # Sort using Sentence.__lt__
            node.matches.sort()
            
            # Keep only the top 3 candidates
            if len(node.matches) > 3:
                node.matches.pop()

    def input(self, c: str) -> list[str]:
        """
        Processes a single input character.
        - If '#': commits sentence, updates frequency, resets state, returns [].
        - Else: moves to next prefix node and returns top 3 sentence strings.
        
        Time Complexity:
          - If c == '#': O(L^2) to join current_input array of length L and insert into Trie.
          - If c != '#': O(1) dictionary lookup and extracting string representations of up to 3 matches.
        Space Complexity:
          - If c == '#': O(L) to construct completedSentence string and reset buffer.
          - If c != '#': O(1) auxiliary space (excluding returned result list of size <= 3).
        """
        if c == '#':
            completedSentence = "".join(self.current_input)
            self.count[completedSentence] = (
                self.count.get(completedSentence, 0) + 1
            )
            self.addToTrie(completedSentence)
            self.current_input = []
            self.curr = self.root
            return []
        else:
            self.current_input.append(c)
            if self.curr and c in self.curr.children:
                self.curr = self.curr.children[c]
                return [match.sentence for match in self.curr.matches]
            else:
                self.curr = None
                return []