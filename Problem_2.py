# Doesn't run on Leetcode
      # I am not sure how to get string in lexicological order

class AutocompleteSystem:
    import heapq
    def __init__(self, sentences: List[str], times: List[int]):
        self.storage = {}
        self.search = ""
        for i in range(len(sentences)):
            self.storage[sentences[i]] = times[i]

    def input(self, c: str) -> List[str]:
        if c == '#':
            if self.search not in self.storage:
                self.storage[self.search] = 1
            else:
                self.storage[self.search] += 1
            self.search = ""
            return []
        self.search += c
        queue = []
        result = []
        for i in self.storage:
            if i.startswith(self.search):
                if len(queue) < 3:
                    heapq.heappush(queue, (i, self.storage[i]))
                else:
                    heapq.heappushpop(queue, (i, self.storage[i]))
        print(queue)
        while queue and len(result) < 3:
            temp = heapq._heappop_max(queue)
            result.append(temp[0])
        return result
