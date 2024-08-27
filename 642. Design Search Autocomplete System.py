########642. Design Search Autocomplete System#############################################################################
// Time Complexity : O(1)
// Space Complexity : O(n) -> for Trie, hash map and O(1) for List on each TrieNode
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : no problem

// Your code here along with comments explaining your approach in three sentences only
We maintained a hash map with string as key and count as val, we also have a Trie with each node having top3 element in list as per hotness. When an character is searched we go in trie to that character and return the list once the search is over with # the count in hash map is increased and also we make sure list at Trie node is also updated

import heapq

class TrieNode:
    def __init__(self):
        self.children={}
        self.hotheap=[]

class Trie:
    def __init__(self):
        self.root=TrieNode()

    def insert(self,word,hashmap):
        curr=self.root
        for i,val in enumerate(word):
            if val not in curr.children:
                curr.children[val]=TrieNode()
            if word not in curr.children[val].hotheap:
                curr.children[val].hotheap.append(word)
            curr.children[val].hotheap.sort(key=lambda k: (-hashmap[k],k))
            if len(curr.children[val].hotheap)>3:
                curr.children[val].hotheap.pop()
            #print(curr.children[val].hotheap)
            curr=curr.children[val]

    def startswith(self,str) -> list:
        curr=self.root
        for i in str:
            if i not in curr.children:
                return []
            else:
                curr=curr.children[i]
        return curr.hotheap

class Customtuple: #custom tuple for comparision in heap
    def __init__(self,key,val):
        self.key=key
        self.val=val

    def __lt__(self,other):
        if self.key==other.key:
            return self.val>other.val
        return self.key<other.key

class AutocompleteSystem:


    def __init__(self, sentences: List[str], times: List[int]):
        self.hashmap={} #hashmap to keep word and count
        self.Trieword=Trie()
        self.substr=''  #string to keep user input
        i=0
        while i<len(sentences): #update hashmap
            self.hashmap[sentences[i]]=times[i]
            self.Trieword.insert(sentences[i],self.hashmap)
            i+=1
        
 
        

    def input(self, c: str) -> List[str]:
        if c=="#":  #if word ended add it to hashmap
            if self.substr not in self.hashmap:
                self.hashmap[self.substr]=0
            self.hashmap[self.substr]+=1
            self.Trieword.insert(self.substr,self.hashmap)
            self.substr=''
            return []
        else:
         
            self.substr+=c
            listtmp=self.Trieword.startswith(self.substr)
            return listtmp

        


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)
        
        
            
            

        
        
