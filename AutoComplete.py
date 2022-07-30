# Time Complexity : O(1) --> UserLevel; O(n) --> constructor.
# Space Complexity : O(1) --> UserLevel; O(n) --> constructor.
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
#
#
# Your code here along with comments explaining your approach
from functools import cmp_to_key


class TrieNode:
    def __init__(self):
        self.children = [None] * 256
        self.words = []


class AutocompleteSystem:
    def __init__(self, sentences, times):
        self.dictu = {}
        self.sb = []
        self.root = TrieNode()
        for i in range(len(sentences)):
            if sentences[i] not in self.dictu:
                self.dictu[sentences[i]] = times[i]
            else:
                self.dictu[sentences[i]] += times[i]
            self.insert(sentences[i])

    def insert(self, word):
        # custom sort function.
        def cmp_items(a, b):
            if self.dictu[a] > self.dictu[b]:
                return -1
            elif self.dictu[a] == self.dictu[b]:
                if a > b:
                    return 1
                else:
                    return -1
            else:
                return 1

        temp = self.root
        for i in range(len(word)):
            letter = word[i]
            if not temp.children[ord(letter)]:
                temp.children[ord(letter)] = TrieNode()
            temp = temp.children[ord(letter)]
            if word not in temp.words:
                temp.words.append(word)
            # sorting the list.
            temp.words.sort(key=cmp_to_key(cmp_items))
            if len(temp.words) > 3:
                temp.words.pop()

    def search(self, word):
        temp = self.root
        for i in range(len(word)):
            letter = word[i]
            if not temp.children[ord(letter)]:
                return []
            temp = temp.children[ord(letter)]
        return temp.words

    def input(self, c):
        if c != '#':
            self.sb.append(c)
        temp = ''.join(self.sb)
        if c == '#':
            if temp in self.dictu:
                self.dictu[temp] += 1
            else:
                self.dictu[temp] = 1
            self.insert(temp)
            self.sb = []
            return
        return self.search(temp)


check = AutocompleteSystem(['i love you', 'island', 'ironman', 'i love leetcode'], [5, 3, 2, 2, 1])
print(check.input('i'))
print(check.input(' '))
print(check.input('a'))
print(check.input('#'))
print(check.input('i'))
print(check.input(' '))
print(check.input('a'))
print(check.input('#'))
print(check.input('i'))
print(check.input(' '))
print(check.input('a'))
print(check.input('#'))


# TC: O(k) k --> no.of words that starts with particular index; SC: O(1) --> UserLevel
# Constructor, TC: O(n); SC: O(n)
# import heapq
#
#
# class Comparator:
#     def __init__(self, word, frequency):
#         self.word = word
#         self.frequency = frequency
#
#     def __lt__(self, other):
#         if self.frequency == other.frequency:
#             return self.word > other.word
#         else:
#             return self.frequency < other.frequency
#
#
# class TrieNode:
#     def __init__(self):
#         self.children = [None] * 256
#         self.words = []
#
#
# class AutocompleteSystem:
#     def __init__(self, sentences, times):
#         self.dictu = {}
#         self.sb = []
#         self.root = TrieNode()
#         for i in range(len(sentences)):
#             if sentences[i] not in self.dictu:
#                 self.dictu[sentences[i]] = times[i]
#                 self.insert(sentences[i])
#             else:
#                 self.dictu[sentences[i]] += times[i]
#
#     def insert(self, word):
#         temp = self.root
#         for i in range(len(word)):
#             letter = word[i]
#             if not temp.children[ord(letter)]:
#                 temp.children[ord(letter)] = TrieNode()
#             temp = temp.children[ord(letter)]
#             temp.words.append(word)
#
#     def search(self, word):
#         temp = self.root
#         for i in range(len(word)):
#             letter = word[i]
#             if not temp.children[ord(letter)]:
#                 return []
#             temp = temp.children[ord(letter)]
#         return temp.words
#
#     def heap(self, word):
#         heap = []
#         result = []
#         temp = self.search(word)
#         for i in temp:
#             if i[:len(word)] == word:
#                 heapq.heappush(heap, Comparator(i, self.dictu[i]))
#                 if len(heap) > 3:
#                     heapq.heappop(heap)
#         for i in range(len(heap)):
#             result.insert(0, heapq.heappop(heap).word)
#         return result
#
#     def input(self, c):
#         if c != '#':
#             self.sb.append(c)
#         temp = ''.join(self.sb)
#         if c == '#':
#             if temp in self.dictu:
#                 self.dictu[temp] += 1
#             else:
#                 self.insert(temp)
#                 self.dictu[temp] = 1
#             self.sb = []
#             return
#         result = self.heap(temp)
#         return result

# TC: O(n*log(3)); SC: O(1) --> UserLevel
# Constructor, TC: O(n); SC: O(n)
# import heapq
#
#
# class Comparator:
#     def __init__(self, word, frequency):
#         self.word = word
#         self.frequency = frequency
#
#     def __lt__(self, other):
#         if self.frequency == other.frequency:
#             return self.word > other.word
#         else:
#             return self.frequency < other.frequency
#
#
# class AutocompleteSystem:
#     def __init__(self, sentences, times):
#         self.dictu = {}
#         self.sb = []
#         for i in range(len(sentences)):
#             if sentences[i] not in self.dictu:
#                 self.dictu[sentences[i]] = times[i]
#             else:
#                 self.dictu[sentences[i]] += times[i]
#
#     def heap(self, word):
#         heap = []
#         result = []
#         for i in self.dictu:
#             if i[:len(word)] == word:
#                 heapq.heappush(heap, Comparator(i, self.dictu[i]))
#                 if len(heap) > 3:
#                     heapq.heappop(heap)
#         for i in range(len(heap)):
#             result.insert(0, heapq.heappop(heap).word)
#         return result
#
#     def input(self, c):
#         if c != '#':
#             self.sb.append(c)
#         temp = ''.join(self.sb)
#         if c == '#':
#             if temp in self.dictu:
#                 self.dictu[temp] += 1
#             else:
#                 self.dictu[temp] = 1
#             self.sb = []
#             return
#         result = self.heap(temp)
#         return result


check = AutocompleteSystem(['i love you', 'leetcode', 'i love leetcode', 'island', 'ispat', 'ironman', 'india', 'i love leetcode'], [5, 7, 4, 3, 1, 4, 2, 1])
print(check.input('i'))
print(check.input(' '))
print(check.input('l'))
print(check.input('#'))
print(check.input('i'))
print(check.input(' '))
