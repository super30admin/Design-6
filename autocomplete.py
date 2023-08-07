import heapq
class CountSentence:
    def __init__(self, time, sentence):
        self.sentence = sentence
        self.time = time
    
    def __lt__(self, other):
        if self.time != other.time:
            return self.time < other.time
        return self.sentence > other.sentence

class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.curr_input = ""
        self.mapping = {sentence: time for sentence, time in zip(sentences, times)}
        

    def input(self, c: str) -> List[str]:
        if c == "#":
            self.mapping[self.curr_input] = self.mapping.get(self.curr_input, 0) + 1
            self.curr_input = ""
            return []
        res = []
        self.curr_input += c
        heap = []
        for sentence, time in self.mapping.items():
            if sentence.startswith(self.curr_input):
                heapq.heappush(heap, CountSentence(time, sentence))
            if len(heap) > 3:
                heapq.heappop(heap)
        while heap:
            countSentence = heapq.heappop(heap)
            res.append(countSentence.sentence)
        return reversed(res)
