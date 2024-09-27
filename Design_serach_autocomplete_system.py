// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None


// Your code here along with comments explaining your approach
In this problem we create a trie with all the given sentences and at every letter we maintain the word frequency at each and every letter.
when inputing we check the trie and if the sentence is not present we add it to the trie and make changes in the frequency map accordingly.Then we fetch the frequency map and then we extract the 3 most frequency lexiographically greater sentences and then we send them as output at each input.

# Time complexity --> o(l*n) where n is the number of sentences and l is the length of each sentence

import heapq
from collections import deque
# creating a custom comparator if frequencies of the sentences are of same value we go with higher lexiographic sentence else we go with the sentence which has higher frequency
class lexico:
    def __init__(self, key, value):
        self.key = key
        self.value = value

    def __lt__(self, d2):
        if self.value == d2.value:
            return self.key> d2.key
        else:
            return self.value<d2.value
# creating a trie which store the sentence character and the word with its frequencies

class TrieNode:
    def __init__(self):
        self.isEnd = False
        self.children = dict()
        self.freqmap = dict()


class AutocompleteSystem(object):

    def __init__(self, sentences, times):
        """
        :type sentences: List[str]
        :type times: List[int]
        """
        # self.sentences=sentences
        # self.times=times
        self.str1 = ''
        self.root = TrieNode()
        self.cursor = self.root
        # max length of the heap to be maintained
        self.k = 3
        # inserting all the sentences into the trie
        for i in range(len(sentences)):
            sent = sentences[i]
            freq = times[i]
            self.insert(sent, freq)
# logic for trie insertion
    def insert(self, word, freq):
        root1=self.root
        for i in range(len(word)):
            if word[i] not in root1.children:
                root1.children[word[i]] = TrieNode()
            root1 = root1.children[word[i]]
            root1.freqmap[word] = freq

        root1.isEnd = True

    def input(self, c):
        """
        :type c: str
        :rtype: List[str]
        """
        # if the input is # we have to insert all the previous sentence into the trie else if present we have to increment its frequency
        if c == '#':
            if self.str1 not in self.cursor.freqmap:
                self.insert(self.str1, 1)
            else:
                self.insert(self.str1,self.cursor.freqmap[self.str1]+1)
            self.str1 = ''
            self.cursor = self.root
            return
        
        self.str1 = self.str1 + c
        if c not in self.cursor.children:
            self.cursor.children[c] = TrieNode()
        # storing the frequency map for that character
        freqcursor=self.cursor.children[c].freqmap
        self.cursor = self.cursor.children[c]
        pq = []
        
        # min heap with custom comparator
        for key,value in freqcursor.items():
            val1=lexico(key,value)
            heapq.heappush(pq, val1)
            if len(pq) > self.k:
                heapq.heappop(pq)
        # storing the values based on the frequency order and lexicographic order
        out=deque()
        for i in range(len(pq)):
            ele=heapq.heappop(pq)
            out.appendleft(ele.key)
        return out