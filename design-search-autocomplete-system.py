# Time Complexity: O(n*L) L is length of search string
# Space Complexity: O(n)
# class ob:
#     def __init__(self,sentence,val):
#       self.sentence=sentence
#       self.val=val
#     def __lt__(self,other):
#       if self.val==other.val:
#         return other.sentence<self.sentence
#       return self.val<other.val
# class AutocompleteSystem:
#   def __init__(self, sentences,times):
#     self.hmap={}
#     self.str=[]
#     for i in range(len(sentences)):
#       self.hmap[sentences[i]]=self.hmap.get(sentences[i],0)+times[i]
#   def input(self,c):
#     if c=='#':
#       s="".join(self.str)
#       self.hmap[s]=self.hmap.get(s,0)+1
#       self.str=[]
#     self.str.append(c)
#     s="".join(self.str)
#     heap=[]
#     from heapq import heappush as push
#     from heapq import heappop as pop
#     for key in self.hmap:
#       if key.startswith(s):
#         push(heap,ob(key,self.hmap[key]))
#         if len(heap)>3:
#           pop(heap)
#     result=[]
#     while len(heap)!=0:
#       result.insert(0,pop(heap).sentence)
#     return result
      
# obj=AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
# print(obj.input('i'),obj.input(' '),obj.input('a'),obj.input('#'))
# ####################################################
# Time Complexity: O(k)
# Space Complexity: O(n)
# class ob:
#     def __init__(self,sentence,val):
#       self.sentence=sentence
#       self.val=val
#     def __lt__(self,other):
#       if self.val==other.val:
#         return other.sentence<self.sentence
#       return self.val<other.val
      
# class AutocompleteSystem:
#   class TriNode:
#     def __init__(self):
#       self.children=[None]*256
#       self.li=[]
#   def insert(self,word):
#     cur=self.root
#     for i in range(len(word)):
#       if cur.children[ord(word[i])-ord(' ')]==None:
#         cur.children[ord(word[i])-ord(' ')]=self.TriNode()
#       cur=cur.children[ord(word[i])-ord(' ')]
#       cur.li.append(word)
#   def search(self,s):
#     cur=self.root
#     for i in range(len(s)):
#       if cur.children[ord(s[i])-ord(' ')]==None:
#         return []
#       cur=cur.children[ord(s[i])-ord(' ')]
#     return cur.li
    
#   def __init__(self, sentences,times):
#     self.hmap={}
#     self.str=[]
#     self.root=self.TriNode()
#     for i in range(len(sentences)):
#       if self.hmap.get(sentences[i])==None: self.insert(sentences[i])
#       self.hmap[sentences[i]]=self.hmap.get(sentences[i],0)+times[i]
#   def input(self,c):
#     if c=='#':
#       s="".join(self.str)
#       if s not in self.hmap: self.insert(s)
#       self.hmap[s]=self.hmap.get(s,0)+1
#       self.str=[]
#     self.str.append(c)
#     s="".join(self.str)
#     heap=[]
#     from heapq import heappush as push
#     from heapq import heappop as pop
#     lis=self.search(s)
#     for i in lis:
#       push(heap,ob(i,self.hmap[i]))
#       if len(heap)>3:
#         pop(heap)
#     result=[]
#     while len(heap)!=0:
#       result.insert(0,pop(heap).sentence)
#     return result
      
# obj=AutocompleteSystem(["i love you","island","ironman", "i love leetcode"], [5,3,2,2])
# print(obj.input('i'),obj.input(' '),obj.input('a'),obj.input('#'))

  
###################################################
# Time Complexity: O(1)
# Space Complexity: O(n)
class ob:
    def __init__(self,sentence,val):
      self.sentence=sentence
      self.val=val
    def __lt__(self,other):
      if self.val==other.val:
        return other.sentence<self.sentence
      return self.val<other.val
      
class AutocompleteSystem:
  class TriNode:
    def __init__(self):
      self.children=[None]*256
      self.li=[]
  def insert(self,word):
    cur=self.root
    for i in range(len(word)):
      if cur.children[ord(word[i])-ord(' ')]==None:
        cur.children[ord(word[i])-ord(' ')]=self.TriNode()
      cur=cur.children[ord(word[i])-ord(' ')]
      temp=cur.li
      if word not in temp: temp.append(word)
      temp.sort(key=lambda x: (-self.hmap[x],x))
      if len(temp)>3: temp.pop()
      cur.li=temp
  def search(self,s):
    cur=self.root
    for i in range(len(s)):
      if cur.children[ord(s[i])-ord(' ')]==None:
        return []
      cur=cur.children[ord(s[i])-ord(' ')]
    return cur.li
    
  def __init__(self, sentences,times):
    self.hmap={}
    self.str=[]
    self.root=self.TriNode()
    for i in range(len(sentences)):
      self.hmap[sentences[i]]=self.hmap.get(sentences[i],0)+times[i]
      self.insert(sentences[i])
  def input(self,c):
    if c=='#':
      s="".join(self.str)
      self.hmap[s]=self.hmap.get(s,0)+1
      self.insert(s)
      self.str=[]
    self.str.append(c)
    s="".join(self.str)
    heap=[]
    from heapq import heappush as push
    from heapq import heappop as pop
    lis=self.search(s)
    for i in lis:
      push(heap,ob(i,self.hmap[i]))
      if len(heap)>3:
        pop(heap)
    result=[]
    while len(heap)!=0:
      result.insert(0,pop(heap).sentence)
    return result
      
obj=AutocompleteSystem(["i love you","island","ironman", "i love leetcode"], [5,3,2,2])
print(obj.input('i'),obj.input(' '),obj.input('a'),obj.input('#'))