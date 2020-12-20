"""
Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2]) 
The system have already tracked down the following sentences and their corresponding times: 
"i love you" : 5 times 
"island" : 3 times 
"ironman" : 2 times 
"i love leetcode" : 2 times 
Now, the user begins another search: 

Operation: input('i') 
Output: ["i love you", "island","i love leetcode"] 
Explanation: 
There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored. 

Operation: input(' ') 
Output: ["i love you","i love leetcode"] 
Explanation: 
There are only two sentences that have prefix "i ". 

Operation: input('a') 
Output: [] 
Explanation: 
There are no sentences that have prefix "i a". 

Operation: input('#') 
Output: [] 
Explanation: 
The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.
"""

##Time Complexity : O(l*n + mlog3) where l is the average length of each sentence and n is the number of given sentences, and m is the average length of the given input string
#Space Complexity : O(l*n) where l is the average length of each sentence and n is the number of given sentences


import heapq
class Pair:
    def __init__(self, sentence, count):
        self.sentence = sentence
        self.count = count
        
        
class TrieNode:
    def __init__(self):
        self.hashmap = {}
        self.children = {}
        
        
class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.root = TrieNode()
        self.input1=""
        for i in range(len(sentences)):
            self.insert(sentences[i], times[i])
         #comparisons based on the frequency and if frequency is same then lexographically compare
        Pair.__lt__ = lambda self, next: self.sentence > next.sentence if self.count == next.count else self.count < next.count
        
    
    def insert(self, sentence, times):
        curr = self.root
        for ch in sentence:
            if ch not in curr.children:
                curr.children[ch] = TrieNode()
            curr = curr.children[ch]
            curr.hashmap[sentence] = curr.hashmap.get(sentence, 0) + times
    
    def search(self, string):
        curr = self.root
        for ch in string:
            if ch not in curr.children:
                return {}
            curr = curr.children[ch]

        return curr.hashmap
    
    def input(self, c: str) -> List[str]:
        if(c=="#"):
            self.insert(self.input1, 1)
            self.input1=""
            return []
        else:
            self.input1+=c
            pq=[]
            sentmap = self.search(self.input1)
            #get top three highest frequency sentences for input
            for sent, count in sentmap.items():
                heapq.heappush(pq, Pair(sent, count))
                if(len(pq)>3):
                    heapq.heappop(pq)
            result=[]
            while(pq):
                pair = heapq.heappop(pq)
                result.insert(0,pair.sentence)    
            return result        

        
obj = AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
c='i'
param_1 = obj.input(c)
print(param_1)