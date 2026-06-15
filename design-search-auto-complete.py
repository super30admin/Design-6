class TrieNode:
    def __init__(self):
        self.child = collections.defaultdict(TrieNode)
        self.isWord = False
        self.word = ""
        self.times = 0
        
class Trie:
    def __init__(self):
        self.root = TrieNode()
    
    def insert(self, sentence, times):
        cur = self.root
        for char in sentence:
            cur = cur.child[char]
        if not cur.isWord:
            cur.isWord = True
            cur.word = sentence
            # self.trie.insert("i love you", 5)
            cur.times = times if times is not None else 1
        # this exact sentence has already been inserted before
        else:
            cur.times += times if times is not None else 1
        
    def search(self, inpt):
        res = []
        dic = {}
        cur = self.root
        # get cur to the correct child (end position that matches the input)
        for c in inpt:
            # example: 
            # --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a05e1550> ---
            # self.isWord = False
            # self.word   = ''
            # self.times  = 0
            # self.child  = {'i': <__main__.TrieNode object at 0x7f50a05d8a50>}
                #         Entering dictionary key 'i':
                # --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a05d8a50> ---
                # self.isWord = False
                # self.word   = ''
                # self.times  = 0
                # self.child  = {' ': <__main__.TrieNode object at 0x7f50a05d9950>, 's': <__main__.TrieNode object at 0x7f50a08a65d0>, 'r': <__main__.TrieNode object at 0x7f50a087d910>}

            if c not in cur.child:
                return []
            cur = cur.child[c]
            
        # then explore from that determined child, trying to find words
        stack = []
        stack.append(cur)
        while stack:
            node = stack.pop()
            if node.isWord:
                dic[node.word] = node.times # store the found word and number of times in a dict for our output
            for ch in node.child:
                n = node.child[ch]
                stack.append(n)

        # get the top 3 items
        i = 3
        for k,v in sorted(dic.items(), key=lambda x:(-x[1],x[0]) ):
        	# -x[1] means sort by frequency descending (highest score first)
        	# x[0] means if there's a tie in frequency, sort alphabetically.
            if i ==0:
                break
            res.append(k)
            i-=1
        return res
    
    def print_literal_raw(self):
        """Prints the absolute raw memory representation of the Trie."""
        def dump(node, level=0):
            indent = "    " * level
            print(f"{indent}--- TrieNode Memory Location: {node} ---")
            print(f"{indent}self.isWord = {node.isWord}")
            print(f"{indent}self.word   = '{node.word}'")
            print(f"{indent}self.times  = {node.times}")
            
            # This shows the actual self.child dictionary contents
            print(f"{indent}self.child  = {dict(node.child)}") 
            
            # Recursively print the nested objects inside the dictionary
            for char, child_node in node.child.items():
                print(f"\n{indent}👉 Entering dictionary key '{char}':")
                dump(child_node, level + 1)

        print("\n================== ABSOLUTE RAW DATA STRUCTURE ==================")
        dump(self.root)
        print("=================================================================\n")
        
        
class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        self.inp = ""
        self.trie = Trie()
        for sentence,time in zip(sentences,times):
            self.trie.insert(sentence, time)
        #self.trie.print_literal_raw()

    def input(self, c: str) -> List[str]:
        if c == "#":
            self.trie.insert(self.inp, None)
            self.inp = ""
            return []
        self.inp+=c
        return self.trie.search(self.inp)


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)





🚀 STARTING SEARCH FOR PREFIX: 'i'
📍 Anchored at node representing the end of prefix 'i'. Initializing stack.

--- 🔄 DFS STEP 1 ---
📚 Current Stack: ["Node(Keys:[' ', 's', 'r'], Word:'')"]
💥 POPPED: Node(Keys:[' ', 's', 'r'], Word:'')
➡️ Pushing children nodes onto stack for characters: [' ', 's', 'r']

--- 🔄 DFS STEP 2 ---
📚 Current Stack: ["Node(Keys:['l'], Word:'')", "Node(Keys:['l'], Word:'')", "Node(Keys:['o'], Word:'')"]
💥 POPPED: Node(Keys:['o'], Word:'')
➡️ Pushing children nodes onto stack for characters: ['o']

--- 🔄 DFS STEP 3 ---
📚 Current Stack: ["Node(Keys:['l'], Word:'')", "Node(Keys:['l'], Word:'')", "Node(Keys:['m'], Word:'')"]
💥 POPPED: Node(Keys:['m'], Word:'')
➡️ Pushing children nodes onto stack for characters: ['m']

--- 🔄 DFS STEP 4 ---
📚 Current Stack: ["Node(Keys:['l'], Word:'')", "Node(Keys:['l'], Word:'')", "Node(Keys:['a'], Word:'')"]
💥 POPPED: Node(Keys:['a'], Word:'')
➡️ Pushing children nodes onto stack for characters: ['a']

--- 🔄 DFS STEP 5 ---
📚 Current Stack: ["Node(Keys:['l'], Word:'')", "Node(Keys:['l'], Word:'')", "Node(Keys:['n'], Word:'')"]
💥 POPPED: Node(Keys:['n'], Word:'')
➡️ Pushing children nodes onto stack for characters: ['n']






================== ABSOLUTE RAW DATA STRUCTURE ==================
--- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a05e1550> ---
self.isWord = False
self.word   = ''
self.times  = 0
self.child  = {'i': <__main__.TrieNode object at 0x7f50a05d8a50>}

👉 Entering dictionary key 'i':
    --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a05d8a50> ---
    self.isWord = False
    self.word   = ''
    self.times  = 0
    self.child  = {' ': <__main__.TrieNode object at 0x7f50a05d9950>, 's': <__main__.TrieNode object at 0x7f50a08a65d0>, 'r': <__main__.TrieNode object at 0x7f50a087d910>}

    👉 Entering dictionary key ' ':
        --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a05d9950> ---
        self.isWord = False
        self.word   = ''
        self.times  = 0
        self.child  = {'l': <__main__.TrieNode object at 0x7f50a0897ce0>}

        👉 Entering dictionary key 'l':
            --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a0897ce0> ---
            self.isWord = False
            self.word   = ''
            self.times  = 0
            self.child  = {'o': <__main__.TrieNode object at 0x7f50a05f8180>}

            👉 Entering dictionary key 'o':
                --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a05f8180> ---
                self.isWord = False
                self.word   = ''
                self.times  = 0
                self.child  = {'v': <__main__.TrieNode object at 0x7f50a05dc5f0>}

                👉 Entering dictionary key 'v':
                    --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a05dc5f0> ---
                    self.isWord = False
                    self.word   = ''
                    self.times  = 0
                    self.child  = {'e': <__main__.TrieNode object at 0x7f50a05a59d0>}

                    👉 Entering dictionary key 'e':
                        --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a05a59d0> ---
                        self.isWord = False
                        self.word   = ''
                        self.times  = 0
                        self.child  = {' ': <__main__.TrieNode object at 0x7f50a05a5ae0>}

                        👉 Entering dictionary key ' ':
                            --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a05a5ae0> ---
                            self.isWord = False
                            self.word   = ''
                            self.times  = 0
                            self.child  = {'y': <__main__.TrieNode object at 0x7f50a05b4e50>, 'l': <__main__.TrieNode object at 0x7f50a0836450>}

                            👉 Entering dictionary key 'y':
                                --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a05b4e50> ---
                                self.isWord = False
                                self.word   = ''
                                self.times  = 0
                                self.child  = {'o': <__main__.TrieNode object at 0x7f50a05b5250>}

                                👉 Entering dictionary key 'o':
                                    --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a05b5250> ---
                                    self.isWord = False
                                    self.word   = ''
                                    self.times  = 0
                                    self.child  = {'u': <__main__.TrieNode object at 0x7f50a08a68a0>}

                                    👉 Entering dictionary key 'u':
                                        --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a08a68a0> ---
                                        self.isWord = True
                                        self.word   = 'i love you'
                                        self.times  = 5
                                        self.child  = {}

                            👉 Entering dictionary key 'l':
                                --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a0836450> ---
                                self.isWord = False
                                self.word   = ''
                                self.times  = 0
                                self.child  = {'e': <__main__.TrieNode object at 0x7f50a05eb950>}

                                👉 Entering dictionary key 'e':
                                    --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a05eb950> ---
                                    self.isWord = False
                                    self.word   = ''
                                    self.times  = 0
                                    self.child  = {'e': <__main__.TrieNode object at 0x7f50a05eba50>}

                                    👉 Entering dictionary key 'e':
                                        --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a05eba50> ---
                                        self.isWord = False
                                        self.word   = ''
                                        self.times  = 0
                                        self.child  = {'t': <__main__.TrieNode object at 0x7f50a05d0980>}

                                        👉 Entering dictionary key 't':
                                            --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a05d0980> ---
                                            self.isWord = False
                                            self.word   = ''
                                            self.times  = 0
                                            self.child  = {'c': <__main__.TrieNode object at 0x7f50a05d01a0>}

                                            👉 Entering dictionary key 'c':
                                                --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a05d01a0> ---
                                                self.isWord = False
                                                self.word   = ''
                                                self.times  = 0
                                                self.child  = {'o': <__main__.TrieNode object at 0x7f50a05d0360>}

                                                👉 Entering dictionary key 'o':
                                                    --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a05d0360> ---
                                                    self.isWord = False
                                                    self.word   = ''
                                                    self.times  = 0
                                                    self.child  = {'d': <__main__.TrieNode object at 0x7f50a05d03d0>}

                                                    👉 Entering dictionary key 'd':
                                                        --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a05d03d0> ---
                                                        self.isWord = False
                                                        self.word   = ''
                                                        self.times  = 0
                                                        self.child  = {'e': <__main__.TrieNode object at 0x7f50a05d0440>}

                                                        👉 Entering dictionary key 'e':
                                                            --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a05d0440> ---
                                                            self.isWord = True
                                                            self.word   = 'i love leetcode'
                                                            self.times  = 2
                                                            self.child  = {}

    👉 Entering dictionary key 's':
        --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a08a65d0> ---
        self.isWord = False
        self.word   = ''
        self.times  = 0
        self.child  = {'l': <__main__.TrieNode object at 0x7f50a060e0b0>}

        👉 Entering dictionary key 'l':
            --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a060e0b0> ---
            self.isWord = False
            self.word   = ''
            self.times  = 0
            self.child  = {'a': <__main__.TrieNode object at 0x7f50a060fcb0>}

            👉 Entering dictionary key 'a':
                --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a060fcb0> ---
                self.isWord = False
                self.word   = ''
                self.times  = 0
                self.child  = {'n': <__main__.TrieNode object at 0x7f50a058f110>}

                👉 Entering dictionary key 'n':
                    --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a058f110> ---
                    self.isWord = False
                    self.word   = ''
                    self.times  = 0
                    self.child  = {'d': <__main__.TrieNode object at 0x7f50a087d3d0>}

                    👉 Entering dictionary key 'd':
                        --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a087d3d0> ---
                        self.isWord = True
                        self.word   = 'island'
                        self.times  = 3
                        self.child  = {}

    👉 Entering dictionary key 'r':
        --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a087d910> ---
        self.isWord = False
        self.word   = ''
        self.times  = 0
        self.child  = {'o': <__main__.TrieNode object at 0x7f50a06331d0>}

        👉 Entering dictionary key 'o':
            --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a06331d0> ---
            self.isWord = False
            self.word   = ''
            self.times  = 0
            self.child  = {'m': <__main__.TrieNode object at 0x7f50a0633280>}

            👉 Entering dictionary key 'm':
                --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a0633280> ---
                self.isWord = False
                self.word   = ''
                self.times  = 0
                self.child  = {'a': <__main__.TrieNode object at 0x7f50a083f070>}

                👉 Entering dictionary key 'a':
                    --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a083f070> ---
                    self.isWord = False
                    self.word   = ''
                    self.times  = 0
                    self.child  = {'n': <__main__.TrieNode object at 0x7f50a0604550>}

                    👉 Entering dictionary key 'n':
                        --- TrieNode Memory Location: <__main__.TrieNode object at 0x7f50a0604550> ---
                        self.isWord = True
                        self.word   = 'iroman'
                        self.times  = 2
                        self.child  = {}
=================================================================




RAW TRIE STRUCTURE:
 {
    "i": {
        " ": {
            "l": {
                "o": {
                    "v": {
                        "e": {
                            " ": {
                                "y": {
                                    "o": {
                                        "u": {
                                            "WORD": "i love you",
                                            "TIMES": "5"
                                        }
                                    }
                                },
                                "l": {
                                    "e": {
                                        "e": {
                                            "t": {
                                                "c": {
                                                    "o": {
                                                        "d": {
                                                            "e": {
                                                                "WORD": "i love leetcode",
                                                                "TIMES": "2"
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        },
        "s": {
            "l": {
                "a": {
                    "n": {
                        "d": {
                            "WORD": "island",
                            "TIMES": "3"
                        }
                    }
                }
            }
        },
        "r": {
            "o": {
                "m": {
                    "a": {
                        "n": {
                            "WORD": "iroman",
                            "TIMES": "2"
                        }
                    }
                }
            }
        }
    }
}










--- TRIE STRUCTURE ---
└── 'ROOT'
  └── 'i'
    └── ' '
      └── 'l'
        └── 'o'
          └── 'v'
            └── 'e'
              └── ' '
                └── 'y'
                  └── 'o'
                    └── 'u' -> [WORD: 'i love you' (Times: 5)]
                └── 'l'
                  └── 'e'
                    └── 'e'
                      └── 't'
                        └── 'c'
                          └── 'o'
                            └── 'd'
                              └── 'e' -> [WORD: 'i love leetcode' (Times: 2)]
    └── 's'
      └── 'l'
        └── 'a'
          └── 'n'
            └── 'd' -> [WORD: 'island' (Times: 3)]
    └── 'r'
      └── 'o'
        └── 'm'
          └── 'a'
            └── 'n' -> [WORD: 'iroman' (Times: 2)]
----------------------
