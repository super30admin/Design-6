from collections import defaultdict, deque
import heapq
from typing import List


class AutocompleteSystem:
    class Pair:
        def __init__(self, sentence, count):
            self.sentence = sentence
            self.count = count

        def __lt__(self, other):
            if self.count == other.count:
                return self.sentence > other.sentence
            return self.count < other.count

    def __init__(self, sentences: List[str], times: List[int]):
        self.dic = defaultdict(int)
        for i in range(len(sentences)):
            self.dic[sentences[i]] += times[i]
        self.search = ''

    def input(self, c: str) -> List[str]:
        pq = []
        if c == '#':
            self.dic[self.search] += 1
            self.search = ''
            return pq
        self.search += c
        for k, v in self.dic.items():
            if k.startswith(self.search):
                heapq.heappush(pq, self.Pair(k, v))
            if len(pq) > 3:
                heapq.heappop(pq)
        result = deque()
        while pq:
            count_sentence = heapq.heappop(pq)
            result.insert(0, count_sentence.sentence)
        return result
