# 642. Design Search Autocomplete System
import heapq
from typing import List, Tuple

class AutocompleteSystem:
    def __init__(self, sentences: List[str], times: List[int]):
        # A dictionary to map sentences to their frequencies
        self.sentence_map = {}
        # Current search string being typed
        self.current_query = ""

        # Initialize the map with the given sentences and their times
        for i in range(len(sentences)):
            self.sentence_map[sentences[i]] = times[i]
    
    def input(self, c: str) -> List[str]:
        # If the end of the input is indicated with '#', update the sentence map
        if c == "#":
            if self.current_query:
                self.sentence_map[self.current_query] = self.sentence_map.get(self.current_query, 0) + 1
            self.current_query = ""  # Reset the current query
            return []
        
        # Add the character to the current query
        self.current_query += c
        
        # Use a max-heap to store sentences that match the current query
        max_heap: List[Tuple[int, str]] = []
        
        for sentence, freq in self.sentence_map.items():
            if sentence.startswith(self.current_query):
                # Push a tuple with (-frequency, sentence) to simulate max-heap
                # Sentence is used as a secondary sorting criterion for lexicographical order
                heapq.heappush(max_heap, (-freq, sentence))
        
        # Extract up to 3 elements from the heap
        result = []
        for _ in range(min(3, len(max_heap))):
            freq, sentence = heapq.heappop(max_heap)
            result.append(sentence)
        
        return result
