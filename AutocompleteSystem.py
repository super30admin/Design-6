#Time complexity:O(N+mlog (3))
#Space complexity:O(n)
#Ran successfully on Leetcode:yes
# here I am iterating over the map which has N sentences stored and I am getting all the sentences which starts with given input. Lets say we have m sentences starting with given query. Now to sort these sentences according to their occurance count and ascii value need Mlog(3) time.

  
class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.data=collections.defaultdict(int)
        for i , item in enumerate(sentences):
            self.data[item]=times[i]
        self.text=''
        self._reset()
    
    def _reset(self):
        self.text=''
        self.cache=self.data
        
        

    def input(self, c: str) -> List[str]:
        if c=='#':
            self.data[self.text]+=1
            self._reset()
            return []
        
        self.text+=c
        heap=[]
        new_cache={}
        for i in self.cache:
            if i.startswith(self.text):
                heapq.heappush(heap, (-self.cache[i], i))#using max heap as min heap
                new_cache[i] = self.cache[i]
        
        self.cache = new_cache
        
        res = []
        while len(res) < 3 and heap:
            res.append(heapq.heappop(heap)[1])
        
        return res
        


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)
