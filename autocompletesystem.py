# Using Tries to store the prefix
# sentence list to store the tuple (sentence, count)
# store only the index of the sentence present in the list along with every trie node.
# this mapping (sentence-index mapping) is maintained in the hash map.
# Time complexity - O(m+n) # O(m) - m is the length of the input. O(n) - results corresponding to a search query (worst case - all n sentences)
# Space complexity - O(n^2+mn) # O(n^2) for sentences set. 1 set corresponding to each n node. n is the max length of each set. # O()
# Did this code run on leetcode? - yes

# Trienode to store the children of the current node and set to store the sentences corresponding to it.
class TrieNode:
    def __init__(self):
        self.children = dict()  # space - O(mn)
        self.sentences = set()  # space - O(n^2)
        
        
class Node:
    def __init__(self, sent, count):
        self.sentence = sent
        self.count = count
    
    def __gt__(self, other):
        if self.count > other.count or (self.count==other.count and self.sentence < other.sentence):
            return True
        return False


class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        # create a sentence trie.
        self.sentence_trie = {
                            'root': TrieNode()
                        }
        
        # create array to keep the sentence and it's count.
        self.sent_array = []    # space - O(mn)
        
        # create a hash map to store the index and sentence.
        self.sent_index_map = dict()    # space - O(mn)
        
        # keep a count 
        self.count = 0
        
        # Trie to track the sentences
        for i,sentence in enumerate(sentences):
            # if sentence is not present in the sentence index mapping.
            self.add_trie(sentence, times[i])
      
        # print(sentence_trie['root'].children['i'].children)
        # print(sent_array)
        self.input_str = ""
    
    
    def search_trie(self):
        # point the current to the root node.
        curr = self.sentence_trie['root']
        # traverse the input and return the indices to the sentences.
        for ch in self.input_str:
            if ch in curr.children:
                curr = curr.children[ch]
            else:
                return []
        return curr.sentences
    
    def add_trie(self, sentence, times):
        if sentence not in self.sent_index_map:
            self.sent_index_map[sentence] = self.count
            idx = self.count
            self.count += 1
            self.sent_array.append((sentence, times))
        else:
            idx = self.sent_index_map[sentence]
            sent_array_pair = self.sent_array[idx]
            self.sent_array[idx] = (sentence, sent_array_pair[1]+times)

        curr = self.sentence_trie['root']
        for ch in sentence:
            if ch not in curr.children:
                curr.children[ch] = TrieNode()
            # add the index of the sentence to the sentences set.
            curr.sentences.add(idx)
            # navigate to the new child node
            curr = curr.children[ch]
        
        # add the sentence at the end.
        curr.sentences.add(idx)
                

    def input(self, c: str) -> List[str]:
        if c=="#":
            # add the current input to the trie
            self.add_trie(self.input_str, 1)
            self.input_str = ""
            return []
            
        self.input_str += c
        indices = self.search_trie()
        # print(indices)
        # maintain a priority queue (min. heap of length 3)
        counts = []
        if len(indices)==0:
            return []
        
        for idx in indices:
            s, c = self.sent_array[idx][0], self.sent_array[idx][1]
            if len(counts)>=3:
                heapq.heappushpop(counts, Node(s, c))
            else:
                heapq.heappush(counts, Node(s, c))
        
        # return the sentences.
        answer = []
        while counts:
            answer.append(heapq.heappop(counts).sentence)
        
        return answer[::-1]
        


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)