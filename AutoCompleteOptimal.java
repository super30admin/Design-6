/*
* Did this code successfully run on Leetcode : YES
* 
* Any problem you faced while coding this : NO
* 
* Time Complexity: O(1)
* 
* Space Complexity: O(1)
* 
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class AutoCompleteOptimal {
    class TrieNode {
        List<String> sentencesStored;
        HashMap<Character, TrieNode> children;

        public TrieNode() {
            this.sentencesStored = new ArrayList<>();
            this.children = new HashMap<>();
        }
    };

    TrieNode root;

    HashMap<String, Integer> hmap;

    StringBuilder input;

    public AutoCompleteOptimal(String[] sentences, int[] times) {
        int n = sentences.length;

        this.hmap = new HashMap<>();

        this.root = new TrieNode();

        for (int index = 0; index < n; index++) {
            hmap.put(sentences[index],
                    hmap.getOrDefault(sentences[index], 0)
                            + times[index]);
            insertIntoTrie(sentences[index]);
        }

        this.input = new StringBuilder();
    }

    private void insertIntoTrie(String sentence) {
        int n = sentence.length();

        TrieNode curr = root;

        for (int index = 0; index < n; index++) {
            char ch = sentence.charAt(index);

            if (!curr.children.containsKey(ch)) {
                curr.children.put(ch, new TrieNode());
            }

            List<String> li = curr.children.get(ch).sentencesStored;

            if (!li.contains(sentence)) {
                li.add(sentence);
            }

            // figure out the hot sentences while adding
            Collections.sort(li, (a, b) -> {
                int countA = hmap.get(a);
                int countB = hmap.get(b);

                if (countA == countB) {
                    return a.compareTo(b);
                }

                return countB - countA;
            });

            if (li.size() > 3) {
                li.remove(li.size() - 1);
            }

            curr = curr.children.get(ch);
        }
    }

    private List<String> getPrefixMatchesFromTrie() {
        TrieNode curr = root;

        String inputStr = input.toString();

        for (int index = 0; index < inputStr.length(); index++) {
            char ch = input.charAt(index);

            if (!curr.children.containsKey(ch)) {
                return new ArrayList<>();
            }

            curr = curr.children.get(ch);
        }

        return curr.sentencesStored;
    }

    private List<String> getHotSentences() {
        return getPrefixMatchesFromTrie();
    }

    private void clearInput() {
        String inputStr = input.toString();

        hmap.put(inputStr, hmap.getOrDefault(inputStr, 0) + 1);

        insertIntoTrie(inputStr);

        input = new StringBuilder();
    }

    public List<String> input(char c) {
        if (c == '#') {
            clearInput();

            return new ArrayList<>();
        } else {
            input.append(c);

            return getHotSentences();
        }
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */