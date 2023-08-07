class AutocompleteSystem(object):

    def __init__(self, sentences, times):
        """
        Initialize the AutocompleteSystem with given sentences and times.

        Time Complexity: O(n), where n is the number of sentences.
        Space Complexity: O(n), for storing sentences and times.

        :type sentences: List[str]
        :type times: List[int]
        """
        self.timesMap = {}  # Store sentences and their corresponding times

        for i in range(0, len(sentences)):
            sentence = sentences[i]
            time = times[i]
            if sentence in self.timesMap:
                self.timesMap[sentence] += time
            else:
                self.timesMap[sentence] = time

        self.currentInput = ""  # Store the current input being typed

    def input(self, c):
        """
        Process the input character and return autocomplete suggestions.

        Time Complexity: O(n * m * log m), where n is the number of sentences and m is the average length of sentences.
        Space Complexity: O(n), for storing suggestions.
        """
        if c == '#':
            # If the current input is complete, add it to the timesMap with a time of 1
            if self.currentInput:
                if self.currentInput in self.timesMap:
                    self.timesMap[self.currentInput] += 1
                else:
                    self.timesMap[self.currentInput] = 1
                self.currentInput = ""  # Reset currentInput for the next input
            return []

        # Update the current input being typed
        self.currentInput += c

        # Generate a list of sentences that match the current input
        suggestions = []
        for sentence in self.timesMap:
            if sentence.startswith(self.currentInput):
                suggestions.append(sentence)

        # Sort suggestions based on frequency and lexicographical order
        suggestions.sort(key=lambda x: (-self.timesMap[x], x))

        # Return the top 3 suggestions or all suggestions if there are fewer than 3
        return suggestions[:3]
