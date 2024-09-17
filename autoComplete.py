from typing import List
import  collections, heapq

class trieNode:
    def __init__(self):
        self.children = collections.defaultdict( lambda  : trieNode)
        self.map = collections.defaultdict(int)
class Pair:
    def __init__(self, sentence, count):
        self.sentence = sentence
        self.count = count

    def __lt__(self, other):
        if self.count == other.count:
            return self.sentence > other.sentence
        return self.count < other.count

class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.root = trieNode()
        self.current = self.root
        self.string = ''
        for id in range(len(sentences)):
            self.insert(sentences[id], times[id])
    def insert(self, sentences: str, times: int) -> None:
        curr = self.current
        for char in sentences:
            if char not in curr.children.keys():
                curr.children[char] = trieNode()
            curr = curr.children[char]
            if sentences in curr.map.keys():
                curr.map[sentences] += times
            else:
                curr.map[sentences] = times

    def search(self, sentences):
        curr = self.current
        for character in sentences:
            if character not in curr.children.keys():
                return {}
            curr = curr.children[character]
        return curr.map
    def getresult(self, map1):
        heap =[]
        for key in map1.keys():
            count = map1[key]
            p = Pair(key, count)
            heapq.heappush(heap,p)
            if heap.__len__()> 3:
                heapq.heappop(heap)
        stack = []
        while heap.__len__() >0:
            stack.append(heapq.heappop(heap).sentence)
        return  stack[::-1]


    def input(self, c: str) -> List[str]:
        if c =='#':
            self.insert(self.string, 1)
            self.string = ''
            return  []
        else:
            self.string += c
            countmap = self.search(self.string)
            result = self.getresult(countmap)
            return result
