

import collections
import heapq

#Here we store the sentences in map as key and Node(sentence, count) combination as value.
# For input passed we search for all sentences in map that start with input and return the top 3 using minHeap

class Node:
    def __init__(self, s, count):
        self.sString = s
        self.count = count

    def __lt__(self, other):
        if self.count==other.count:
            return self.sString > other.sString
        return self.count < other.count


class AutocompleteSystem:

# Here we create a map with sentence as key and Node=(sentence, count) as value.
  def __init__(self, sentences, times):
      self.map = collections.defaultdict(int)
      self.searchString = ""
      self.minHeap = []

      for i in range(len(sentences)):
          s = sentences[i]
          if s in self.map:
              node = self.map[s]
              node.count += times[i]
          else:
            node = Node(s, times[i])
            self.map[s] = node

  #Here we return the top 3 sentences from the list of sentences that match the input passed
  # Time complexity - O(nlog3) -> where n is list of sentence starting with input chars
  # Space complexity - O(1)
  def input(self, c: str):
      if c=="#":
          if self.searchString in self.map:
              node = self.map[self.searchString]
              node.count += 1
          else:
              node = Node(self.searchString, 1)
              self.map[self.searchString] = node

          self.searchString = ""
          return []

      self.searchString += c

      for key in self.map.keys():
          if key.startswith(self.searchString):
              heapq.heappush(self.minHeap, self.map[key])
              if len(self.minHeap)>3:
                  heapq.heappop(self.minHeap)

      res = []
      for i in range(len(self.minHeap)):
          res.insert(0, heapq.heappop(self.minHeap).sString)
      return res


s = AutocompleteSystem(["i love you", "island", "iroman", "i love leetcode"], [5,3,2,2])
print(s.input("i"))
print( s.input(" "))
print( s.input("a"))
print(s.input("#"))


