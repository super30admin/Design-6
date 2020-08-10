# Time Complexity : O(n)
# Space Complexity :O(256) for root nodes + O(n) for number of sentences for hashmap
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
# Your code here along with comments explaining your approach
import heapq
# ******************************* METHOD 1 **************************************************************
# Using Dictionaries.
# class Pair:
#     def __init__(self, sentence, count):
#         self.sentence = sentence
#         self.count = count

#     def __lt__(self, other):
#         if self.count == other.count:
#             return self.sentence > other.sentence
#         return self.count < other.count

#     def __repr__(self):
#         return '%s' % (self.sentence)

# Method 1) Using hashtable and storing sentences and its count.
# Add the sentence, count pair to the priority queue and define a custom gt than operation for sorting.
# In our min heap we will have lexiographically the minimum sentence and count at the top. If the

# class AutoComplete:
#     map_ = None
#     input = None

#     def __init__(self, sentences, times):
#         pass
#         self.map_ = {}
#         self.input = ''
#         for i in range(len(sentences)):
#             if sentences[i] not in self.map_:
#                 self.map_[sentences[i]] = 0
#             self.map_[sentences[i]] += times[i]

#     def check(self, c):
#         result = []
#         if c == '#':
#             if self.input not in self.map_:
#                 self.map_[self.input] = 0
#             self.map_[self.input] += 1
#             return result

#         self.input += c

#         st = []

#         for key in self.map_.keys():

#             if key.startswith(self.input):
#                 heapq.heappush(st, Pair(key, self.map_[key]))

#             if len(st) > 3:
#                 item = heapq.heappop(st)

#         while len(st) != 0:
#             item = heapq.heappop(st).sentence
#             result.insert(0, item)
#         return result

# ******************************* METHOD 2 **************************************************************

import heapq

class Pair:
    def __init__(self, sentence, count):
        self.sentence = sentence
        self.count = count

    def __lt__(self, other):
        if self.count == other.count:
            return self.sentence > other.sentence
        return self.count < other.count

    def __repr__(self):
        return '%s' % (self.sentence)


class TrieNode:
    def __init__(self):
        self.children = {}                  # <Character, TrieNode>
        self.map = {}                       # <String, Integer>

class Trie:
    def __init__(self):
        self.root = TrieNode()
    
    # Time = O(n) where n are no of sentences)
    def insert(self, sentence, times):  
        curr = self.root
        
        for i in range(len(sentence)):
            c = sentence[i]                  # Char
            if c not in curr.children:
                curr.children[c] = TrieNode()
            
            curr = curr.children[c]
            if sentence not in curr.map:
                curr.map[sentence] = 0
            curr.map[sentence] += times
    
    # Time = O(k) where k is length of prefix/input)        
    def search(self, prefix):                 # search the prefix
        curr = self.root 
        
        for i in range(len(prefix)):
            c = prefix[i]
            if c not in curr.children:
                temp = {}
                return temp                 # return empty map 
            curr = curr.children[c]
            
        return curr.map
    
    
class AutoComplete:
    
    input = None
    root = None 
    def __init__(self, sentences, times):
        self.trie = Trie()
        self.input = ''
        for i in range(len(sentences)):
            self.trie.insert(sentences[i], times[i])
    
    # Time = O(nlogn) where n are no of sentences)
    def check(self, c):
        result = []
        if c == '#':
            self.trie.insert(self.input, 1)
            self.input = ""
            return result 
        
        pq = []
        self.input += c 
        count = self.trie.search(self.input)           # count is <String, Integer>
        for key in count.keys():
            heapq.heappush(pq, Pair(key, count[key]))
            if len(pq) > 3:
                heapq.heappop(pq)
        
        while len(pq) != 0:
            result.insert(0, heapq.heappop(pq))
        
        return result 
            
        
if __name__ == "__main__":
    sentences = ['i love you', 'island', 'ironman', 'i love leetcode']
    times = [5, 3, 2, 2]
    s = AutoComplete(sentences, times)

    # test case 1
    print(s.check('i'))

    # test case 2
    print(s.check(' '))

    # test case 3
    print(s.check('a'))

    # test case 4
    print(s.check('#'))
