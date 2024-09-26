# Time Complexity : O(N + mlogK)
# Space Complexity : O(TM) trie*avgLengthOfHM
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No


# Your code here along with comments explaining your approach

class TrieNode:
    def __init__(self):
        self.children = defaultdict()
        
        self.countMap = defaultdict()

class CountMap:
    
    def __init__(self, sentence, freq):
        self.sentence = sentence
        self.freq = freq
        
    def __lt__(self, o):
        if self.freq == o.freq:
            return self.sentence > o.sentence 
        return self.freq < o.freq 
    def ___gt__(self, o):
        if self.freq == o.freq:
            return self.sentence < o.sentence
        return self.freq > o.freq
    def __eq__(self, o):
        return (self.freq == o.freq) and (self.sentence == o.sentence)
    
class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.root = TrieNode()
        self.inp = []
        self.map = defaultdict()
        self.pointer = self.root 
        for i in range(len(sentences)):
            if sentences[i] not in self.map:
                self.map[sentences[i]] = times[i]

            #self.insert(sentences[i], times[i])
            
    def insert(self, sentence, time):
        curr = self.root 
        for char in sentence:
            if char not in curr.children:
                curr.children[char] = TrieNode()
            curr = curr.children[char]
            
            if sentence in curr.countMap:
                curr.countMap[sentence] += 1
            else:
                curr.countMap[sentence] = time
        
        #curr.isEnd = True
    def search(self, input):
        curr= self.root
        for i in range(len(input)):
            char = input[i]
            if char not in curr.children:
                return {}
            curr = curr.children[char]
            
        return curr.count           
    #fix it later
    def input(self, c: str) -> List[str]:
        
        if c == '#':
            # if ''.join(self.inp) not in self.map:
            #     self.map[''.join(self.inp)] = 0
            # else:
            #     self.map[''.join(self.inp)] += 1
            self.insert(''.join(self.inp), 1)
            self.inp = []
            self.pointer = self.root 
            return []
        self.inp.append(c)
        if c not in self.pointer.children:
            self.pointer.children[c] = TrieNode()
        self.pointer = self.pointer.children[c]
        minHeap = []
        for sent in self.map:
            freq = self.map[sent]
            currObj = CountMap(sent, freq)
            if len(minHeap) == 3:
                minObj = heappop(minHeap)
                if currObj > minObj:
                    heappush(minHeap, currObj)
                else:
                    heappush(minHeap, minObj)
            else:
                heappush(minHeap, currObj)
            
            # if ''.join(self.inp) in self.map:
            # #if sent.startsWith(self.inp):
            #     heappush(minHeap, sent)
            #     if len(minHeap) > 3:
            #         heappop(minHeap)
        stack = [] 
        while len(minHeap) > 0:
            currObj = heappop(minHeap)
            stack.append(currObj.sentence)
        finalList = []
        while len(stack) > 0:
            finalList.append(stack.pop())
        return finalList
                
# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)