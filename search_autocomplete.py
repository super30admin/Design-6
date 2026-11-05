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
 We keep track of all sentences and their counts using a map. When a character is typed, we find all matching sentences that start with the current prefix
 and store them in a list. We sort the list to keep only the top 3 sentences (top defined by frequency of occurrence). If two sentences have the same frequency, then they are arranged lexicographical order (smaller lexicographical appears before greater).

 Time: O(N*logN) per input call, N = no. of sentences in the map that start with the user input prefix, N log N for sorting N sentences
 Space: O(M), M = length of the map (no. of sentences in the map)

 https://youtu.be/klpDIfsnhXk?t=1982
 https://youtu.be/klpDIfsnhXk?t=2971 (problem with this approach: scan through the entire map for each input character typed)

2. Sub-Optimal (Trie + Hash map + Priority Queue)
We use a Trie where each node stores a map where the key is a sentence starting with the input prefix and value = freq count. When a character is typed, we search the Trie for the node containing the prefix, get the map from that node to get the sentences and freq counts and use a min-heap to keep the top 3 based on frequency and then lexicographical order. Finally, we reverse the heap output to return the most relevant 3 suggestions. The problem with this approach is that a Trie node could have a very large hash map. Eg. input prefix = "a", the hash map of this node will contain all the sentences starting with "a"!!

Time: O(P + N log K + K) per input call, where K = 3 (top k), P = length of prefix. But K = const. Hence, O(P + N log 3 + 3) = O(N), N  = total number of sentences starting with the input prefix.
Space: O(M^2 * L^2) (due to storing many sentences in many prefix nodes)

https://youtu.be/klpDIfsnhXk?t=3046

3. Optimal (Trie + Hash map + Priority Queue)
We build a Trie where each node keeps track of only the top 3 hot sentences seen so far. Thus, every prefix Trie node stores the top 3 sentences using a list (instead of hash map as in the sub-optimal solution). We also maintain a single universal map to track the sentence and the frequency count.
It is possible that a list associated with the prefix trie node might have a sentence that falls in top-3 list but later after multiple input queries is replaced by a new sentence not in the list. This is where the universal map
helps in updating the list (replace old sentence with lower freq by a new sentence with higher freq). When typing a character, we walk down the Trie and simply return the node's top 3 list. If we hit #, we record the full sentence and update the Trie with it.

Time:
Insert(word): O(L * log 3) = O(L), where L = length of word. Sorting 3 elements is constant.
Input(char): O(P) = O(1), where P = length of current prefix

Space:
O(ML) (for hash map), where M = number of unique sentences and L = average length of word

https://youtu.be/klpDIfsnhXk?t=4415


'''
from typing import List
from collections import defaultdict
import heapq
from copy import deepcopy as dcp

class FreqSentence(object):
    '''
        This class is used as an element in the heap
        that stores the sentence and the freq count
        of the sentence
    '''
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
        print(f"Initiating AutocompleteSystem - 1 ...")
        N = len(sentences)
        self.map = defaultdict(int)
        self.buffer = [] # character buffer
        self.topk = 3
        for i in range(N):
            sentence = sentences[i]
            self.map[sentence] += times[i]

    def input(self, c: str) -> List[str]:
        if c == '#':
            sentence = "".join(self.buffer)
            self.map[sentence] += 1
            self.buffer = []
            return []
        else:
            self.buffer.append(c)
            prefix = "".join(self.buffer)
            candidates = []
            # iterate through the map to find
            # which sentences start with our prefix
            for sent in self.map: # O(M)
                if sent.startswith(prefix):
                    candidates.append((self.map[sent],sent))
            # sort by descending order of counts
            # if counts are equal, sort by ascending order of characters
            candidates = sorted(candidates, key=lambda x: (-x[0], x[1])) # N log N
            le = min(len(candidates), self.topk)
            candidates = candidates[:le]
            candidates = [item[1] for item in candidates]
            return candidates

class AutocompleteSystem2:
    class TrieNode:
        def __init__(self):
            self.children = [None]*256
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
                index = ord(c)
                if not curr.children[index]:
                    curr.children[index] = AutocompleteSystem2.TrieNode()
                curr = curr.children[index]
                curr.map[sentence] += frequency
            curr.isEOS = True

        def startsWith(self, prefix):
            ''' return the map of the trie node which contains the prefix
                map contains all sentences starting with prefix
            '''
            curr = self.root
            if not curr:
                return None
            for c in prefix:
                index = ord(c)
                if not curr.children[index]:
                    return {} # empty hash map
                curr = curr.children[index]
            return curr.map

    def __init__(self, sentences: List[str], times: List[int]):
        print(f"Initiating AutocompleteSystem - 2 ...")
        N = len(sentences)
        self.trie = AutocompleteSystem2.Trie()
        self.buffer = [] # character buffer
        self.topk = 3
        for i in range(N):
            sentence = sentences[i]
            frequency = times[i]
            self.trie.insert(sentence, frequency)

    def input(self, c: str) -> List[str]:
        if c == '#':
            sentence = "".join(self.buffer)
            map = self.trie.startsWith(sentence)
            if not map:
                self.trie.insert(sentence, 1)
            else:
                self.trie.insert(sentence, map[sentence])
            self.buffer = []
            return []
        else:
            self.buffer.append(c)
            prefix = "".join(self.buffer)
            # get the map by traversing
            # the prefix in the trie
            map = self.trie.startsWith(prefix) # O(P), P = len(prefix)
            candidates = []
            for sentence in map: # N log K, N = len(map)
                if len(candidates) < self.topk:
                    heapq.heappush(candidates, FreqSentence(map[sentence], sentence))
                else:
                    heapq.heappushpop(candidates, FreqSentence(map[sentence], sentence))
            le = min(len(candidates), self.topk)
            result = []
            for _ in range(le): # O(K)
                pop = heapq.heappop(candidates)
                result.append(pop.sentence)
            return result[::-1]

class AutocompleteSystem3:
    class TrieNode:
        def __init__(self):
            self.children = [None]*256
            self.pq = [] # list to store topk strings
            self.isEOS = False

    class Trie:
        def __init__(self):
            self.root = AutocompleteSystem3.TrieNode()

        def insert(self, sentence, frequency_map, topk):
            curr = self.root
            if not curr:
                return None
            for c in sentence: # O(L), L = len(sentence)
                index = ord(c)
                if not curr.children[index]:
                    curr.children[index] = AutocompleteSystem3.TrieNode()
                curr = curr.children[index]
                li = curr.pq # li is a reference to curr.pq
                if sentence not in li:
                    li.append(sentence)
                li.sort(key=lambda x: (-frequency_map[x], x)) # O(1) since topk = 3, sorting 3 elements is constant time
                curr.pq = li[:topk] # slicing creates a new list, original ref is lost, hence curr.pq is updated to the new list
            curr.isEOS = True

        def startsWith(self, prefix):
            ''' return the list of words in trie which contains prefix
            '''
            curr = self.root
            if not curr:
                return None
            for c in prefix: # O(P), P = len(prefix)
                index = ord(c)
                if not curr.children[index]:
                    return [] # empty list
                curr = curr.children[index]
            return curr.pq # list

    def __init__(self, sentences: List[str], times: List[int]):
        print(f"Initiating AutocompleteSystem - 3 ...")
        N = len(sentences)
        self.trie = AutocompleteSystem3.Trie()
        self.map = defaultdict(int) # global map storing all sentences and freq counts
        self.buffer = [] # character buffer
        self.topk = 3
        for i in range(N):
            sentence = sentences[i]
            frequency = times[i]
            self.map[sentence] += frequency
            self.trie.insert(sentence, self.map, self.topk)

    def input(self, c: str) -> List[str]:
        if c == '#':
            sentence = "".join(self.buffer)
            self.map[sentence] += 1
            self.trie.insert(sentence, self.map, self.topk)
            self.buffer = []
            return []
        else:
            self.buffer.append(c)
            prefix = "".join(self.buffer)
            # get the topk list by traversing
            # the prefix in the trie
            result = self.trie.startsWith(prefix) # O(P), P = len(prefix)
            return result

def run_AutocompleteSystem():
    tests = [  # test 1
               ( ["AutocompleteSystem", "input", "input", "input", "input"],
                [
                 [["i love you", "island", "ironman", "i love leetcode"], [5, 3,2,2]],
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
                [["i love you","island","ironman","i love leetcode"],[5,3,2,2]],["i"],
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

              # test 3: Verify ASCII Tie-Breaking logic with identical frequencies
                (
                    ["AutocompleteSystem", "input", "input", "input"],
                    [
                        [["car", "cat", "cab"], [10, 10, 10]],  # All have identical frequency
                        ["c"],
                        ["a"],
                        ["#"]
                    ],
                    [
                        None,
                        ["cab", "car", "cat"],  # Must sort alphabetically: b -> r -> t
                        ["cab", "car", "cat"],
                        []
                    ]
                ),

              # test 4:  Dynamic hotness insertion of a brand new word via '#'
                (
                    ["AutocompleteSystem", "input", "input", "input", "input"],
                    [
                        [["apple", "apricot"], [5, 3]],
                        ["a"],
                        ["p"],
                        ["#"],  # User finalized 'ap#' -> 'ap' is now a new historic sentence with freq 1
                        ["a"]   # Checking if 'ap' shows up alongside 'apple' and 'apricot'
                    ],
                    [
                        None,
                        ["apple", "apricot"],
                        ["apple", "apricot"],
                        [],
                        ["apple", "apricot", "ap"]  # Rank order based on freq: 5, 3, 1
                    ]
                ),
    ]
    for test in tests:
        print(f"\n ---- Test Case ---- ")
        for method in ['brute-force', 'sub-optimal', 'optimal']:
            if method == 'brute-force':
                AutocompleteSystem = AutocompleteSystem1
            elif method == 'sub-optimal':
                AutocompleteSystem = AutocompleteSystem2
            elif method == 'optimal':
                AutocompleteSystem = AutocompleteSystem3

            operations, inputs, ans = test[0], test[1], test[2]
            outputs = []

            print(f"\nMethod: {method}")
            for operation, input in zip(operations, inputs):
                if operation == "AutocompleteSystem":
                    sol = AutocompleteSystem(input[0], input[1])
                    output = None
                elif operation == "input":
                    output = dcp(sol.input(input[0])) # create a deep copy of the output to avoid mutation issues
                outputs.append(output)
                print(f"{operation}({input}): {output}")

            passed = (ans == outputs)
            assert passed == True, f"Test case failed"
            print(f"Pass: {passed}")
    print(f"\nDONE")

run_AutocompleteSystem()