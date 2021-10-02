"""
Time Complexity : O(n) where n is the number of sentences
Space Complexity : O(n) where n is the number of sentences
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No
"""
class TreiNode:
    def __init__(self):
        self.children = collections.defaultdict(TreiNode)
        self.sentence = collections.defaultdict(int)

class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.root = TreiNode()
        self.curnode = self.root
        self.cursentence = ""
        for sentence, time in zip(sentences, times):
            self.store(sentence, time)
        

    def input(self, c: str) -> List[str]:
        if c=='#':
            self.store(self.cursentence,1)
            self.curnode = self.root
            self.cursentence = ""
        else:
            self.cursentence+=c
            self.curnode = self.curnode.children[c]
            if len(self.curnode.sentence)>0:
                l = sorted([k for k in self.curnode.sentence], key=lambda k: (-self.curnode.sentence[k],k))
                return l[:3]
            else:
                return []
            
            

        
    def store(self, sentence, time):
        node = self.root
        for c in sentence:
            node = node.children[c]
            node.sentence[sentence]+=time

                
        


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)