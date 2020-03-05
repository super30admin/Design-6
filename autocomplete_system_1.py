from collections import defaultdict
from typing import List


class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        """
            Time Complexity - O(mn)
            'm' is the average length of the string in the sentences list
            'n' is the number of sentences
            Space Complexity - O(n)
        """
        self.dic = defaultdict(int)
        for i in range(len(sentences)):
            self.dic[sentences[i]] += times[i]
        self.search = ''

    def input(self, c: str) -> List[str]:
        """
            Time Complexity - O(n+mlogm)
            'm' the number of strings in the result list
            'n' is the number of sentences
            Space Complexity - O(m)
        """
        result = []

        if c == '#':
            self.dic[self.search] += 1
            self.search = ''
            return result

        self.search += c
        for k, v in self.dic.items():
            if k.startswith(self.search):
                result.append((k, -v))
        result.sort(key=lambda x: (x[1], x[0]))
        return [result.pop(0)[0] for _ in range(min(len(result), 3))]


# Your AutocompleteSystem object will be instantiated and called as such:

if __name__ == '__main__':
    """
    ["AutocompleteSystem","input","input","input","input","input","input","input","input","input","input","input","input"]
[[,],["i"],[" "],["a"],["#"],["i"],[" "],["a"],["#"],["i"],[" "],["a"],["#"]]
    """
    obj = AutocompleteSystem(["i love you", "island", "iroman", "i love leetcode"], [5, 3, 2, 2])
    print(obj.input("i"))
    print(obj.input(" "))
    print(obj.input("a"))
    print(obj.input("#"))
    print(obj.input("i"))
    print(obj.input(" "))
    print(obj.input("a"))
    print(obj.input("#"))
    print(obj.input("i"))
    print(obj.input(" "))
    print(obj.input("a"))
    print(obj.input("#"))
    """
    ["i love you","island","i love leetcode"],
    ["i love you","i love leetcode"],
    [],
    []
    
    ["i love you","island","i love leetcode"],
    ["i love you","i love leetcode","i a"],
    ["i a"],
    []
    
    ["i love you","island","i a"],
    ["i love you","i a","i love leetcode"],
    ["i a"],
    []]
    """
