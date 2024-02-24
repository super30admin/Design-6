/*
Time Complexity: O(N log k)

Space Complexity: O(3N)

Did this code successfully run on Leetcode : Yes

Approach: Using a Trie, hashmap and min heap - storing the cache data in the hashmap, for prefix search
using a trie and having the hottest wordsin the min heap to give out top 3 hottest searches.

Prob: 642. Design Search Autocomplete System

*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

class AutocompleteSystem {
    class TrieNode{
        List<String> startsWith;
        TrieNode [] children;
        public TrieNode(){
            this.startsWith = new ArrayList<>();
            this.children = new TrieNode[100];
        }
    }

    public void insert(String word){
        TrieNode curr = root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(curr.children[c - ' '] == null){
                curr.children[c - ' '] = new TrieNode();
            }
            curr = curr.children[c - ' '];
            curr.startsWith.add(word);
        }
    }
    public List<String> search(String prefix){
        TrieNode curr = root;
        for(int i=0;i<prefix.length();i++){
            char c = prefix.charAt(i);
            if(curr.children[c - ' '] == null){
                return new ArrayList<>();
            }
            curr = curr.children[c - ' '];
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
            if(!map.containsKey(in)){
                insert(in);
            }
            map.put(in,map.getOrDefault(in,0) + 1); 
            inputStr = new StringBuilder();
            return new ArrayList<>();
        }
        inputStr.append(c);
        PriorityQueue<String> pq = new PriorityQueue<>((a,b) -> {
            int countA = map.get(a);
            int countB = map.get(b);
            if(countA == countB){
                return b.compareTo(a);
            }
            return countA - countB;
        });
        // find words starting with this inpurstr
        String prefix = inputStr.toString();
        List<String> startsWith = search(prefix);
        for(String key : startsWith){
            if(key.startsWith(prefix)){
                pq.add(key);
                if(pq.size() > 3){
                    pq.poll();
                }
            }
        }
        List<String> result = new ArrayList<>();
        while(!pq.isEmpty()){
            result.add(0,pq.poll());
        }
        return result;

    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */