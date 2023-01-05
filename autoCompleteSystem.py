'''
We can use priority queu to sort the items based on the frequency
Time Complexity --> O(n)
SpaceC omplexity --> O(n) for hashmap
'''
class Sentence:  #this is our priority queue class 
    def __init__(self, sentence, freq):
        self.sentence = sentence
        self.freq = freq
        
    def __lt__(self, other): #this method helps in comparison 
        if self.freq == other.freq:
            return self.sentence > other.sentence
        
        return self.freq < other.freq

class AutocompleteSystem:
            
    def __init__(self, sentences: List[str], times: List[int]):
        self.sentences = sentences
        self.times = times
        self.map = {}
        for i in range(len(self.sentences)):
            self.map[sentences[i]] = self.times[i]
        #self.q =[]
        self.st = ''
        # self.res = []

        

    def input(self, c: str) -> List[str]:
        if c=='#':
            try:
                self.map[self.st]+=1
            except:
                self.map[self.st]=1
            self.st = ''
            return []
        self.st+=c
        h = []
        # print(self.st)
        # print(self.map)
        for k,v in self.map.items():
            if k.startswith(self.st):
                #print('Yes')
                heapq.heappush(h, Sentence(k,v)) #logk but runing n times so O(n)
                if len(h)>3:
                    heapq.heappop(h)
        # val = self.q.get()
        # print(val)
        res = []
        while len(h)!=0:
            val = heapq.heappop(h)
            #print(val)
            res.insert(0,val.sentence)
        return(res)
        





        


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)