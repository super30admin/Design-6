# Approach: Use hashmap and priority queue - heap
# TC - O(nlogk) - n is no of sentences, k is top k elements
# SC - O(k) for k elements in pq and O(n) for n elements in the hmap

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
    
class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.hmap = {}
        for i,s in enumerate(sentences):
            self.hmap[s] = self.hmap.get(s,0) + times[i]
        self.sb = [] # string builder

    def input(self, c: str) -> List[str]:
        if c == "#":
            search = ''.join(self.sb)
            self.hmap[search] = self.hmap.get(search,0) + 1
            # reset sb
            self.sb = []
            return []
        self.sb.append(c)
        # maintain min heap of (key,occurence)
        # so that every time pq crosses size k we remove min element
        # only keeping max k elements in the pq to return top k results
        pq = []
        search = ''.join(self.sb)
        for key in self.hmap.keys(): # O(n) because we may have very large hashmap of sentences. Could be a billion.
            if key.startswith(search):
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
