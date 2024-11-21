# -*- coding: utf-8 -*-
"""
TC: O(l + m + m * log(m)) l = no. of sentences, m = no. of letters in each sentence
SC: O(m) 
"""

class Trie:
    def __init__(self):
        self.root = {}
    
    def add(self, word, times):
        
        curr = self.root
        
        for c in word:
            if c not in curr:
                curr[c] = {}
            curr = curr[c]
        
        if '*' in curr:
            curr['*'][1] += times
        else:
            curr['*'] = [word, times]
    
        
    def starts_with(self, pref):
        
        if pref == '':
            return []
        
        curr = self.root
        
        for c in pref:
            if c not in curr:
                return []
            curr = curr[c]
        
        res = []
        self.get_words(curr, res)
        
        return res
        
    def get_words(self, curr, res):

        for c in curr:
            if c == '*':
                res.append(curr[c])
            else:
                self.get_words(curr[c], res)
            
    
class AutocompleteSystem(object):

    def __init__(self, sentences, times):
        """
        :type sentences: List[str]
        :type times: List[int]
        """
        
        self.curr_input = []
        self.k = 3
        self.trie = Trie()
        for i, sen in enumerate(sentences):
            self.trie.add(sen, times[i])
        
        # time O(n * l)
        # space O(n * l)
        
        
    def input(self, c):
        """
        :type c: str
        :rtype: List[str]
        """
        
        if c == '#':
            # add the word to trie
            self.trie.add(''.join(self.curr_input), 1)
            self.curr_input = []
            return []
        
        self.curr_input.append(c)
        
        words = self.trie.starts_with(''.join(self.curr_input))
        
        words.sort()
        words.sort(key=lambda x: x[1], reverse=True)
        
        return [words[i][0] for i in range(min(self.k, len(words)))]
    

        
        


