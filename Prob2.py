class AutocompleteSystem(object):
    class TrieNode(object):
        def __init__(self):
            self.children = {}
            self.inventory = {}
    
    class _Comparator(tuple):
        def __lt__(self, other):
            ct1, str1 = self
            ct2, str2 = other
            if ct1 == ct2:
                return str1 > str2
            return ct1 < ct2
    
    def insertIntoTrie(self,sentence,count):
        curr = self.root
        for ch in sentence:
            if ch not in curr.children:
                curr.children[ch] = self.TrieNode()
            curr = curr.children.get(ch)
            curr.inventory[sentence] = curr.inventory.get(sentence,0) + count
    
    def search(self,prefix):
        curr = self.root
        for ch in prefix:
            if ch not in curr.children:
                return {}
            curr = curr.children.get(ch)
        return curr.inventory
    
    
    def __init__(self, sentences, times):
        """
        :type sentences: List[str]
        :type times: List[int]
        """
        self.inputStr = []
        self.root = self.TrieNode()
        for idx,sentence in enumerate(sentences):
            self.insertIntoTrie(sentence,times[idx])

    def input(self, c):
        """
        :type c: str
        :rtype: List[str]
        """
        if c == '#':
            self.insertIntoTrie(''.join(self.inputStr),1)
            self.inputStr = []
            return []
        self.inputStr.append(c)
        tempStr = ''.join(self.inputStr)
        smallInv = self.search(tempStr)
        heap = []
        resultLi = []
        for sentence,count in smallInv.items():
            heapq.heappush(heap,self._Comparator((count,sentence)))
            if len(heap) > 3:
                heapq.heappop(heap)
        
        while heap:
            resultLi.insert(0,heapq.heappop(heap)[1])
        return resultLi
        


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)
