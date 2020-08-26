"""
// Time Complexity : O(len(database)*len(searchword))
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Your code here along with comments explaining your approach
Algorithm explanation
Given below
"""

import heapq
import queue as q
class AutocompleteSystem:
    """
    1. Construct the database of sentences and times
    2. Track the current input by adding the char unless #(enter)
    4. Input cases
    - if enter is pressed, update the word in the database with new or increment count
    - Else
        - Add the (sentence,-time_count) to the heap(maxheap) for all sentence matching the input_char
        - Pop the top3 hot sentences based on the time_count and add the sentence to the result
    """
    
    def __init__(self, sentences: List[str], times: List[int]):
        #init the database
        self.database = {}
        for s,t in zip(sentences,times):
            self.database[s] = t
        
        #self.sentence_q = q.PriorityQueue()
        self.input_tracker = ""

    def input(self, c: str) -> List[str]:
        #print(self.database)
        self.sentence_q = [] #need to setup heap for each search call
        if c == '#':
            #enter is pressed, so just update the database
            self.database[self.input_tracker] = self.database.get(self.input_tracker,0)+1
            self.input_tracker = ""
            return []
        else:
            self.input_tracker+=c
            for s in self.database.keys():
                if s.startswith(self.input_tracker):
                    heapq.heappush(self.sentence_q,(-self.database[s],s))
            
            result = []
            
            #pop the top 3 hot sentences from the queue
            print(self.sentence_q)
            while self.sentence_q and len(result) < 3:
                _,sent = heappop(self.sentence_q)
                print("sent",sent)
                result.append(sent)
            return result

# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)