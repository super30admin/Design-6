

import collections
import heapq

#Here we store the sentences in Trie and every Trie node holds all
# sentences for the prefix uptill that char

class Node:
    def __init__(self, s, count):
        self.sString = s
        self.count = count

    def __lt__(self, other):
        if self.count==other.count:
            return self.sString > other.sString
        return self.count < other.count

class Trie:
    def __init__(self):
        self.children = [None]*256
        self.list = []


class AutocompleteSystem:

  #Here we create a map with sentence as key and Node=(sentence, count) as value. We add every sentence to Trie.
  # Every Trie holds the sentence that start with prefix uptill that char
  def __init__(self, sentences, times):
      self.map = collections.defaultdict(int)
      self.searchString = ""
      self.minHeap = []
      self.root = Trie()

      for i in range(len(sentences)):
          s = sentences[i]
          if s in self.map:
              node = self.map[s]
              node.count += times[i]
          else:
              self.insert(s)
              node = Node(s, times[i])
              self.map[s] = node

  # Here we return add the sentence to Trie and list of sentences for every Trie
  # Time complexity - O(n) where n is size of sentence
  # Space complexity - O(1)
  def insert(self, sentence):
      cur = self.root
      for i in range(len(sentence)):
          ch = sentence[i]
          if not cur.children[ord(ch)-ord(' ')]:
              cur.children[ord(ch) - ord(' ')] = Trie()
          cur = cur.children[ord(ch)-ord(' ')]
          cur.list.append(sentence)

  # Here we return all the sentences stored in Trie for the prefix passed
  # Time complexity - O(n) where n is size of prefix
  # Space complexity - O(1)
  def search(self, prefix):
      res = []
      cur = self.root
      for i in range(len(prefix)):
          ch = prefix[i]
          if not cur.children[ord(ch)-ord(' ')]:
              return []
          cur = cur.children[ord(ch)-ord(' ')]
      return cur.list

  # Here we return the top 3 sentences from the list of sentences stored in Trie for the input passed
  # Time complexity - O(klog3) -> O(k) where k is list of sentence stored in Trie
  # Space complexity - O(1)
  def input(self, c: str):
      if c=="#":
          if self.searchString in self.map:
              node = self.map[self.searchString]
              node.count += 1
          else:
              self.insert(self.searchString)
              node = Node(self.searchString, 1)
              self.map[self.searchString] = node
          self.searchString = ""
          return []

      self.searchString += c
      matches = self.search(self.searchString)
      for key in matches:
          if key.startswith(self.searchString):
              heapq.heappush(self.minHeap, self.map[key])
              if len(self.minHeap)>3:
                  heapq.heappop(self.minHeap)

      res = []
      for i in range(len(self.minHeap)):
          res.insert(0, heapq.heappop(self.minHeap).sString)
      return res


s =AutocompleteSystem(["i love you", "island", "iroman", "i love leetcode"], [5,3,2,2])
print(s.input("i"))
print(s.input(" "))
print(s.input("a"))
print(s.input("#"))



