'''
Time Complexity :O(n) --> each key's prefix in the dictionary 
Space Complexity :O(n) ---> for dictionary 
Did this code successfully run on Leetcode : yes
Any problem you faced while coding this :no

Approach --> Dictionary i.e. hashMap to find the prefix and return the recommendList with top 3 elements using 
priority queue

'''

import heapq
from heapq import heappush as push
from heapq import heappop as pop

class pairList:
    def __init__(self,sentence,frequency):
        self.sentence = sentence
        self.freq = frequency
    
    def __lt__(self, other):
        if self.freq == other.freq:
            return self.sentence > other.sentence
        return self.freq < other.freq

class AutocompleteSystem:
    
    def __init__(self, sentences: List[str], times: List[int]):
        
        # initialize a dictionary 
        self.dict = {}
        
        # iterate sentences and times and append it to the dict
        for i in range(0,len(sentences)):
            if sentences[i] not in self.dict:
                self.dict[sentences[i]] = times[i]
        
        # maintain inputString
        self.inputStr = ""
        
    def input(self, c: str) -> List[str]:
        
        # base-case
        if c == "#":
            if self.inputStr != "":
                # add to the dictionary
                if self.inputStr not in self.dict:
                    self.dict[self.inputStr] = 1
                else:
                    self.dict[self.inputStr] = self.dict[self.inputStr] + 1
                # reset the inputStr
                self.inputStr = ""
            # else --- return the blank list
            return []
        
        # logic-case
        
        '''Initlize minHeap and set the count of minHeap'''
        minHeap = []
        count = 0
        
        # 1. add to the inputStr
        self.inputStr = self.inputStr + c
        
        # 2. chk if the prefix exist in the self.dict
        # iterate the self.dict
        for key in self.dict:
            
            if key[0:len(self.inputStr)] == self.inputStr:
                # I have a match
                pair = pairList(key,self.dict[key])
                # add to the minHeap
                push(minHeap,pair)
                # update count
                count += 1
            
            if count > 3:
                # extractMin from the heap
                pop(minHeap)
                # update count
                count -= 1
        '''end of iteration'''
        
        # 3. return the resultList
        minHeap.sort(reverse = True)
        
        resultList = []
        for pair in minHeap:
            resultList.append(pair.sentence)
        
        # print("ResultList is:\t",resultList)
        return resultList
        
        
# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)