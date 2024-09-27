"""
Description: Design search autocomplete system (# 642)

Time Complexity: O(n + plog3), where p = number of words starting with a character on average
Space Complexity: O(np)

Approach:

Construct a data structure 
- Use a Trie data structure, start with default key as a Trie node
- To refer the above dictionary, we will require a count map
- Using above data structure, construct all sentences and count from given input

Input function:
- if the input has "#", use the trie data structure and add the current sentence (from preceeding items in input for c), return an empty list here (as instructed)
- else, update current node to children of c and update the sentence. Add empty lists for results and records (for sentences)
- the records of count and sentence can be pushed to a heap data structure by making use of count dictionary (defined in Trie data structure)
- pop top 3 items from the heap and add to the results list if available
"""

from collections import defaultdict
from heapq import heappush, heappop

class Trie:
    
    def __init__(self):
        
        self.children = defaultdict(Trie)
        
        # with each trie node, count is also required for each sentence
        self.count = defaultdict(int)

class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        
        self.root = Trie()
        self.curr = self.root
        self.sentence = ""
        
        # convert sentences to trie structure (defined above)
        for sentence, count in zip(sentences, times):
            node = self.root
            for char in sentence:
                node = node.children[char]
                node.count[sentence] += count
    
    def input(self, c: str) -> List[str]:
        
        if c == "#":
            node = self.root
            for char in self.sentence:
                node = node.children[char]
                node.count[self.sentence] += 1
            
            # once a sentence is over, reset the current node to the root, and empty sentence
            self.curr = self.root
            self.sentence = ""
            return []
        else:
            # valid input (how many characters until now)
            self.sentence += c
            
            # go one level down
            self.curr = self.curr.children[c]
            results = []
            records = []
            for sentence, count in self.curr.count.items():
                # heap data structure to get top 3
                heappush(records, (-count, sentence))

            for i in range(3):
                if len(records) > 0:
                    cnt, st = heappop(records)
                    results.append(st)

        return results


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)
