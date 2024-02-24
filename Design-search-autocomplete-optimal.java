/*
Time Complexity: O(1)

Space Complexity: O(1) - without the constructor space

Did this code successfully run on Leetcode : Yes

Approach: Using a Tire -> hashmap, map to store cache data. Doing the sorting while inserting the 
elements in the trie and returning hottest searches.

Prob: 642. Design Search Autocomplete System

*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


class AutocompleteSystem {
    class TrieNode{
        List<String> startsWith;
        HashMap<Character,TrieNode> children;
        public TrieNode(){
            this.startsWith = new ArrayList<>();
            this.children = new HashMap<>();
        }
    }

    public void insert(String sentence){
        TrieNode curr = root;
        for(int i=0;i<sentence.length();i++){
            char c = sentence.charAt(i);
            if(!curr.children.containsKey(c)){
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
            List<String> li = curr.startsWith;
            if(!li.contains(sentence)){
                li.add(sentence);
            }
            Collections.sort(li, (a,b) -> {
                int cnta = map.get(a);
                int cntb = map.get(b);
                if(cnta == cntb){
                    return a.compareTo(b);
                }
                return cntb-cnta;
            });
           if(li.size() > 3){
                li.remove(li.size()-1);
           }
        }
    }
    public List<String> search(String prefix){
        TrieNode curr = root;
        for(int i=0;i<prefix.length();i++){
            char c = prefix.charAt(i);
            if(!curr.children.containsKey(c)){
                return new ArrayList<>(); 
            }
            curr = curr.children.get(c);
        }
        return curr.startsWith;
    }
    private HashMap<String,Integer> map;
    private StringBuilder inputStr;
    private TrieNode root;
    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map = new HashMap<>();
        this.inputStr = new StringBuilder();
        this.root = new TrieNode();
        for(int i=0;i<sentences.length;i++){
            String sentence = sentences[i];
            int cnt = times[i];
            map.put(sentence, cnt);
            insert(sentence); 
        }
    }
    
    public List<String> input(char c) {
        if(c == '#'){
            String in = inputStr.toString(); 
            map.put(in,map.getOrDefault(in,0) + 1); 
            insert(in);
            inputStr = new StringBuilder();
            return new ArrayList<>();
        }
        inputStr.append(c);
        // find words starting with this inpurstr
        String prefix = inputStr.toString();
        return search(prefix);
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
    