# 642. Design Search Autocomplete System
# https://leetcode.com/problems/design-search-autocomplete-system/

# Logic: make a Trie out of the input. Modify the trie to hold all the words that can be made using the prefix so far. 

# Space Complexity: O(sum of length input)

class TrieNode:
    def __init__(self):
        self.child = dict()
        self.sugWords = dict()
        
class AutocompleteSystem:
    def __init__(self, sentences: List[str], times: List[int]):
        self.root = TrieNode()
        self.searchWord = list()
        self.sentences = sentences
        self.times = times
        
        for i in range(len(self.sentences)):
            self.insertToTrie(self.sentences[i], self.times[i])
    
    def insertToTrie(self, word, freq):
    # Time Complexity: O(sum of length input)
        cur = self.root
        
        for i in word:
            if cur.child.get(i) == None:
                cur.child[i] = TrieNode()
            cur = cur.child[i]
            
            if word in cur.sugWords:
                cur.sugWords[word] += 1
            else:
                cur.sugWords[word] = freq
    
    def searchTrie(self, searchWord):
    # Time Complexity: O(sum of length input)
        cur = self.root
        
        for i in searchWord:
            if cur.child.get(i) == None:
                return []
            cur = cur.child[i]
        return [(i,cur.sugWords[i]) for i in cur.sugWords]
    
    def top3(self, worddict):
        worddict.sort(key = lambda x: (-x[1],x[0]))
        if len(worddict) < 3:
            return [i[0] for i in worddict]
        return [i[0] for i in worddict][:3]
    
    def input(self, c: str) -> List[str]:
    # Time Complexity: O(sum of length searchWord)
        if c == "#":
            self.insertToTrie("".join(self.searchWord), 1)
            self.cur = self.root
            self.searchWord = []
            
            return []
        else:
            self.searchWord.append(c)
            possibleWords = self.searchTrie(self.searchWord)
            return self.top3(possibleWords)