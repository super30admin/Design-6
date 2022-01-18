import java.util.*;
class AutocompleteSystem {

    public class TrieNode{
        TrieNode[] children;
        List<String> pq;
        public TrieNode(){
            children = new TrieNode[256];
            pq = new ArrayList<>();
        }
    }
    public void insert(String sentence,int time){
        TrieNode curr= root;
       for(int i =0;i<sentence.length();i++){
           char c = sentence.charAt(i);
           if(curr.children[c-' '] == null){
               curr.children[c-' '] = new TrieNode();
           }
           curr = curr.children[c-' '];
            List<String> li = curr.pq;
            if(!li.contains(sentence)){
                li.add(sentence);
            }
           
           Collections.sort(li,(a,b)->{
               if(map.get(a)== map.get(b)){
                   return a.compareTo(b);
               }
               return map.get(b)-map.get(a);
           });
           if(li.size()>3){
               li.remove(li.size()-1);
           }
       }
    }
    public List<String> search(String sentence){
       TrieNode curr= root;
       for(int i =0;i<sentence.length();i++){
           char c = sentence.charAt(i);
           if(curr.children[c-' ']== null){
               return new ArrayList<>();
           }
           curr = curr.children[c-' '];
        
        }
        return curr.pq; 
    }
    
    TrieNode root;
    HashMap<String, Integer> map;
    StringBuilder sb; 
    public AutocompleteSystem(String[] sentences, int[] times) {
        map =new HashMap<>();
        root = new TrieNode();
        sb = new StringBuilder();
        for(int i =0;i<sentences.length;i++){
            map.put(sentences[i],map.getOrDefault(sentences[i],0)+times[i]);
            insert(sentences[i],times[i]);
        }
        
    }
    //time complexity : length of  input
    // space complexity : 1
    // did it run on leetcode : yes
    // any doubts : is space and time complexity correct for input function as I have neglected 
    // map space and trie space because they are initialized in constructor
    // https://leetcode.com/problems/design-search-autocomplete-system/submissions/
    public List<String> input(char c) {
        if(c == '#'){
            String st = sb.toString();
            map.put(st, map.getOrDefault(st, 0) + 1);
            insert(st, 1);
            sb = new StringBuilder();
            return new ArrayList<>();
        }
        sb.append(c);
        String se = sb.toString();
        return search(se);
        
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */