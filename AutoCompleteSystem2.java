// Time Complexity : O(1) -> for user operations
// for programmer
// Since we are storing only the top 3 results which can be received in constant time
// insertion takes O(N * l) to insert into trie and to sort in the list O(n log n) which is verry small (3 items)
// Space Complexity : O(N)
// Did this code successfully run on Leetcode : Yes

//with Trie and PriorityQueue with all only top 3 strings of prefixes in trie

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class AutocompleteSystem2 {
    HashMap<String, Integer> map;
    String search;
    TrieNode root;
    class TrieNode{
        TrieNode[] children;
        List<String> topResults;

        public TrieNode(){
            children = new TrieNode[256];
            topResults = new ArrayList<>();
        }
    }
    public AutocompleteSystem2(String[] sentences, int[] times) {
        map = new HashMap<>();
        root = new TrieNode();
        search = "";
        for(int i=0; i<sentences.length; i++)
        {
            map.put(sentences[i],map.getOrDefault(sentences[i],0)+times[i]);
            insert(sentences[i]);
        }
    }

    public void insert(String word){
        TrieNode curr = root;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if(curr.children[c-' ']==null){
                curr.children[c-' '] = new TrieNode();
            }
            curr = curr.children[c- ' '];
            List<String> temp = curr.topResults;
            if(!temp.contains(word)){
                temp.add(word);
            }

            Collections.sort(temp,(String a, String b)->{
                if(map.get(a) == map.get(b))
                {
                    return a.compareTo(b);
                }
                return map.get(b) - map.get(a);
            });
            if(temp.size()>3)
            {
                temp.remove(temp.size()-1);
            }
        }

    }
    public List<String> searchPrefix(String prefix){
        List<String> result = new ArrayList<>();
        TrieNode curr = root;
        for(int i=0; i<prefix.length(); i++)
        {
            char c = prefix.charAt(i);
            if(curr.children[c-' ']== null){
                return result;
            }
            curr = curr.children[c-' '];
        }
        return curr.topResults;
    }
    public List<String> input(char c) {
        List<String> result = new ArrayList<>();
        if(c == '#')
        {
            map.put(search, map.getOrDefault(search,0)+1);
            insert(search);
            search = "";
            return result;
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