# // Time Complexity : Exponential
# // Space Complexity : O(1)
# // Did this code successfully run on Leetcode : Yes
# // Any problem you faced while coding this : No
#
#
# // Your code here along with comments explaining your approach

class pair:
    def __init__(self, sentence, count):
        self.sentence = sentence
        self.count = count

    def __lt__(self, other):
        # if count is same we use sentences
        if self.count == other.count:
            return self.sentence > other.sentence
        return self.count < other.count


class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.hashmap = {}
        self.in_string = ""
        # put all sentences into the hashmap
        for i in range(len(sentences)):
            self.hashmap[sentences[i]] = times[i]

    def input(self, c: str) -> List[str]:
        # if input is # we add string to the hashmap and reset string to  ""
        if c == "#":
            self.hashmap[self.in_string] = self.hashmap.get(self.in_string, 0) + 1
            self.in_string = ""
            return []
        self.in_string += c

        heap = []
        # for each sentence in hashmap we look if it starts with given string.
        for i in self.hashmap:
            if i.startswith(self.in_string):
                # if yes, we create pair of sentence and count  and put in heapq
                p = pair(i, self.hashmap[i])
                heapq.heappush(heap, p)
                if len(heap) > 3:
                    heapq.heappop(heap)
        res = []
        # return list from heapq
        while heap:
            res.append(heapq.heappop(heap).sentence)
        return reversed(res)

# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)