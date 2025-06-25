// Time Complexity : O(N * L + M * L * log K)
//   - N is the number of sentences initially inserted
//   - L is the average length of sentences
//   - M is the number of input characters typed by the user
//   - K is the number of top results maintained (3 here, so log K is constant)
// Space Complexity : O(N * L), for storing all nodes in the Trie and map
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
// Approach :
//   - Use a Trie data structure where each node keeps a list of top 3 autocomplete suggestions.
//   - Maintain a HashMap to track the frequency of each sentence for ranking.
//   - On input:
//       - If the input is '#', update the map and Trie with the complete sentence.
//       - Otherwise, search the Trie based on the current prefix and return the top 3 matches.
//   - Trie insertions maintain the top 3 sorted list by frequency and lexicographical order.
//   - This allows efficient prefix search and hot sentence suggestion in real time.

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DesignAutocompleteSystem {
// HashMap<String, Integer> map;
    // String search;

    // public AutocompleteSystem(String[] sentences, int[] times) {
    //     this.search = "";
    //     this.map = new HashMap<>();
    //     for(int i = 0; i < sentences.length; i++) {
    //         map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
    //     }
    // }

    // public List<String> input(char c) {
    //     if(c == '#') {
    //         map.put(search, map.getOrDefault(search, 0) + 1);
    //         search = "";
    //         return new ArrayList<>();
    //     }

    //     search += c;

    //     PriorityQueue<String> pq = new PriorityQueue<>((a,b) -> {
    //         if(map.get(a) == map.get(b)) {
    //             return b.compareTo(a);  //lexiographically superior one i.e. smaller should stay in bottom of heap, because we are going to remove str if size gets greater than 3, hence, for same freq, keep str in desc order
    //         }
    //         return map.get(a) - map.get(b); //min heap so asc order
    //     });

    //     for(String sentence: map.keySet()) {
    //         if(sentence.startsWith(search)) {
    //             pq.add(sentence);
    //             if(pq.size() > 3) {
    //                 pq.poll();
    //             }
    //         }
    //     }

    //     List<String> result = new ArrayList<>();
    //     while(!pq.isEmpty()) {
    //         result.add(pq.poll());
    //     }

    //     Collections.reverse(result);
    //     return result;
    // }



    HashMap<String, Integer> map;
    StringBuilder search;
    TrieNode root;

    class TrieNode {
        TrieNode[] children;
        List<String> top3Results;

        public TrieNode() {
            this.children = new TrieNode[256]; // assuming ASCII characters
            this.top3Results = new ArrayList<>();
        }
    }

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map = new HashMap<>();
        this.search = new StringBuilder();
        this.root = new TrieNode();

        for (int i = 0; i < sentences.length; i++) {
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
            insert(sentences[i]);
        }
    }

    private void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            if (curr.children[c - ' '] == null) {
                curr.children[c - ' '] = new TrieNode();
            }
            curr = curr.children[c - ' '];

            if (!curr.top3Results.contains(word)) {
                curr.top3Results.add(word);
            }

            Collections.sort(curr.top3Results, (a, b) -> {
                int freqA = map.get(a);
                int freqB = map.get(b);
                if (freqA == freqB) return a.compareTo(b);
                return freqB - freqA;
            });

            // keep only top 3
            if (curr.top3Results.size() > 3) {
                curr.top3Results.remove(curr.top3Results.size() - 1);
            }
        }
    }

    private List<String> searchPrefix(String prefix) {
        TrieNode curr = root;
        for (char c : prefix.toCharArray()) {
            if (curr.children[c - ' '] == null) return new ArrayList<>();
            curr = curr.children[c - ' '];
        }
        return curr.top3Results;
    }

    public List<String> input(char c) {
        if (c == '#') {
            String completed = search.toString();
            map.put(completed, map.getOrDefault(completed, 0) + 1);
            insert(completed);
            search = new StringBuilder(); // reset
            return new ArrayList<>();
        }

        search.append(c);
        return searchPrefix(search.toString());
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
