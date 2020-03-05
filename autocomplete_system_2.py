from collections import defaultdict
import heapq
from typing import List


class AutocompleteSystem:
    class Pair:
        def __init__(self, sentence, count):
            self.sentence = sentence
            self.count = count

        def __lt__(self, other):
            if self.count != other.count:
                return self.count < other.count
            return self.sentence > other.sentence

    def __init__(self, sentences: List[str], times: List[int]):
        self.dic = defaultdict(int)
        for i in range(len(sentences)):
            self.dic[sentences[i]] += times[i]
        self.search = ''

    def input(self, c: str) -> List[str]:
        result = []
        if c == '#':
            self.dic[self.search] += 1
            self.search = ''
            return result
        self.search += c
        for k, v in self.dic.items():
            if k.startswith(self.search):
                heapq.heappush(result, self.Pair(k, v))
            if len(result) > 3:
                heapq.heappop(result)
        res = []
        while result:
            count_sentence = heapq.heappop(result)
            res.insert(0, count_sentence.sentence)
        return res
