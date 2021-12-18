# Brute Force Solution


import heapq

class Sentences:
    def __init__(self, sentence, time):
        self.sentence = sentence
        self.time = time
        
    def __lt__(self, other):
        if self.time == other.time:
            return self.sentence > other.sentence # Smaller one appears first
        return self.time < other.time # Min Heap
        
class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.hashmap = {}
        self.inputString = ''
        for i in range(len(sentences)):
            self.hashmap[sentences[i]] = self.hashmap.get(sentences[i], 0) + times[i]

    def input(self, c: str) -> List[str]:
        if c == '#':
            self.hashmap[self.inputString] = self.hashmap.get(self.inputString, 0) + 1
            self.inputString = ''
            return []
        self.inputString += c
        minheap = []
        for key, val in self.hashmap.items():
            if key.startswith(self.inputString):
                heapq.heappush(minheap, Sentences(key, val))
                if len(minheap) > 3:
                    heapq.heappop(minheap)
        result = []
        while len(minheap):
            result.append(heapq.heappop(minheap).sentence)
        result = result[::-1]
        return result

        
                
# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)



# better version because not searching through whole map
import heapq

class Sentences:
    def __init__(self, sentence, time):
        self.sentence = sentence
        self.time = time
        
    def __lt__(self, other):
        if self.time == other.time:
            return self.sentence > other.sentence # Smaller one appears first
        return self.time < other.time # Min Heap
    
class TrieNode:
    def __init__(self):
        self.countMap = {}
        self.children = {}
        
class AutocompleteSystem:
    def __init__(self, sentences: List[str], times: List[int]):
        self.root = TrieNode()
        self.inputString = ''
        for i in range(len(sentences)):
            self.insertWord(sentences[i], times[i])
            
    def insertWord(self, word, time):
        curr = self.root
        for i in range(len(word)):
            if word[i] not in curr.children:
                curr.children[word[i]] = TrieNode()
            curr = curr.children[word[i]]
            curr.countMap[word] = curr.countMap.get(word, 0) + time
            
    def searchMap(self, prefix):
        curr = self.root
        for i in range(len(prefix)):
            if prefix[i] not in curr.children:
                return {}
            curr = curr.children[prefix[i]]
        return curr.countMap
                
    def input(self, c: str) -> List[str]:
        if c == '#':
            self.insertWord(self.inputString, 1)
            self.inputString = ''
            return []
        self.inputString += c
        countMap = self.searchMap(self.inputString)
        minheap = []
        for key, val in countMap.items():
            if key.startswith(self.inputString):
                heapq.heappush(minheap, Sentences(key, val))
                if len(minheap) > 3:
                    heapq.heappop(minheap)
        result = []
        while len(minheap):
            result.append(heapq.heappop(minheap).sentence)
        result = result[::-1]
        return result


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)


# Better way - Maintaining Top 3 list
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