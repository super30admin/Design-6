'''
TC: O(n)
SC: O(n)
'''

class AutocompleteSystem(object):
    class TrieNode:
        def __init__(self, val):
            self.val = val
            self.nodes = dict()
            self.values = dict()
            self.back = None

    def __init__(self, sentences, times):
        self.root = self.TrieNode("root")
        self.record = str()
        tmp = self.root
        for i in range(len(sentences)):
            word = sentences[i]
            for letter in word:
                if letter in tmp.nodes:
                    tmp.nodes[letter].values[word] = times[i]
                else:
                    tmp.nodes[letter] = self.TrieNode(letter)
                    tmp.nodes[letter].back = tmp 
                    tmp.nodes[letter].values[word] = times[i]
                tmp = tmp.nodes[letter]
            tmp = self.root
        self.curr = self.root
        """
        :type sentences: List[str]
        :type times: List[int]
        """

    def input(self, c):
        if str(c) != '#':
            self.record += c
            if c in self.curr.nodes:
                self.curr = self.curr.nodes[c]
                if len(self.curr.values.keys()) <= 3:
                    return sorted(self.curr.values, key=lambda k: (-self.curr.values[k], k))
                else:
                    return sorted(self.curr.values, key=lambda k: (-self.curr.values[k], k))[:3]
            else:
                self.curr.nodes[c] = self.TrieNode(c)
                self.curr.nodes[c].back = self.curr
                self.curr = self.curr.nodes[c]
                return []
        else:
            while self.curr.back is not None:
                if self.record in self.curr.values:
                    self.curr.values[self.record] += 1
                else:
                    self.curr.values[self.record] = 1
                self.curr = self.curr.back
            self.curr = self.root
            self.record = ""
            return []