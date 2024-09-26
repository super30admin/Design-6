# // Time Complexity : O(l)
# // Space Complexity : O(1)
# // Did this code successfully run on Leetcode : Yes
# // Any problem you faced while coding this : No


# // Your code here along with comments explaining your approach
# Same as hashmap solution just insted of hashmap use Trie for the storage of sentences
# at each TrieNode we store a hashmap, in which we store the sentences and count with that prefix

class pair:
    def __init__(self, sentence, count):
        self.sentence = sentence
        self.count = count
    def __lt__(self, other):
        if self.count == other.count:
            return self.sentence > other.sentence
        return self.count < other.count
# class trienode will have two hashmaps- childrens and list
class TrieNode:
    def __init__(self):
        self.children = {}
        self.list = {}
        
class AutocompleteSystem:
        
    def __init__(self, sentences: List[str], times: List[int]):
        self.inString = ""
        self.root = TrieNode()
        # We create tries of the sentences 
        for i in range(len(sentences)):
            self.insert(sentences[i], times[i])
    # insert the sentences and it count in trie
    def insert(self, sentences, count):
        cur = self.root
        # each character will have separate node 
        for i in sentences:
            if i not in cur.children:
                cur.children[i] = TrieNode()
            cur = cur.children[i]
            # insert the current sentence into the list of the node
            cur.list[sentences] = cur.list.get(sentences,0) + count
                        
        
    def search(self, prefix):
        cur = self.root
        # if not found then return empty list
        for i in prefix:
            if i not in cur.children:
                return []
            cur = cur.children[i]
        return cur.list
        
        

    def input(self, c: str) -> List[str]:
        if c == "#":
            # self.hm[self.inString] = self.hm.get(self.inString, 0) + 1
            self.insert(self.inString,1)
            self.inString = ""
            return []
        self.inString += c
            
        heap = []
        hm = self.search(self.inString)
        for i in hm:
            # if i.startswith(self.inString):
            p = pair(i,hm[i])
            heapq.heappush(heap, p)
            if len(heap) > 3:
                heapq.heappop(heap)
        res = []
        while heap:
            res.append(heapq.heappop(heap).sentence)
        return reversed(res)

# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)