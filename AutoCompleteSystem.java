import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Time Complexity: O(n × L) where L is the average length of each words for autocomplete constructor
 * Space Complexity: O(n × L)
 * Did this code successfully run on Leetcode: Yes
 * Any problem you faced while coding this: None

 * Approach:
 * Using tries to capture words
 * Using HashMap to capture frequencies/hot degrees
 * Using StringBuilder to build words from input/prefixes
 */

class AutocompleteSystem {
    class TrieNode {

        TrieNode[] children;
        boolean isEnd;

        TrieNode() {
            this.children = new TrieNode[27];
            this.isEnd = false;
        }
    }

    TrieNode root;
    HashMap<String, Integer> hotDegree;
    StringBuilder currentInput;

    private void insert(String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            int index;
            if (ch == ' ') {
                index = 26;
            } else {
                index = ch - 'a';
            }
            
            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index];
        }

        curr.isEnd = true;
    }


    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        hotDegree = new HashMap<>();
        currentInput = new StringBuilder();
        for (int i = 0; i < sentences.length; i++) {
            insert(sentences[i]);
            hotDegree.put(sentences[i], times[i]);
        }
    }
    
    public List<String> input(char c) {
         if (c == '#') {
            String word = currentInput.toString();
            insert(word);
            hotDegree.put(word, hotDegree.getOrDefault(word, 0) + 1);
            currentInput = new StringBuilder();
            return new ArrayList<>();
         }

        currentInput.append(c);
        String prefix = currentInput.toString();
        List<String> result = new ArrayList<>();

        TrieNode curr = root;
        for (char ch : prefix.toCharArray()) {
            int index;
            if (ch == ' ') {
                index = 26;
            } else {
                index = ch - 'a';
            }
            
            if (curr.children[index] == null) return result;
            curr = curr.children[index];
        }

        dfs(curr, prefix, result);

        // Sort by hot degree
        result.sort((a, b) -> {
            int compare = hotDegree.get(b) - hotDegree.get(a);
            return compare == 0 ? a.compareTo(b) : compare;
        });

        return result.size() > 3 ? result.subList(0, 3) : result;
    }

    // Find all the words with this prefix in a dfs approach
    private void dfs(TrieNode node, String prefix, List<String> result) {
        // base
        if (node == null) return;

        if (node.isEnd && hotDegree.containsKey(prefix)) {
            result.add(prefix);
        }

        // logic
        for (int i = 0; i < 27; i++) {
            if (node.children[i] != null) {
                char nextChar;
                if (i == 26) {
                    nextChar = ' ';
                } else {
                    nextChar = (char) ('a' + i);
                }
                dfs(node.children[i], prefix + nextChar, result);
            }
        }
    } 
}