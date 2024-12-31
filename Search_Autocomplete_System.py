# Explain your approach briefly:
# The AutocompleteSystem is implemented using a dictionary (hash map) to store sentence frequencies.
# - Each character input builds a prefix.
# - When a prefix is typed, search the dictionary for sentences matching the prefix.
# - Sort the results by frequency (descending) and lexicographical order.
# - Store new sentences or update frequencies when '#' is input.
# 
# Time Complexity:
# - Initialization: O(n * m), where n is the number of sentences and m is the average length.
# - Input: O(p + k log k), where p is the prefix length and k is the number of matching sentences.
# Space Complexity: O(n * m), for storing sentences and frequencies.
# Did this code successfully run on Leetcode: Yes
# Any problem you faced while coding this: No

class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        """
        Initialize the system with sentences and their corresponding frequencies.
        """
        self.trie = defaultdict(int)  # Dictionary to store sentence frequencies
        self.current_input = ""  # To track the ongoing input prefix
        
        # Populate the dictionary with initial data
        for sentence, time in zip(sentences, times):
            self.trie[sentence] += time

    def input(self, c: str) -> List[str]:
        """
        Process the input character and return the top 3 sentences matching the prefix.
        """
        if c == '#':
            # If '#' is input, save the current input as a sentence
            self.trie[self.current_input] += 1  # Add or update the sentence in the dictionary
            self.current_input = ""  # Reset current input for a new query
            return []

        self.current_input += c  # Build the prefix
        prefix = self.current_input

        # Find all sentences matching the prefix
        matches = [(sentence, freq) for sentence, freq in self.trie.items() if sentence.startswith(prefix)]
        
        # Sort matches by frequency (descending) and lexicographical order
        matches.sort(key=lambda x: (-x[1], x[0]))

        # Return the top 3 sentences
        return [sentence for sentence, _ in matches[:3]]

# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)
