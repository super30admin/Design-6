"""
Leetcode: https://leetcode.com/problems/design-search-autocomplete-system/
Approach:
    1. Construct the database of sentences and times
    2. Track the current input by adding the char unless #(enter)
    4. Input cases
    - if enter is pressed, update the word in the database with new or increment count
    - Else
        - Add the (sentence,-time_count) to the heap(maxheap) for all sentence matching the input_char
        - Pop the top3 hot sentences based on the time_count and add the sentence to the result
Time complexity: O(N + m log (3)), N is the number of sentences, m is the number of sentences starting with given query.
Space complexity: O(n)
"""

import heapq

class AutocompleteSystem:
    def __init__(self, sentences: List[str], times: List[int]):
        # init the database
        self.database = {}
        for s, t in zip(sentences, times):
            self.database[s] = t

        self.input_tracker = ""

    def input(self, c: str) -> List[str]:
        self.sentence_q = []  # need to setup heap for each search call
        if c == '#':
            # enter is pressed, so just update the database
            self.database[self.input_tracker] = self.database.get(self.input_tracker, 0) + 1
            self.input_tracker = ""
            return []
        else:
            self.input_tracker += c
            for s in self.database.keys():
                if s.startswith(self.input_tracker):
                    heapq.heappush(self.sentence_q, (-self.database[s], s))

            result = []

            # pop the top 3 hot sentences from the queue
            while self.sentence_q and len(result) < 3:
                _, sent = heapq.heappop(self.sentence_q)
                result.append(sent)
            return result

# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)