# Time complexity : O(n log k) --> k = 3, n = strings in hashmap
# Space complexity : O(n) --> frequency map
# Brute force solution
class TrieNode:
    def __init__(self,):
        self.children = [None for _ in range(256)]
        self.strList = []
        
        
        
class AutocompleteSystem:
    # custom class for Heap
    class Distance:
        # passing the string and the Hashmap to the function
        def __init__(self, string, strs):
            self.string = string
            self.strs = strs
        # compare the two strings
        def __lt__(self, other):
            # if frequency is same, return the lexo string
            if self.strs[self.string] == self.strs[other.string]:
                return self.string > other.string
            # return the string with higher freq
            return self.strs[self.string] < self.strs[other.string]
        def __gt__(self, other):
            # if frequency is same, return the lexo string
            if self.strs[self.string] == self.strs[other.string]:
                return self.string < other.string
            # return the string with higher freq
            return self.strs[self.string] > self.strs[other.string]
    def insert(self, sentence):
            curr = self.root
            for i in range(len(sentence)):
                ch = sentence[i]
                if curr.children[ord(ch) - ord(' ')] == None:
                    curr.children[ord(ch) - ord(' ')] = TrieNode()
                curr = curr.children[ord(ch) - ord(' ')]
                curr.strList.append(sentence)
        
    def search(self, prefix):
        curr = self.root
        for i in range(len(prefix)):
            ch = prefix[i]
            if curr.children[ord(ch) - ord(' ')] == None:
                return []
            curr = curr.children[ord(ch) - ord(' ')]
        return curr.strList
        
    def __init__(self, sentences: List[str], times: List[int]):
        self.sentences = sentences
        self.times = times
        self.freq = {}
        self.input_str = ''
        self.root = TrieNode()
        
        # building Hashmap with the frequency
        for i in range(len(self.sentences)):
            if self.sentences[i] in self.freq:
                self.freq[self.sentences[i]] = self.freq[self.sentences[i]] + times[i]
            else:
                self.freq[self.sentences[i]] = times[i]
                self.insert(self.sentences[i])
            

    def input(self, c: str) -> List[str]:
        # if the input is # then we check for the string in Hashmap and increase it's frequency is present
        # else make it 1, and return an empty string
        if c == '#':
            search_str = self.input_str
            if search_str not in self.freq:
                self.insert(search_str)
                self.freq[search_str] = 1
            else:
                self.freq[search_str] += 1
            
            self.input_str = ''
            return []
        self.input_str += c
        minHeap = []
        prefix = self.input_str
        starts_with = self.search(prefix)
        for st in starts_with:
            heapq.heappush(minHeap, self.Distance(st,self.freq))
            if len(minHeap) > 3:
                heapq.heappop(minHeap)    
        res = []
        # remove the elements from Heap and append to the result and return it
        while minHeap:
            res.insert(0,heapq.heappop(minHeap).string)
        
        return res


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)
