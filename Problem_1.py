class TrieNode:
    def __init__(self):
        self.pq = []
        self.children = {}
        
class AutocompleteSystem:
    def __init__(self, sentences: List[str], times: List[int]):
        self.root = TrieNode()
        self.hashmap = {}
        self.inputString = ''
        for i in range(len(sentences)):
            self.insertWord(sentences[i], times[i])
    
    def insertWord(self, sentence, time):
        self.hashmap[sentence] = self.hashmap.get(sentence, 0) + -time
        time = self.hashmap[sentence]
        curr = self.root
        for i in range(len(sentence)):
            if sentence[i] not in curr.children:
                curr.children[sentence[i]] = TrieNode()
            curr = curr.children[sentence[i]]   
            
            temp = sorted([(t, s) for t,s in curr.pq if s != sentence ] + [(time, sentence)])[:3]
            curr.pq = temp
                    
    def searchMap(self, prefix):
        curr = self.root
        for i in range(len(prefix)):
            if prefix[i] not in curr.children:
                return []
            curr = curr.children[prefix[i]]
        return [s for t, s in curr.pq]
                
    def input(self, c: str) -> List[str]:
        if c == '#':
            self.insertWord(self.inputString, 1)
            self.inputString = ''
            return []
        self.inputString += c
        return self.searchMap(self.inputString)


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)

