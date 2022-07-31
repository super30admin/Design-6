# Approch : Use trie - and at each trie node maintain top k results using sorting based on 
# freq of keys and if they are equal,then lexiographical order of keys
# TC :O(n + l), where n is the size of the input and l is the size of the word
# SC :O(n), where n is the size of the input

class TrieNode:
        def __init__(self):
            self.children = [None]*256 # same as regular Trie implementation
            self.list = [] # To store list of all words/sentences starting with current prefix
    

class AutocompleteSystem:

    def insert(self, word):
        curr = self.root
        for i in range(len(word)):
            c = word[i]
            idx = ord(c)-ord(' ')
            if curr.children[idx] == None:
                curr.children[idx] = TrieNode()
            curr = curr.children[idx]
            if word not in curr.list:
                curr.list.append(word)
            # use lambda function to sort current list
            # first it will check hmap[x] and use `-1` to reverse the effect
            # meaning key x with highest value will be prioritized first
            # if both are equal, then compare x - meaning string comparison, and keep
            # the lexiographically smaller key
            curr.list.sort(key = lambda x:(-1 * self.hmap[x], x))  # we are sorting only 4 elements, so its negligible
            while(len(curr.list) > 3):
                 curr.list.pop()
    
    def search(self, search):
        curr = self.root
        for i in range(len(search)):
            c = search[i]
            idx = ord(c)-ord(' ')
            if curr.children[idx] == None:
                # our search term is not in the trie
                return []
            curr = curr.children[idx]
        return curr.list

    def __init__(self, sentences: List[str], times: List[int]):
        self.root = TrieNode()
        self.hmap = {}
        for i,s in enumerate(sentences):
            self.hmap[s] = self.hmap.get(s,0) + times[i]
            self.insert(s)
        self.sb = [] # string builder

    def input(self, c: str) -> List[str]:
        if c == "#":
            search = ''.join(self.sb)
            # insert search term into trie, if it is not there
            self.hmap[search] = self.hmap.get(search,0) + 1
            self.insert(search)
            # reset sb
            self.sb = []
            return []
        self.sb.append(c)
        return self.search(''.join(self.sb))
            
        


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)