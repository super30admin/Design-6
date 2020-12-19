# Time Complexity: init : O(n*m) where n is the number of sentences and m is the average length of the sentences
#				   input: O(nm logn) where n is the number of strings and m is the average length of a string.
#						  The m occurs in the complexity since string comparison is O(m)
# Space Complexity: O(n*m) : where n is the number of strings and m is the average length of the string.
# Approach: Implement a Trie and populate the list of strings in the trie.
#			At each trie node, maintain a list of indexes of sentences that have the same prefix and a boolean value for identifying end of sentence.
#			When an input is received, see the list of sentences at that trie node and get the top 3 using sorting.
#			When a "#" input is found see if the inputted sentence is present in the dictionary, if so increase the frequency else create a new string,
#			Iterate the string in the trie and add the string to the same prefix sentence index list at each of the sentence char's trie nodes.
import operator
    
class AutocompleteSystem:

    class Trie:
        def __init__(self):
            self.char_arr = [None] * 27
            self.sentences = []
            self.string_end = False
        
    def __init__(self, sentences: List[str], times: List[int]):
        self.trie_dict = self.Trie()
        self.trie_dict.sentences = list(range(0,len(sentences)))
        self.sentences = sentences
        self.times = times
        self.current_trie = self.trie_dict
        self.input_sentence = []
        for idx,i in enumerate(sentences):
            sent_trie = self.trie_dict
            for j in i:
                position = 26 if j == ' ' else ord(j) - ord('a')
                if sent_trie.char_arr[position] is  None:    
                    sent_trie.char_arr[position] = self.Trie()
                sent_trie = sent_trie.char_arr[position]
                sent_trie.sentences.append(idx)
            sent_trie.string_end = True
                    

    def destructor(self):
        self.current_trie = self.trie_dict
        self.input_sentence = []
        
    def input(self, c: str) -> List[str]:   
        if c == "#":
            new_sentence = True
            sentence = "".join(self.input_sentence)
            if self.current_trie.string_end:
                index = self.sentences.index(sentence)
                
                new_sentence = False
            else:
                self.current_trie.string_end = True
                self.sentences.append(sentence)
                index = len(self.sentences) -1
                self.times.append(0)
            self.times[index] += 1

            if new_sentence:
                current_trie = self.trie_dict
                current_trie.sentences.append(index)
                for i in self.input_sentence:
                    position =  26 if i == ' ' else ord(i) - ord('a')
                    current_trie = current_trie.char_arr[position]
                    current_trie.sentences.append(len(self.sentences)-1)
            self.destructor()
                
        else:
            self.input_sentence.append(c)
            position = 26 if c == ' ' else ord(c) - ord('a')
           
            if self.current_trie.char_arr[position] is  None:
                self.current_trie.char_arr[position] = self.Trie()
            self.current_trie = self.current_trie.char_arr[position]

            sentence_times_list = [(self.times[i], self.sentences[i]) for i in self.current_trie.sentences]
            asc_strings = sorted(sentence_times_list, key=operator.itemgetter(1))
            desc_timings_strings = sorted(asc_strings, key=operator.itemgetter(0), reverse=True)
            return [i[1] for i in desc_timings_strings[:3]]


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)