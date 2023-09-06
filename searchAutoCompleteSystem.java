// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
/*
 * 1. Store all the sentences and their frequencies in a hashmap and sentences in a trie.
 * 2. For every input, search the trie and return the top 3 sentences.
 * 3. If the input is '#', add the sentence to the trie and hashmap.
 * 4. While adding the sentence to the trie, update the top 3 sentences in the trie. The top 3 results are put in a list and sorted based on the frequency and the sentence.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class AutocompleteSystem {
    private HashMap<String, Integer> map;
    private String search;
    private TrieNode root;
    
    class TrieNode{
        TrieNode[] children;
        List<String> topResults;
        public TrieNode(){
            this.children = new TrieNode[256];
            this.topResults = new ArrayList<>();
        }   
    }

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.root = new TrieNode();
        this.map = new HashMap<>();
        this.search = "";
        for(int i=0; i<sentences.length; i++){
            String sentence = sentences[i];
            int freq = times[i];
            map.put(sentence, map.getOrDefault(sentence, 0) + freq);
            insert(sentence);
        }
    }

    private void insert(String word){
        TrieNode curr = root;
        for(char ch : word.toCharArray()){
            if(curr.children[ch - ' '] == null)
                curr.children[ch - ' '] = new TrieNode();

            curr = curr.children[ch - ' '];
            List<String> list = curr.topResults;
            if(!list.contains(word))
                list.add(word);

            Collections.sort(list, (String a, String b) -> {
                if(map.get(a) == map.get(b)){
                    return a.compareTo(b);
                }

                return map.get(b) - map.get(a);
            });
            if(list.size() > 3){
                list.remove(list.size()-1);
            }
        }
    }

    private List<String> startsWith(String word){
        TrieNode curr = root;
        for(char ch : word.toCharArray()){
            if(curr.children[ch - ' '] == null){
                return new ArrayList<>();
            }
            curr = curr.children[ch - ' '];
        }
        return curr.topResults;
    }
    
    public List<String> input(char c) {
        if(c == '#'){
            map.put(search, map.getOrDefault(search, 0) + 1);
            insert(search);
            this.search = "";
            return new ArrayList<>();
        }

        this.search += c;
        return startsWith(search);
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */