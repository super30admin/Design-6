import heapq
class Node:
    def __init__(self):
        self.child = collections.defaultdict(Node)
        self.freq = 0

class Trie:
    def __init__(self):
        self.root = Node()
        
    def insert(self, sentence, time):
        p = self.root
        for ch in sentence:
            p = p.child[ch]
        p.freq += time    
        
    def search(self, node, path):
        '''
        given a node, return the top 3 sentences starting from that node.
        '''
        hq = []
        def dfs(node, path, hq):
            if not node:
                return
            if node.freq:
                hq.append((-node.freq, path))
            for ch in node.child:
                dfs(node.child[ch], path + ch, hq)
        dfs(node, path, hq)
        res = []
        heapq.heapify(hq)
        for _ in range(3):
            if hq:
                res.append(heapq.heappop(hq)[1])
        return res
    
class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.trie = Trie()
        for sentence, time in zip(sentences, times):
            self.trie.insert(sentence, time)
        self.path = '' # current active input
        self.p = self.trie.root # current node
        
    def input(self, c: str) -> List[str]:
        if c == '#':
            if self.path:
                self.trie.insert(self.path, 1)
                self.path = ''
                self.p = self.trie.root
            return []
        else:
            if not self.p or c not in self.p.child:
                self.p = None
                self.path += c
                return []
            else:
                self.p = self.p.child[c]
                self.path += c
                return self.trie.search(self.p, self.path)