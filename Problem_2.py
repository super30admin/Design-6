"""
Problem : 2

Time Complexity : 
Approach 1-4 - O(n)
Approach 5 - O(1)

Space Complexity : O(m) //m=size of trie

Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No

"""


# Design Autocomplete System

# Approach - 1

class AutocompleteSystem(object):

    def __init__(self, sentences, times):
        """
        :type sentences: List[str]
        :type times: List[int]
        """
        self.hmap={}
        self.search=""
        for i in range(len(sentences)):
            sentence=sentences[i]
            time=times[i]
            if sentence not in self.hmap:
                self.hmap[sentence]=0
            self.hmap[sentence]+=time


    def input(self, c):
        """
        :type c: str
        :rtype: List[str]
        """
        if c=="#":
            # whatever is serach term add it in cache
            searchTerm=self.search
            if searchTerm not in self.hmap:
                self.hmap[searchTerm]=0
            self.hmap[searchTerm]+=1
            self.search=""
            return []
        # priority queue data
        pq=[]
        self.search+=c
        searchTerm=self.search
        for sentence in self.hmap.keys():
            if sentence.startswith(searchTerm):
                pq.append(sentence)
            pq.sort(key=lambda x: (-self.hmap[x], x))
            if len(pq) > 3:
                pq.pop()
        return pq


# Approach - 2
# Using Min Heap/Priority Queue

class minheap:
    def __init__(self, freq, sentence):
        self.sentence = sentence
        self.freq = freq

    def __lt__(self, other):
        if self.freq != other.freq:
            return self.freq < other.freq
        return self.sentence > other.sentence

class AutocompleteSystem(object):

    def __init__(self, sentences, times):
        """
        :type sentences: List[str]
        :type times: List[int]
        """
        self.hmap={}
        self.search=""
        for i in range(len(sentences)):
            sentence=sentences[i]
            time=times[i]
            if sentence not in self.hmap:
                self.hmap[sentence]=0
            self.hmap[sentence]+=time

    def input(self, c):
        """
        :type c: str
        :rtype: List[str]
        """
        if c=="#":
            # whatever is serach term add it in cache
            searchTerm=self.search
            if searchTerm not in self.hmap:
                self.hmap[searchTerm]=0
            self.hmap[searchTerm]+=1
            self.search=""
            return []
        # priority queue data
        pq=[]
        self.search+=c
        searchTerm=self.search
        for sentence in self.hmap.keys():
            if sentence.startswith(searchTerm):
                heapq.heappush(pq,minheap(self.hmap[sentence],sentence))
            if len(pq) > 3:
                heapq.heappop(pq)
        res=[]
        while pq:
            s=heapq.heappop(pq)
            res.append(s.sentence)
        return reversed(res)

# Approach - 3
# Trie

class TrieNode(object):
    def __init__(self):
        self.children=[None for _ in range(256)]
        self.startsWith=[]

class minheap(object):
    def __init__(self, freq, sentence):
        self.sentence = sentence
        self.freq = freq

    def __lt__(self, other):
        if self.freq != other.freq:
            return self.freq < other.freq
        return self.sentence > other.sentence

class AutocompleteSystem(object):

    def insert(self,word):
        curr=self.root
        for i in range(len(word)):
            c=word[i]
            if curr.children[ord(c)-ord(' ')]==None:
                curr.children[ord(c)-ord(' ')]=TrieNode()
            curr=curr.children[ord(c)-ord(' ')]
            curr.startsWith.append(word)

    def __init__(self, sentences, times):
        """
        :type sentences: List[str]
        :type times: List[int]
        """
        self.root=TrieNode()
        self.hmap={}
        self.search=""
        for i in range(len(sentences)):
            sentence=sentences[i]
            time=times[i]
            if sentence not in self.hmap:
                self.insert(sentence)
                self.hmap[sentence]=0
            self.hmap[sentence]+=time

    def searchTrie(self,word):
        curr=self.root
        for i in range(len(word)):
            c=word[i]
            if curr.children[ord(c)-ord(' ')]==None:
                return []
            curr=curr.children[ord(c)-ord(' ')]
        return curr.startsWith

    def input(self, c):
        """
        :type c: str
        :rtype: List[str]
        """
        if c=="#":
            # whatever is serach term add it in cache
            searchTerm=self.search
            if searchTerm not in self.hmap:
                self.insert(searchTerm)
                self.hmap[searchTerm]=0
            self.hmap[searchTerm]+=1
            self.search=""
            return []
        # priority queue data
        pq=[]
        self.search+=c
        searchTerm=self.search
        li=self.searchTrie(searchTerm)
        for sentence in li:
            
            heapq.heappush(pq,minheap(self.hmap[sentence],sentence))
            if len(pq) > 3:
                heapq.heappop(pq)
        res=[]
        while pq:
            s=heapq.heappop(pq)
            res.append(s.sentence)
        return reversed(res)


# Approach - 4
# Trie Using Hashmap

class TrieNode(object):
    def __init__(self):
        self.children={}
        self.startsWith=[]


class minheap(object):
    def __init__(self, freq, sentence):
        self.sentence = sentence
        self.freq = freq

    def __lt__(self, other):
        if self.freq != other.freq:
            return self.freq < other.freq
        return self.sentence > other.sentence

class AutocompleteSystem(object):

    def insert(self,word):
        curr=self.root
        for i in range(len(word)):
            c=word[i]
            if c not in curr.children:
                curr.children[c]=TrieNode()
            curr=curr.children[c]
            curr.startsWith.append(word)

    def __init__(self, sentences, times):
        """
        :type sentences: List[str]
        :type times: List[int]
        """
        self.root=TrieNode()
        self.hmap={}
        self.search=""
        for i in range(len(sentences)):
            sentence=sentences[i]
            time=times[i]
            if sentence not in self.hmap:
                self.insert(sentence)
                self.hmap[sentence]=0
            self.hmap[sentence]+=time

    def searchTrie(self,word):
        curr=self.root
        for i in range(len(word)):
            c=word[i]
            if c not in curr.children:
                return []
            curr=curr.children[c]
        return curr.startsWith

    def input(self, c):
        """
        :type c: str
        :rtype: List[str]
        """
        if c=="#":
            # whatever is serach term add it in cache
            searchTerm=self.search
            if searchTerm not in self.hmap:
                self.insert(searchTerm)
                self.hmap[searchTerm]=0
            self.hmap[searchTerm]+=1
            self.search=""
            return []
        # priority queue data
        pq=[]
        self.search+=c
        searchTerm=self.search
        li=self.searchTrie(searchTerm)
        for sentence in li:
            
            heapq.heappush(pq,minheap(self.hmap[sentence],sentence))
            if len(pq) > 3:
                heapq.heappop(pq)
        res=[]
        while pq:
            s=heapq.heappop(pq)
            res.append(s.sentence)
        return reversed(res)


# Approach - 5
# Trie

class TrieNode(object):
    def __init__(self):
        self.children=[None for _ in range(256)]
        self.startsWith=[]

class AutocompleteSystem(object):

    def insert(self,word):
        curr=self.root
        for i in range(len(word)):
            c=word[i]
            if curr.children[ord(c)-ord(' ')]==None:
                curr.children[ord(c)-ord(' ')]=TrieNode()
            curr=curr.children[ord(c)-ord(' ')]

            if word not in curr.startsWith:
                curr.startsWith.append(word)
            curr.startsWith.sort(key=lambda word: (-self.hmap[word], word))
            if len(curr.startsWith)>3:
                curr.startsWith.pop()


    def __init__(self, sentences, times):
        """
        :type sentences: List[str]
        :type times: List[int]
        """
        self.root=TrieNode()
        self.hmap={}
        self.search=""
        for i in range(len(sentences)):
            sentence=sentences[i]
            time=times[i]
            if sentence not in self.hmap:
                self.hmap[sentence]=0
            self.hmap[sentence]+=time
            self.insert(sentence)


    def searchTrie(self,word):
        curr=self.root
        for i in range(len(word)):
            c=word[i]
            if curr.children[ord(c)-ord(' ')]==None:
                return []
            curr=curr.children[ord(c)-ord(' ')]
        return curr.startsWith


    def input(self, c):
        """
        :type c: str
        :rtype: List[str]
        """
        if c=="#":
            # whatever is search term add it in cache
            searchTerm=self.search
            self.search=""
            if searchTerm not in self.hmap:
                self.hmap[searchTerm]=0
            
            self.hmap[searchTerm]+=1
            self.insert(searchTerm)
            return []

        self.search+=c
        searchTerm=self.search

        return self.searchTrie(searchTerm)
