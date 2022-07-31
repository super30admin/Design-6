""""// Time Complexity : O(1)
// Space Complexity :O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :
"""
class TrieNode:
    def __init__(self):
        self.pq=[]
        self.children=[False for i in range(256)]



class Autocomplete:
    def __init__(self,sentence,times):
        self.hmap={}
        self.root=TrieNode()
        self.sb=[]
        for i in range(len(sentence)):
            if sentence[i] not in self.hmap.keys():
                self.hmap[sentence[i]]=0
            self.hmap[sentence[i]]+=times[i]
            self.insert(sentence[i],times[i])

    def insert(self,st,time):
        curr=self.root

        for i in range(len(st)):
            c=st[i]
            if not curr.children[ord(c) -ord(' ')]:
                curr.children[ord(c)-ord(' ')]=TrieNode()
            curr=curr.children[ord(c)-ord(' ')]

            li=curr.pq

            if st not in li:
                li.append([st,time])

            for i in li:
                sorted(li,key=lambda x:x[1])
                sorted(li)
            if len(li)>3:
                li.pop(len(li)-1)

        curr.pq=li

    def search(self,prefix):
        curr=self.root
        for i in range(len(prefix)):
            c=prefix[i]
            if not curr.children[ord(c)-ord(' ')]:
                return []
            curr=curr.children[ord(c)-ord(' ')]
        return curr.pq



    def input(self,c):
        st=""
        if c=='#':
            for i in range(len(self.sb)):
                st=st+self.sb[i]
            if st not in self.hmap:
                self.hmap[st]=0
            self.hmap[st]+=1
            self.insert(st,1)
            self.sb=[]
            return []
        else:
            self.sb.append(c)
            for i in range(len(self.sb)):
                st=st+self.sb[i]
            return self.search(st)


def main():
    sentence=["i love you","island","ironman","i love leetcode","blah"]
    times=[5,3,2,2,8]
    obj=Autocomplete(sentence,times)
    print(obj.input("i"))
main()


""""// Time Complexity : O(n) each key's prefix in the dictionary
// Space Complexity :O(n) for dictionary
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :
"""
# import heapq
# from heapq import heappush as push
# from heapq import heappop as pop
#
# class pairList:
#     def __init__(self,sentence,frequency):
#         self.sentence = sentence
#         self.freq = frequency
#
#     def __lt__(self, other):
#         if self.freq == other.freq:
#             return self.sentence > other.sentence
#         return self.freq < other.freq
#
# class AutocompleteSystem:
#
#     def __init__(self, sentences: List[str], times: List[int]):
#         self.dict = {}
#         self.inputStr = []
#         for i in range(0,len(sentences)):
#             if sentences[i] not in self.dict:
#                 self.dict[sentences[i]] = times[i]
#
#     def input(self, c: str) -> List[str]:
#         if c == "#":
#             search=''.join(self.inputStr)
#             if search not in self.dict:
#                 self.dict[search] = 1
#             else:
#                 self.dict[search]+= 1
#             self.inputStr = []
#             return []
#         self.inputStr.append(c)
#
#         minHeap = []
#         count = 0
#         search = ''.join(self.inputStr)
#         for key in self.dict:
#             if key[0:len(self.inputStr)] == search:
#                 pair = pairList(key,self.dict[key])
#                 push(minHeap,pair)
#                 count += 1
#
#             if count > 3:
#                 pop(minHeap)
#                 count -= 1
#
#
#         resultList = []
#         for pair in minHeap:
#             resultList.append(0, pair.sentence)
#         return resultList


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)