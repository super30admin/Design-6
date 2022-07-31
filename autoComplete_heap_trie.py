import heapq as hq
class heapNode: # for custom comparison in heap
    def __init__(self, key, val):
        self.key = key
        self.val = val
    
    def __lt__(self, other):
        # if values are eq, then lexiographically
        # check which is bigger
        if self.val == other.val:
            return self.key > other.key
        # else check which one has smaller value
        return self.val < other.val

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
            curr.list.append(word)
    
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
            if s not in self.hmap: self.insert(s)
            self.hmap[s] = self.hmap.get(s,0) + times[i]
        self.sb = [] # string builder

    def input(self, c: str) -> List[str]:
        if c == "#":
            search = ''.join(self.sb)
            # insert search term into trie, if it is not there
            if search not in self.hmap: self.insert(search)
            self.hmap[search] = self.hmap.get(search,0) + 1
            # reset sb
            self.sb = []
            return []
        self.sb.append(c)
        pq = []
        li = self.search(''.join(self.sb))
        for key in li:
            hq.heappush(pq, heapNode(key, self.hmap[key]))
            if len(pq) > 3:
                hq.heappop(pq)
        result = []
        while pq:
            result.insert(0,hq.heappop(pq).key)
        return result
            
        


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)