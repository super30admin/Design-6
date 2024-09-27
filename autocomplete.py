# Time Complexity : O(N + mlogm)
# Space Complexity : O(n)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
'''
1. Store sentences ad thier occurence time in hashmap
2. To sort these sentences according to their ocuurence I have use max heap
3 Maximum priority is given to count, in case count is same priority is given lexicographically smaller sentence
'''

import heapq
from collections import defaultdict

class AutocompleteSystem:
    
    map_ = defaultdict()
    input_ = ""
    
    def __init__(self, sentences, times):
        
        for i in range(len(sentences)):
            self.map_[sentences[i]] += times[i]
            
    def input(self, c):
        if c == '#':
            self.map_[self.input_] += 1
            
            self.input_ = ""
            
            return []
        
        self.input_ += c
        
        heap_list = []
        
    for key in self.map_:
        if s.startswith(self.input_):
            heapq.heappush((key, -self.map_[key]), heap_list)
            
            if len(heap_list) > 3:
                heapq.heappop(heap_list)

    
    result = []
    
    while len(heap_list) >0:
        p = heapq.heappop()
        result.insert(0, p[0])
        
    return result
        