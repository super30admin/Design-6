'''
Time Complexity :O(1) --> each key in the tries 
Space Complexity :O(n) ---> for dictionary 
Did this code successfully run on Leetcode : yes
Any problem you faced while coding this :no

Approach --> Tries to find the prefix and return the recommendList with top 3 elements using 
priority queue

'''

import heapq
from heapq import heappush as push
from heapq import heappop as pop

class TrieNode:
    def __init__(self):
        self.trieList = [None]*27 # last character is for space
        self.recommendList = set()

class Trie:
    
    def __init__(self):
        self.root = None
    
    def insert(self,word):
        if self.root == None:
            # create an obj of trieNode and assign it to the root
            objNewNode = TrieNode()
            self.root = objNewNode
        
        # assign currentNode to the trieNode
        currentNode = self.root
        
        # iterate the word
        for char in word:
            # 1. get the index
            index = 0
            if char == " ":
                index = len(currentNode.trieList)-1
            else:
                index = ord(char) - ord('a')
            
            # 2. chk if the position is occupied
            if currentNode.trieList[index] == None:
                # not occupied
                
                # create a trieNode
                objNewNode = TrieNode()
                
                # assign it to the index position
                currentNode.trieList[index] = objNewNode
                
                # update the currentNode
                currentNode = currentNode.trieList[index]
                
            else:
                # update the currentNode rfr
                currentNode = currentNode.trieList[index]
                
            # add word to the recommendation list
            currentNode.recommendList.add(word)
        '''end of for loop'''
    '''end of insertion'''                 
    
    def startsWith(self,prefix):
        
        # base-case
        if self.root == None:
            # empty Trie
            return []
        
        # 1. Initialize the currentPtr
        currentPtr = self.root
        
        # 2. Iterate the prefix
        for char in prefix:
            
            index = 0
            # 2.1. cal index position
            if char == " ":
                index = len(currentPtr.trieList)-1
            else:
                index = ord(char) - ord('a')
            
            # 2.2. chk the index
            if currentPtr.trieList[index] == None:
                # We don't have the char
                return []
            
            else:
                currentPtr = currentPtr.trieList[index]
                continue
        '''out of for-loop'''
        
        # default return
        return currentPtr.recommendList
    
    '''end of prefix search'''
        
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
        
        # insert the word present in the sentence into tries
        # create an obj of class Trie
        self.trie = Trie()
        for word in sentences:
            self.trie.insert(word)
        
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
                
                # add to the trie
                self.trie.insert(self.inputStr)
                
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
        
        # create a search function in trie
        recommendList = self.trie.startsWith(self.inputStr)
        
        # iterate the self.dict
        for word in recommendList:
            pair = pairList(word,self.dict[word])
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