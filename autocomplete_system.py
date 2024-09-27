# Time Complexity : input: O(n) where n is the length of input
# Space Complexity : O(m) where m is the number of letters in all the words
# Did this code successfully run on Leetcode : Yes
# At each input # I store the word in trie and store top 3 words based on sorting criteria at each TrieNode. For other inputs, I traverse the nodes and return 
# sorted list of top 3 words at that node

class TextPair:
    def __init__(self, string, frequency):
        self.string = string
        self.frequency = frequency
        
    def __str__(self):
        return self.string + " " + str(self.frequency)
    
    def __repr__(self):
        return self.__str__()
        
    def __lt__(self, other):
        if self.frequency == other.frequency:
            return self.string < other.string
        else:
            return self.frequency > other.frequency
        
    def __eq__(self, other):
        return self.frequency == other.frequency and self.string == other.string
            
class TrieNode:
    def __init__(self):
        self.children = { k:None for k in (list(string.ascii_lowercase)+[" "])}
        self.pq = []
        self.counts = 0
        
class Trie:
    def __init__(self):
        self.root = TrieNode()
    
    def insert(self, word, frequency):
        curr = self.root
        for w in word:
            if curr.children[w]:
                curr = curr.children[w]
                contains = False
                for pair in curr.pq:
                    if pair.string == word:
                        pair.frequency = frequency
                        contains = True
                if not contains:
                    curr.pq.append(TextPair(word, frequency))
                    curr.pq.sort()
                    if len(curr.pq)>3:
                        curr.pq = curr.pq[:-1]
            else:
                curr.children[w] = TrieNode()
                curr = curr.children[w]
                curr.pq.append(TextPair(word, frequency))
            
            
    def find(self, word):
        curr = self.root
        for w in word:
            if curr.children[w]:
                curr = curr.children[w]
            else:
                return []
        return sorted(curr.pq)
        
    
class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.curr_search = ""
        self.mapping = defaultdict(lambda: 0)
        self.trie = Trie()
        for i in range(len(sentences)):
            self.mapping[sentences[i]] = times[i]
            self.trie.insert(sentences[i], times[i])

    def input(self, c: str) -> List[str]:
        if c == "#":
            self.mapping[self.curr_search] += 1
            self.trie.insert(self.curr_search, self.mapping[self.curr_search])
            self.curr_search = ""
            return []
        else:
            self.curr_search += c
            result = self.trie.find(self.curr_search)
            return [x.string for x in result] if len(result) else []

