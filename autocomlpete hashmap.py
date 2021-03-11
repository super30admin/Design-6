# // Time Complexity : O() Exponential
# // Space Complexity : O(n)
# // Did this code successfully run on Leetcode : Yes
# // Any problem you faced while coding this : No


# // Your code here along with comments explaining your approach
# use the Hashmap to store the sentences and its count
# use priority queue to give most hottest 3 values

# Use pair class to compare the count and sentences for min heapq
class pair:
    def __init__(self, sentence, count):
        self.sentence = sentence
        self.count = count
    def __lt__(self, other):
        # if count is same we use sentences
        if self.count == other.count:
            return self.sentence > other.sentence
        return self.count < other.count
    
class AutocompleteSystem:
        
    def __init__(self, sentences: List[str], times: List[int]):
        self.hm = {}
        self.inString = ""
        # put all sentences into the hashmap
        for i in range(len(sentences)):
            self.hm[sentences[i]] = times[i]
        
        

    def input(self, c: str) -> List[str]:
        # if input is # we add string to the hashmap and reset string to  ""
        if c == "#":
            self.hm[self.inString] = self.hm.get(self.inString, 0) + 1
            # print(self.hm)
            self.inString = ""
            return []
        self.inString += c
            
        heap = []
        # for each sentence in hashmap we look if it starts with given string.
        for i in self.hm:
            if i.startswith(self.inString):
                # if yes, we create pair of sentence and count  and put in heapq
                p = pair(i,self.hm[i])
                heapq.heappush(heap, p)
                if len(heap) > 3:
                    heapq.heappop(heap)
        res = []
        # return list from heapq
        while heap:
            res.append(heapq.heappop(heap).sentence)
        return reversed(res)

# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)