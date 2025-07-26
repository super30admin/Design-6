# Time Complexity: O(n * m * log(m)) for initialization and O(m) for each input character.
# Space Complexity : O(n * m) for storing the trie.

import heapq

class TrieNode:
    def __init__(self):
        self.children = {}
        self.sentences = []

class AutocompleteSystem:

    def __init__(self, sentences, times):
        self.root = TrieNode()
        self.search_term = ""
        self.hot_sentences = {}
        
        for sentence, time in zip(sentences, times):
            self.hot_sentences[sentence] = time
            self.add_sentence_to_trie(sentence, time)
    
    def add_sentence_to_trie(self, sentence, hot):
        node = self.root
        for char in sentence:
            if char not in node.children:
                node.children[char] = TrieNode()
            node = node.children[char]
            node.sentences.append((hot, sentence))
            node.sentences.sort(key=lambda x: (-x[0], x[1]))
            if len(node.sentences) > 3:
                node.sentences = node.sentences[:3]

    def input(self, c):
        if c == '#':
            self.hot_sentences[self.search_term] = self.hot_sentences.get(self.search_term, 0) + 1
            self.add_sentence_to_trie(self.search_term, self.hot_sentences[self.search_term])
            self.search_term = ""
            return []
        else:
            self.search_term += c
            node = self.root
            for char in self.search_term:
                if char in node.children:
                    node = node.children[char]
                else:
                    return []
            return [sentence for _, sentence in node.sentences]

