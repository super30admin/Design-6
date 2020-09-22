'''
Solution:
1.  The main idea is to use a HashMap for every TrieNode so that we can avoid Depth First Search
    while taking top K sentences at every TrieNode.
2.  In order to extract top K (= 3) sentences for a particular subquery, we use a Heap using
    a custom comparator.
3.  The rest of the design is the same as a basic Trie data structure

Time Complexity:    O(N + MlogK) where 
                    -   N is the length of the subquery or substring
                    -   M is the number of sentences starting with given subquery
                    -   K is the size of the Heap
Space Complexity:   O(T * M) where
                    -   T is the size of the Trie Data structure (number of Trie Nodes)
                    -   M is the average length of frequency HashMap for each Trie Node

--- Passed all testcases successfully on leetcode.
'''


from heapq import heappush as insert
from heapq import heappop as remove

class TrieNode:
    
    def __init__(self):
        
        #   maintaing what all next chars are possible with given subqueries
        self.childrenMap = {}
        #   if end of the word => make this True
        self.isWord = False
        #   maintaing the frequency of sentences starting with given subquery
        self.frequencyMap = {}
        
class FrequencyMap:
    
    #   class for sentence: frequency pair for the purpose of pushing into the Heap
    def __init__(self, sentence, freq):
        self.sentence = sentence
        self.freq = freq
    
    #   custom less than, greater than and equal to operators    
    def __lt__(self, other):
        if self.freq == other.freq:
            return self.sentence > other.sentence
        return self.freq < other.freq
    
    def __gt__(self, other):
        if self.freq == other.freq:
            return self.sentence < other.sentence
        return self.freq > other.freq
    
    def __eq__(self, other):
        return (self.freq == other.freq) and (self.sentence == other.sentence)

class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        
        #   initializations     
        self.root = TrieNode()                          #   root Node 
        self.prefix = []                                #   list of chars seen so far
        self.k = 3                                      #   for top K sentences
        self.persistentCursor = self.root               #   for advancing to current Trie TrieNode
        
        #   add all sentences and their frequencies to the root Trie Node
        for i in range(len(sentences)):
            self.__record(sentences[i], times[i])
            
    def __record(self, sentence: str, frequency: int) -> None:
        
        #   initiaize cursor
        cursor = self.root
        
        #   for each character => add it to the Trie Node
        for char in sentence:
            
            #   if not present => create a Trie Node
            if (char not in cursor.childrenMap):
                cursor.childrenMap[char] = TrieNode()
            
            #   move the cursor to Trie Node containing next char
            cursor = cursor.childrenMap[char]
            
            #   update frequency Maps of each Trie Node
            if (sentence in cursor.frequencyMap):
                cursor.frequencyMap[sentence] += 1
            else:
                cursor.frequencyMap[sentence] = frequency
        
        #   mark the sentence break  
        cursor.isWord = True
    
    def __getTopK(self, frequencyMap):
        
        #   initialize Heap
        minHeap = []

        #   for each sentence, maintain top K sentences seen so far
        for sent in frequencyMap:
            frequency = frequencyMap[sent]
            currentObject = FrequencyMap(sent, frequency)
            
            if (len(minHeap) == self.k):
                minObject = remove(minHeap)
                
                if (currentObject > minObject):
                    insert(minHeap, currentObject)
                else:
                    insert(minHeap, minObject)
            
            else:
                insert(minHeap, currentObject)
            
        
        #   put the remaining top K sentences in a stack for extracting them in descending order 
        stack = []
        while (len(minHeap) > 0):
            currentObject = remove(minHeap)
            stack.append(currentObject.sentence)
        
        #   add to the final list of sentences in descending order
        finalList = []
        while (len(stack) > 0):
            finalList.append(stack.pop())
         
        #   return the top K sentences   
        return finalList

    def input(self, c: str) -> List[str]:
        
        #   if end char
        if (c == '#'):
            self.__record(''.join(self.prefix), 1)      #   record the frequency Map and mark as a word
            self.prefix = []                            #   reset the prefix list
            self.persistentCursor = self.root           #   reset the cursor
            return []                                   #   return empty list
    
        #   else - append to the list of prefix
        self.prefix.append(c)   
        
        #   if Trie Node containing char in the sequence is not present => add it and advance
        if (c not in self.persistentCursor.childrenMap):
            self.persistentCursor.childrenMap[c] = TrieNode()
        self.persistentCursor = self.persistentCursor.childrenMap[c]
        
        #   get top K sentences with the given sequence or subquery or substring
        finalList = self.__getTopK(self.persistentCursor.frequencyMap)
        
        #   return that list
        return finalList            


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)