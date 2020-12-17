#Time Complexity : O(l*n + mlog3) where l is the average length of each sentence and n is the number of given sentences, and m is the average length of the given input substrings
#Space Complexity : O(l*n) where l is the average length of each sentence and n is the number of given sentences
#Did this code successfully run on Leetcode : Yes


class Pair:
    def __init__(self, sentence, count):
        self.sentence = sentence
        self.count = count

class TrieNode:
    def __init__(self):
        self.hashmap = {}
        self.children = {}

class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.root = TrieNode()
        self.currSentence = ""
        #create a trie for all of the given sentences
        for i, sentence in enumerate(sentences):
            self.insert(sentence, times[i])
        #custom less than function for heap to make comparisons according to the frequency and if frequency is same then lexographically
        Pair.__lt__ = lambda self, next: self.sentence > next.sentence if self.count == next.count else self.count < next.count

    def insert(self, sentence, times):
        curr = self.root
        for char in sentence:
            if char not in curr.children:
                curr.children[char] = TrieNode()
            curr = curr.children[char]
            #to the prefix, add the entire sentence and count in it's hashmap
            curr.hashmap[sentence] = curr.hashmap.get(sentence, 0) + times

    def search(self, string):
        curr = self.root
        for char in string:
            if char not in curr.children:
                return {}
            curr = curr.children[char]

        return curr.hashmap

    def input(self, c: str) -> List[str]:
        #if the search input has been completed then add it to the trie
        if c == "#":
            self.insert(self.currSentence, 1)
            self.currSentence = ""
            return []
        else:
            self.currSentence += c
            counts = self.search(self.currSentence)
            heap = []
            #get top three highest frequency sentences for input
            for sent, count in counts.items():
                heapq.heappush(heap, Pair(sent, count))
                if len(heap) > 3:
                    heapq.heappop(heap)

            result = []
            while heap:
                pair = heapq.heappop(heap)
                result.append(pair.sentence)

            return reversed(result)

# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)
