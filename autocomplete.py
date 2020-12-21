#As taught in class using priority queue to solve this problem
#Problems faced: Getting error in heapq implementation. Comparator not working. Need help to solve this issue
import heapq
class Pair:
    def __init__(self,sentence,count):
        self.sentence = sentence
        self.count = count
    
    def __lt__(self,pair1):
        if self.count == pair1.count:
            return self.sentence > pair1.sentence
        else:
            return self.count > pair1.count

    def pushInHeap(self,wordMap,inp):
        pq = []
        for key in wordMap:
            if key.startswith(inp):
                heapq.heappush(pq,Pair(key,wordMap[key]))
                if (len(pq) > 3):
                    heapq.heappop(pq)
        li = list()
        while(len(pq)!=0):
            li.insert(0,heapq.heappop(pq))
        return li

        
class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.wordMap = dict()
        self.inp = ""
        for i in range(len(sentences)):
            sentences[i] = times[i]

        
    def input(self, c: str):
        if c == "#":
            if self.inp in self.wordMap:
                self.wordMap[self.inp] = self.wordMap[self.inp] + 1
            else:
                self.wordMap[self.inp] = 0
            self.inp = ""
            return []
        
        self.inp = self.inp + c
        p = Pair("",0)
        result = p.pushInHeap(self.wordMap,self.inp)
        return result


    
        