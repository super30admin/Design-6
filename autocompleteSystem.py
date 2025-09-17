class AutocompleteSystem:
    class TrieNode:
        def __init__(self):
            self.data=set()
            self.children={}
            self.isEnd=False

    def __init__(self, sentences: List[str], times: List[int]):
        self.head=self.TrieNode()
        self.inp=""
        self.db={}
        for sentence, time in zip(sentences, times):
            self.db[sentence]=time
            curr=self.head
            for char in sentence:
                if char not in curr.children:
                    curr.children[char]=self.TrieNode()
                
                curr=curr.children[char]
                curr.data.add(sentence)
            curr.isEnd=True
    
    def store(self, s):
        self.db[s] = self.db.get(s, 0) + 1
        curr=self.head
        for char in s:
            if char not in curr.children:
                curr.children[char]=self.TrieNode()
            curr=curr.children[char]
            curr.data.add(s)
    
        curr.isEnd=True
        
    def input(self, c: str) -> List[str]:
        res=[]
        if c != '#':
            self.inp+=c
            curr=self.head

            for char in self.inp:
                if char not in curr.children:
                    return []
                curr=curr.children[char]

            heap=[]
            for sentence in curr.data:
                heapq.heappush(heap, (-self.db[sentence], sentence))
            
            
            for _ in range(min(3, len(heap))):
                num, sen = heapq.heappop(heap)
                res.append(sen)
        else:
            self.store(self.inp)
            self.inp=""
        
        return res