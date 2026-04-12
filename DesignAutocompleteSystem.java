// Time Complexity : Insert - O(L) , Input - O(1)
// Space Complexity : O(N * L), N = number of sentences, L = length of the word
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
/*
We have a hashmap to first store the input strings along with their frequencies.Now, we maintain a trie to
construct these strings along with the top 3 strings at each character of the string by sorting the strings
using map and strings' frequencies. If same freq, we return as per lexicographical order.This way, we make sure
to have only top 3 sentences at each character and remove extra ones.Now, when user presses a letter, we got the
trie node and get that letter's node and directly return the top 3 list. If user presses #, we store that new
word in the map and in trie.
 */
class AutocompleteSystem {
    TrieNode root;
    String search;
    Map<String, Integer> map;

    class TrieNode {
        TrieNode[] children;
        List<String> topPrefixList;

        public TrieNode() {
            this.children = new TrieNode[256];
            this.topPrefixList = new ArrayList<>();
        }
    }

    private void insert(String word) {
        TrieNode curr = root;

        for(int ch : word.toCharArray()) {
            if(curr.children[ch - ' '] == null) {
                curr.children[ch - ' '] = new TrieNode();
            }
            curr = curr.children[ch - ' '];

            List<String> topList = curr.topPrefixList;
            if(!topList.contains(word)) {
                topList.add(word);
            }

            Collections.sort(topList, (a, b) -> {
                if(map.get(a) == map.get(b))
                    return a.compareTo(b);
                else
                    return map.get(b) - map.get(a);
            });

            if(topList.size() > 3)
                topList.remove(topList.size() - 1);
        }
    }

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.root = new TrieNode();
        this.map = new HashMap<>();
        this.search = "";

        for(int i = 0 ; i < sentences.length ; i++) {
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
            insert(sentences[i]);
        }
    }

    private List<String> searchPrefix(String searchString) {
        TrieNode curr = root;
        for(char ch : searchString.toCharArray()) {
            if(curr.children[ch - ' '] == null)
                return new ArrayList<>();
            curr = curr.children[ch - ' '];
        }
        return curr.topPrefixList;
    }

    public List<String> input(char c) {
        if(c == '#') {
            map.put(search, map.getOrDefault(search, 0) + 1);
            insert(search);
            search = "";
            return new ArrayList<>();
        }
        search += c;
        return searchPrefix(search);
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */