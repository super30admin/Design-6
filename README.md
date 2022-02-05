# Design-6

## Problem1 Design Phone Directory (https://leetcode.com/problems/design-phone-directory/)

class PhoneDirectory:

    def __init__(self, maxNumbers: int):
        self.available=deque()
        self.assigned=set()
        for i in range(maxNumbers):
            self.available.append(i)
    def get(self) -> int:
        if not self.available:
            return -1
        c=self.available.popleft()
        self.assigned.add(c)
        return c
        
    def check(self, number: int) -> bool:
        if number in self.assigned:
            return False
        return True
    def release(self, number: int) -> None:
        if number in self.assigned:
            self.assigned.remove(number)
            self.available.append(number)
        


# Your PhoneDirectory object will be instantiated and called as such:
# obj = PhoneDirectory(maxNumbers)
# param_1 = obj.get()
# param_2 = obj.check(number)
# obj.release(number)

## Problem2 Design Autocomplete System (https://leetcode.com/problems/design-search-autocomplete-system/)
class AutocompleteSystem:

    def __init__(self, sentences: List[str], times: List[int]):
        # create a trie
        self.root = {}
        self.degree = defaultdict(int)
        self.search = ""
        for i, word in enumerate(sentences):
            node = self.root
            self.degree[word] = times[i]
            for char in word:
                if char not in node:
                    node[char] = {}
                node = node[char]
            node['$'] = word

    def input(self, c: str) -> List[str]:
        if c == "#":
            word = self.search
            node = self.root
            for char in word:
                if char not in node:
                    node[char] = {}
                node = node[char]
            node['$'] = word
            self.degree[word] += 1
            self.search = ""
            return []
        else:
            word = self.search + c
            self.search = word
            node = self.root
            matched = []
            for char in word:
                if char not in node:
                    return []
                node = node[char]
            def helper(node):
                for key in node:
                    if '$' == key:
                        matched.append((-self.degree[node['$']], node['$']))
                    else:
                        helper(node[key])
            helper(node)
            matched.sort()
            self.search = word
            return [matched[i][1] for i in range(min(len(matched), 3))]


# Your AutocompleteSystem object will be instantiated and called as such:
# obj = AutocompleteSystem(sentences, times)
# param_1 = obj.input(c)