import java.util.*;
// Time Complexity : O(nk) where n is length of the sentence and k is the number of sentences.
// Space Complexity : O(nk) where n is the length of the sentence and k is the number of sentences.
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
class AutocompleteSystem {
    class TrieNode {
        boolean isEnd;
        List<String> li; // store the strings with the same prefix
        Map<Character, TrieNode> children;

        public TrieNode() {
            li = new ArrayList();
            children = new HashMap();
        }
    }

    TrieNode root;

    public void insert(String sentence) {
        TrieNode curr = root;
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
            if (!curr.li.contains(sentence))
                curr.li.add(sentence);
            List<String> currLi = curr.li;
            Collections.sort(currLi, (a, b) -> {
                if (map.get(a) == map.get(b))
                    return a.compareTo(b);
                return map.get(b) - map.get(a);
            });
            if (currLi.size() > 3) {
                currLi.remove(curr.li.size() - 1);
            }
        }
        curr.isEnd = true;
    }

    public List<String> searchPrefix(String search) {
        TrieNode curr = root;
        for (int i = 0; i < search.length(); i++) {
            char c = search.charAt(i);
            if (!curr.children.containsKey(c))
                return new ArrayList();
            curr = curr.children.get(c);
        }
        return curr.li;
    }

    Map<String, Integer> map;
    StringBuilder sb;

    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap();
        sb = new StringBuilder();
        root = new TrieNode();
        // insert the data into Map and Trie
        for (int i = 0; i < sentences.length; i++) {
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
            insert(sentences[i]);
        }
    }

    public List<String> input(char c) {
        // # case
        if (c == '#') {
            String search = sb.toString();
            map.put(search, map.getOrDefault(search, 0) + 1);
            insert(search);
            sb = new StringBuilder();
            return new ArrayList();
        }
        sb.append(c);
        String search = sb.toString();
        return searchPrefix(search);
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */