#Solution 1
class AutocompleteSystem:
    #Approach: Trie + HashMap + Constant-time sorting
    #Time Complexity: O(n * l) for init; O(n) for input
    #Space Complexity: O(n * l)
    #where, n is the total number of sentences and l is the average length of a sentence
    #and, m is the current length of the input
    #and, p is the size of prefixMap at the current TrieNode
    
    def __init__(self, sentences: List[str], times: List[int]):
        self.map = {}
        self.root = TrieNode()
        self.input_sb = []
        
        for i in range(len(sentences)):
            self.map[sentences[i]] = self.map.get(sentences[i], 0) + times[i]
            self.insert(sentences[i])
            
    def insert(self, sentence):
        curr = self.root
        for char in sentence:
            if char not in curr.children:
                curr.children[char] = TrieNode()
            curr = curr.children[char]
            
            #adding the sentence if not already in top3 && updating the pair objects
            temp = set([i.sentence for i in curr.top3])
            temp.add(sentence)
            curr.top3 = [Pair(self.map[i], i) for i in temp]
            curr.top3.sort(reverse=True)
            curr.top3 = curr.top3[:3]
        
    def search(self, input_sb):
        curr = self.root
        for char in input_sb:
            if char not in curr.children:
                return {}
            curr = curr.children[char]
        
        return curr.top3

    def input(self, c: str) -> List[str]:
        if c == '#':
            input_str = ''.join(self.input_sb)
            self.map[input_str] = self.map.get(input_str, 0) + 1
            self.insert(input_str)
            self.input_sb = []
            return
            
        self.input_sb.append(c)
        top3 = [i.sentence for i in self.search(self.input_sb)]
        return top3

class TrieNode:
    def __init__(self):
        self.top3 = []
        self.children = {}
    
#Solution 2
"""
class AutocompleteSystem:
    #Approach: Trie + HashMap + Min Heap
    #Time Complexity: O(n * l) for init; O(n * m) for input, but average case is much better
    #Space Complexity: O(n^2 * l)
    #where, n is the total number of sentences and l is the average length of a sentence
    #and, m is the current length of the input
    
    def __init__(self, sentences: List[str], times: List[int]):
        self.root = TrieNode()
        self.input_sb = []
        
        for i in range(len(sentences)):
            self.insert(sentences[i], times[i])
            
    def insert(self, sentence, times):
        curr = self.root
        for char in sentence:
            if char not in curr.children:
                curr.children[char] = TrieNode()
            curr = curr.children[char]
            curr.countMap[sentence] = curr.countMap.get(sentence, 0) + times
        
    def search(self, input_sb):
        curr = self.root
        for char in input_sb:
            if char not in curr.children:
                return {}
            curr = curr.children[char]
        return curr.countMap

    def input(self, c: str) -> List[str]:
        if c == '#':
            input_str = ''.join(self.input_sb)
            self.insert(input_str, 1)
            self.input_sb = []
            return
            
        self.input_sb.append(c)
        prefixMap = self.search(self.input_sb)
        
        heap = []
        for sentence in prefixMap:
            if sentence.startswith(''.join(self.input_sb)):
                heappush(heap, Pair(prefixMap[sentence], sentence))
                if len(heap) > 3:
                    heappop(heap)
        
        result = []
        while heap:
            result.insert(0, heappop(heap).sentence)
            
        return result
        
class TrieNode:
    def __init__(self):
        self.countMap = {}
        self.children = {}
"""
    
#Solution 3
"""
class AutocompleteSystem:
    #Approach: HashMap + Min Heap
    #Time Complexity: O(n * m)   // heap operations are: O(n log 3) -> O(n)
    #Space Complexity: O(n * l)
    #where, n is the total number of sentences and l is the average length of a sentence
    #and, m is the current length of the input
    
    def __init__(self, sentences: List[str], times: List[int]):
        self.map = {}
        self.input_sb = []
        
        for i in range(len(sentences)):
            self.map[sentences[i]] = self.map.get(sentences[i], 0) + times[i]

    def input(self, c: str) -> List[str]:
        if c == '#':
            input_str = ''.join(self.input_sb)
            self.map[input_str] = self.map.get(input_str, 0) + 1
            self.input_sb = []
            return
            
        self.input_sb.append(c)
        
        heap = []
        for sentence in self.map:
            if sentence.startswith(''.join(self.input_sb)):
                heappush(heap, Pair(self.map[sentence], sentence))
                if len(heap) > 3:
                    heappop(heap)
        
        result = []
        while heap:
            result.insert(0, heappop(heap).sentence)
            
        return result
"""

class Pair:
    def __init__(self, freq, sentence):
        self.freq = freq
        self.sentence = sentence
        
    def __lt__(self, other):
        if self.freq != other.freq:
            return self.freq < other.freq
        else:
            return self.sentence > other.sentence

# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)