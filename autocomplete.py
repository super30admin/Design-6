#Time Complexity: O(n)
#Space Complexity: O(n)
import heapq as hq

class Helper:
    
    def __init__(self,string,val):
        self.string = string
        self.val  =  val
        
    def __lt__(self,other):
        if self.val == other.val:
            return self.string > other.string
        return self.val < other.val
    

class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.dic = {}
        self.search = ""
        for i in range(len(sentences)):
            self.dic[sentences[i]] = times[i]
        

    def input(self, c: str) -> List[str]:
        if c == "#":
            if self.search != "":
                if self.search not in self.dic:
                    self.dic[self.search] = 0
                self.dic[self.search] += 1
                
            else:
                return []
            self.search = ''
            return []
        else:
            self.search += c
        heap = []
        for keys in self.dic:
            if keys[:len(self.search)] == self.search:
                obj = Helper(keys,self.dic[keys])
                hq.heappush(heap,obj)
                if len(heap)>3:
                    hq.heappop(heap)
        res = []
        while len(heap)!=0:
            res.insert(0,hq.heappop(heap).string)
        return res


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)