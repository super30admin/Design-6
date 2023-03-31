

import collections
import heapq

#Here we store the sentences in Trie and every Trie node holds top 3 sentences for the prefix uptill that char
class Node:
    def __init__(self, s, count):
        self.sString = s
        self.count = count

    def __lt__(self, other):
        if self.count==other.count:
            return self.sString>other.sString
        return self.count<other.count

class Trie:
    def __init__(self):
        self.children = [None]*256
        self.list = []



class AutocompleteSystem:

  #Here we create a map with sentence as key and Node=(sentence, count) as value. We add every sentence to Trie
  def __init__(self, sentences, times):
      self.map = collections.defaultdict(int)
      self.searchString = ""
      self.root = Trie()

      for i in range(len(sentences)):
          s = sentences[i]
          if s in self.map:
              node = self.map[s]
              node.count+=1
              self.insert(s, node)
          else:
              node = Node(s, times[i])
              self.map[s] = node
              self.insert(s, node)

  # Here we return add the sentence to Trie and store Node=(sentence, count) combination in minHeap for every char
  # Time complexity - O(n) where n is size of sentence
  # Space complexity - O(1)
  def insert(self, sentence, node):
      cur = self.root
      for i in range(len(sentence)):
          ch = sentence[i]
          if not cur.children[ord(ch)-ord(' ')]:
              cur.children[ord(ch) - ord(' ')] = Trie()
          cur = cur.children[ord(ch)-ord(' ')]
          heapq.heappush(cur.list, node)
          if len(cur.list)>3:
              heapq.heappop(cur.list)

  # Here we return the top 3 sentences stored in Trie for the prefix passed
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

  #Here we return the top 3 sentences stored in Trie for the input passed
  #Time complexity - O(1)
  #Space complexity - O(1)
  def input(self, c: str):
      if c=="#":
          if self.searchString in self.map:
              node = self.map[self.searchString]
              node.count+=1
              self.insert(self.searchString, node)
          else:
              node = Node(self.searchString, 1)
              self.map[self.searchString] = node
              self.insert(self.searchString, node)
          self.searchString = ""
          return []
      self.searchString += c
      topMatches = self.search(self.searchString)
      res = []
      for i in range(len(topMatches)):
          res.insert(0, heapq.heappop(topMatches).sString)
      return res


s = AutocompleteSystem(["i love you", "island", "iroman", "i love leetcode"], [5,3,2,2])
print(s.input("i"))
print(s.input(" "))
print(s.input("is"))
print(s.input("#"))



