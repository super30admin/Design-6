// Time Complexity : O(n) -> list of the words of the prefix that the user types
// Space Complexity : O(N)
// Did this code successfully run on Leetcode : Yes

//with Trie and PriorityQueue with all the available strings of prefixes in trie

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

//With Trie and PriorityQueue
public class AutoCompleteSystem1 {
        HashMap<String, Integer> map;
        String search;
        TrieNode root;
        class TrieNode{
            TrieNode[] children;
            List<String> startsWith;

            public TrieNode(){
                children = new TrieNode[256];
                startsWith = new ArrayList<>();
            }
        }
        public AutoCompleteSystem1(String[] sentences, int[] times) { // O(n*l)  insertion into trie by programmer
            map = new HashMap<>();
            root = new TrieNode();
            search = "";
            for(int i=0; i<sentences.length; i++)
            {
                if(!map.containsKey(sentences[i])) insert(sentences[i]);
                map.put(sentences[i],map.getOrDefault(sentences[i],0)+times[i]);
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
                curr.startsWith.add(word);
            }
        }
        public List<String> searchPrefix(String prefix){  //O(l)  l is the length of the prefix
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
            return curr.startsWith;
        }
        public List<String> input(char c) {
            List<String> result = new ArrayList<>();
            if(c == '#')
            {
                if(!map.containsKey(search)){
                    insert(search);
                }
                map.put(search, map.getOrDefault(search,0)+1);
                search = "";
                return result;
            }
            search += c;
            PriorityQueue<String> pq = new PriorityQueue<>((String a, String b)->{
                if(map.get(a) == map.get(b)){
                    return b.compareTo(a);
                }
                return map.get(a) - map.get(b);
            });
            List<String> wordsMatch = searchPrefix(search);
            for(String sentence: wordsMatch)   //O(n)
            {
                pq.add(sentence);
                if(pq.size() > 3) pq.poll();
            }
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
