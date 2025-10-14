'''
642 Design Search Autocomplete System
https://leetcode.com/problems/design-search-autocomplete-system/description/

Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#').

You are given a string array sentences and an integer array times both of length n where sentences[i] is a previously typed sentence and times[i] is the corresponding number of times the sentence was typed. For each input character except '#', return the top 3 historical hot sentences that have the same prefix as the part of the sentence already typed.

Here are the specific rules:
The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same hot degree, use ASCII-code order (smaller one appears first).
If less than 3 hot sentences exist, return as many as you can.
When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.

Implement the AutocompleteSystem class:
AutocompleteSystem(String[] sentences, int[] times) Initializes the object with the sentences and times arrays.
List<String> input(char c) This indicates that the user typed the character c.
    Returns an empty array [] if c == '#' and stores the inputted sentence in the system.
    Returns the top 3 historical hot sentences that have the same prefix as the part of the sentence already typed. If there are fewer than 3 matches, return them all.

Example 1:
Input
["AutocompleteSystem", "input", "input", "input", "input"]
[[["i love you", "island", "iroman", "i love leetcode"], [5, 3, 2, 2]], ["i"], [" "], ["a"], ["#"]]
Output
[null, ["i love you", "island", "i love leetcode"], ["i love you", "i love leetcode"], [], []]

Explanation
AutocompleteSystem obj = new AutocompleteSystem(["i love you", "island", "iroman", "i love leetcode"], [5, 3, 2, 2]);
obj.input("i"); // return ["i love you", "island", "i love leetcode"]. There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.
obj.input(" "); // return ["i love you", "i love leetcode"]. There are only two sentences that have prefix "i ".
obj.input("a"); // return []. There are no sentences that have prefix "i a".
obj.input("#"); // return []. The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.

Constraints:
n == sentences.length
n == times.length
1 <= n <= 100
1 <= sentences[i].length <= 100
1 <= times[i] <= 50
c is a lowercase English letter, a hash '#', or space ' '.
Each tested sentence will be a sequence of characters c that end with the character '#'.
Each tested sentence will have a length in the range [1, 200].
The words in each input sentence are separated by single spaces.
At most 5000 calls will be made to input.

Solution:
1. Brute Force (Hash map + Priority Queue)
 We keep track of all sentences and their counts using a map. When a character is typed, we find all matching sentences and use a min-heap to keep only the top 3 sentences (top defined by frequency of occurrence). If two sentences have the same frequency, then they are arranged lexicographical order (smaller lexicographical appears before greater). After collecting the top 3, we reverse the heap output to show the results in correct order.

 https://youtu.be/klpDIfsnhXk?t=1982
 https://youtu.be/klpDIfsnhXk?t=2971 (problem with this approach: scan through the entire map for each input character typed)

 Time: O(NL*log3) = O(NL) per input call, N = the total number of unique sentences, L = avg length (no. of characters) per word
 Space: O(N) for storing all sentences in the map

2. Sub-Optimal (Trie + Hash map + Priority Queue)
We use a Trie where each node stores a list of sentences starting with that prefix, and for each Trie node we also maintain sentence and frequencies in a hash map. When a character is typed, we search the Trie for matching sentences and use a min-heap to keep the top 3 based on frequency and then lexicographical order. Finally, we reverse the heap output to return the most relevant 3 suggestions. The problem with this approach is that a Trie node could have a very large hash map. Eg. input prefix = "a", the hash map of this node will contain all the sentences starting with "a"!!

https://youtu.be/klpDIfsnhXk?t=3046

Time: O(N log 3) per input call -> O(N), N  = total number of sentences starting with the prefix.
Space: O(N^2 * L^2) (due to storing many sentences in many prefix nodes)

3. Optimal (Trie + Hash map + Priority Queue)
We build a Trie where each node keeps track of only the top 3 hot sentences seen so far. Thus, every prefix Trie node stores the top 3 sentences using a list (instead of hash map as in the sub-optimal solution). We also maintain a single universal map to track the sentence and the frequency count.
It is possible that a list associated with the prefix trie node might have a sentence that falls in top-3 list but later after multiple input queries is replaced by a new sentence not in the list. This is where the universal map
helps in updating the list (replace old sentence with lower freq by a new sentence with higher freq). When typing a character, we walk down the Trie and simply return the node's top 3 list. If we hit #, we record the full sentence and update the Trie with it.

https://youtu.be/klpDIfsnhXk?t=4415

Time:
Insert(word): O(L * log 3) = O(L), where L = length of word. Sorting 3 elements is constant.
Input(char): O(P) = O(1), where P = length of current prefix

Space:
O(NL), where N = number of unique sentences and L = average length of word

'''
from typing import List
from collections import defaultdict
import heapq

class FreqSentence(object):
    def __init__(self, freq, sentence):
        self.freq = freq
        self.sentence = sentence

    def __lt__(self, other):
        if self.freq == other.freq:
            return not self.sentence < other.sentence
        else:
            return self.freq < other.freq

class AutocompleteSystem1:
    def __init__(self, sentences: List[str], times: List[int]):
        print(f"\nInitiating AutocompleteSystem1 ...")
        N = len(sentences)
        self.map = defaultdict(int)
        self.buffer = [] # character buffer
        self.topk = 3
        for i in range(N):
            sentence = sentences[i]
            self.map[sentence] += times[i]

    def input(self, c: str) -> List[str]:
        heap = []
        if c == '#':
            prefix = "".join(self.buffer)
            self.map[prefix] += 1
            self.buffer = []
            return []
        else:
            self.buffer.append(c)
            prefix = "".join(self.buffer)
            for sentence in self.map: # O(N)
                if sentence.startswith(prefix): # O(L)
                    freq = self.map[sentence]
                    if len(heap) < self.topk:
                        heapq.heappush(heap, FreqSentence(freq, sentence))
                    else:
                        heapq.heappushpop(heap, FreqSentence(freq, sentence))
            result = [""]*len(heap)
            i = len(heap) - 1
            for i in range(len(heap)-1,-1,-1):
                fs_obj = heapq.heappop(heap)
                result[i] = fs_obj.sentence
            return result

class AutocompleteSystem2:
    class TrieNode:
        def __init__(self):
            self.children = defaultdict(AutocompleteSystem2.TrieNode)
            self.map = defaultdict(int) # key=sentence,val=frequency
            self.isEOS = False

    class Trie:
        def __init__(self):
            self.root = AutocompleteSystem2.TrieNode()

        def insert(self, sentence, frequency):
            curr = self.root
            if not curr:
                return None
            for c in sentence:
                if c not in curr.children:
                    curr.children[c] = AutocompleteSystem2.TrieNode()
                curr = curr.children[c]
                curr.map[sentence] += frequency
            curr.isEOS = True

        def startsWith(self, prefix):
            ''' return the list of words in trie which contains prefix
            '''
            curr = self.root
            if not curr:
                return None
            for c in prefix:
                if c not in curr.children:
                    return {}
                curr = curr.children[c]
            return curr.map

    def __init__(self, sentences: List[str], times: List[int]):
        print(f"\nInitiating AutocompleteSystem2 ...")
        N = len(sentences)
        self.trie = AutocompleteSystem2.Trie()
        self.buffer = [] # character buffer
        self.topk = 3
        for i in range(N):
            sentence = sentences[i]
            frequency = times[i]
            self.trie.insert(sentence, frequency)

    def input(self, c: str) -> List[str]:
        heap = []
        if c == '#':
            prefix = "".join(self.buffer)
            self.trie.insert(prefix, 1)
            self.buffer = []
            return []
        else:
            self.buffer.append(c)
            prefix = "".join(self.buffer)
            map = self.trie.startsWith(prefix)
            for sentence in map:
                freq = map[sentence]
                if len(heap) < self.topk:
                    heapq.heappush(heap, FreqSentence(freq, sentence))
                else:
                    heapq.heappushpop(heap, FreqSentence(freq, sentence))
            result = [""]*len(heap)
            i = len(heap) - 1
            for i in range(len(heap)-1,-1,-1):
                fs_obj = heapq.heappop(heap)
                result[i] = fs_obj.sentence
            return result


class AutocompleteSystem3:
    class TrieNode:
        def __init__(self):
            self.children = defaultdict(AutocompleteSystem3.TrieNode)
            self.pq = [] # list of top-k strings
            self.isEOS = False

    class Trie:
        def __init__(self, topk):
            self.root = AutocompleteSystem3.TrieNode()
            self.topk = topk

        def insert(self, sentence, frequency):
            curr = self.root
            if not curr:
                return None
            for c in sentence:
                if c not in curr.children:
                    curr.children[c] = AutocompleteSystem3.TrieNode()
                curr = curr.children[c]

                # a) avoid duplication of sentences in list: if sentence is
                # already not in list, add it
                i = 0
                while i < len(curr.pq):
                    if curr.pq[i][1] == sentence: # duplicate sentence found
                        curr.pq[i] = (frequency, sentence) # just update freq
                        break
                    i += 1
                if i == len(curr.pq): # duplicate sentence not found, add it
                    curr.pq.append((frequency,sentence))
                # b) sort by frequency and lexicographical order
                curr.pq.sort(key = lambda x: (-x[0], x[1]))
                # c) remove element from list if exceeds size
                if len(curr.pq) > self.topk:
                    curr.pq.pop()

            curr.isEOS = True

        def startsWith(self, prefix):
            ''' return the list of top-k sentences in the trie which contains
                prefix
            '''
            curr = self.root
            if not curr:
                return None
            for c in prefix:
                if c not in curr.children:
                    return []
                curr = curr.children[c]
            topk_sentences = [item[1] for item in curr.pq]
            return topk_sentences

    def __init__(self, sentences: List[str], times: List[int]):
        print(f"\nInitiating AutocompleteSystem3 ...")
        N = len(sentences)
        self.topk = 3
        self.trie = AutocompleteSystem3.Trie(self.topk)
        # universal map at the root node which captures <sentence,
        # frequency> key-value pairs for all the sentences stored
        # in the trie
        self.map = defaultdict(int)
        self.buffer = [] # character buffer
        for i in range(N):
            sentence = sentences[i]
            frequency = times[i]
            self.map[sentence] = frequency
            self.trie.insert(sentence, self.map[sentence])

    def input(self, c: str) -> List[str]:
        if c == '#':
            prefix = "".join(self.buffer)
            self.map[prefix] += 1
            self.trie.insert(prefix, self.map[prefix])
            self.buffer = []
            return []
        else:
            self.buffer.append(c)
            prefix = "".join(self.buffer)
            result = self.trie.startsWith(prefix)
            return result

def run_AutocompleteSystem():
    tests = [  # test 1
               ( ["AutocompleteSystem", "input", "input", "input", "input"],
                [
                 [["i love you", "island", "iroman", "i love leetcode"], [5, 3,2,2]],
                 ["i"],
                 [" "],
                 ["a"],
                 ["#"],
                ],
                [None,
                 ["i love you", "island", "i love leetcode"],
                 ["i love you", "i love leetcode"],
                 [],
                 []
                ]
              ),
              # test 2
              (["AutocompleteSystem","input","input","input","input","input","input","input","input","input","input","input","input"],
               [
                [["i love you","island","iroman","i love leetcode"],[5,3,2,2]],["i"],
                [" "],
                ["a"],
                ["#"],
                ["i"],
                [" "],
                ["a"],
                ["#"],
                ["i"],
                [" "],
                ["a"],
                ["#"]
               ],
               [
                None,
                ["i love you","island","i love leetcode"],
                ["i love you","i love leetcode"],
                [],
                [],
                ["i love you","island","i love leetcode"],
                ["i love you","i love leetcode","i a"],
                ["i a"],
                [],
                ["i love you","island","i a"],
                ["i love you","i a","i love leetcode"],
                ["i a"],
                []
               ],
              ),
    ]
    tests = [ (["AutocompleteSystem","input","input","input","input","input","input","input","input","input","input","input","input"],
               [
                [["i love you","island","iroman","i love leetcode"],[5,3,2,2]],["i"],
                [" "],
                ["a"],
                ["#"],
                ["i"],
                [" "],
                ["a"],
                ["#"],
                ["i"],
                [" "],
                ["a"],
                ["#"]
               ],
               [
                None,
                ["i love you","island","i love leetcode"],
                ["i love you","i love leetcode"],
                [],
                [],
                ["i love you","island","i love leetcode"],
                ["i love you","i love leetcode","i a"],
                ["i a"],
                [],
                ["i love you","island","i a"],
                ["i love you","i a","i love leetcode"],
                ["i a"],
                []
               ],
              ),
           ]
    for test in tests:
        for method in ['brute-force', 'sub-optimal', 'optimal']:
            if method == 'brute-force':
                AutocompleteSystem = AutocompleteSystem1
            elif method == 'sub-optimal':
                AutocompleteSystem = AutocompleteSystem2
            elif method == 'optimal':
                AutocompleteSystem = AutocompleteSystem3

            operations, inputs, ans = test[0], test[1], test[2]
            outputs = []

            for operation, input in zip(operations, inputs):
                if operation == "AutocompleteSystem":
                    sol = AutocompleteSystem(input[0], input[1])
                    output = None
                elif operation == "input":
                    output = sol.input(input[0])
                print(f"{operation}({input}): {output}")
                outputs.append(output)

            print(f"\nOperations = {operations}")
            print(f"inputs = {inputs}")
            print(f"outputs = {outputs}")
            success = (ans==outputs)
            print(f"Pass: {success}")
            if not success:
                print("Failed")
                return

run_AutocompleteSystem()