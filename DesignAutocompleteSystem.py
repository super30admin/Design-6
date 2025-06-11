# TC - O(nlogk) - n is no of sentences, k is top k elements
# SC - O(k) for k elements in pq and O(n) for n elements in the hmap

import heapq as hq
class heapNode:
    def __init__(self, key, val):
        self.key = key
        self.val = val

    def __lt__(self, other):
        if self.val == other.val:
            return self.key > other.key
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
            self.sb = []
            return []
        self.sb.append(c)

        pq = []
        search = ''.join(self.sb)
        for key in self.hmap.keys():
            if key.startswith(search):
                hq.heappush(pq, heapNode(key, self.hmap[key]))
                if len(pq) > 3:
                    hq.heappop(pq)
        result = []
        while pq:
            result.insert(0,hq.heappop(pq).key)
        return result