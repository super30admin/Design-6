'''
Problem:  Design Search Autocomplete System
Time Complexity: O(1) for input function, since there are constant number of elements which are sorted
Space Complexity: O(n*l), for trie and hashmap
Did this code successfully run on Leetcode: Yes
Any problem you faced while coding this: No
Your code here along with comments explaining your approach:
        used trie to store all sentences
        at each nod, stored top 3 sentnces that starts with prefix
        when user ask for data, we just return the top 3 senetence array directly
'''

class TrieNode:
    def __init__(self):
        self.children = [None] * 256
        self.hot = []

class AutocompleteSystem:

    def insert(self, word):
        curr = self.root
        for c in word:
            idx = ord(c) - ord(' ')
            if curr.children[idx] == None:
                curr.children[idx] = TrieNode()
            curr = curr.children[idx]

            if word not in curr.hot:
                curr.hot.append(word)
            
            curr.hot.sort(key=lambda word: (-self.map[word], word))
            if len(curr.hot)>3:
                curr.hot.pop()
            

    def search(self, word):
        curr = self.root
        for c in word:
            idx = ord(c) - ord(' ')
            if curr.children[idx] == None:
                return []
            curr = curr.children[idx]
        return curr.hot

    def __init__(self, sentences: List[str], times: List[int]):
        self.map = {}
        self.root = TrieNode()
        self.searchTerm = ''

        for i in range(len(sentences)):
            if sentences[i] in self.map:
                self.map[sentences[i]]+=times[i]
            else:
                self.map[sentences[i]]=times[i]
                self.insert(sentences[i])

        
    def input(self, c: str) -> List[str]:
        if c == '#':
            if self.searchTerm in self.map:
                self.map[self.searchTerm]+=1
            else:
                self.map[self.searchTerm]=1 

            self.insert(self.searchTerm)    
            self.searchTerm = ''
            #print(self.map)
            return []
        else:
            self.searchTerm+=c
            #print(self.search(self.searchTerm))
            return self.search(self.searchTerm)


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)