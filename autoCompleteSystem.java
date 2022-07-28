//Time Complexity: O(n)
//Space Complexity: O(n)
import java.util.*;
class AutocompleteSystem {
    HashMap<String, Integer> hm;
    StringBuilder sb;
   
    class TrieNode{
        TrieNode[] children;
        List<String> li;
        private TrieNode(){
            this.children = new TrieNode[256];
            this.li = new ArrayList<>();
        }
    }
    private void insert(String word){
        TrieNode curr = root;
        for(int i=0;i<word.length();i++){
            char c = word.charAt(i);
            if(curr.children[c-' '] == null){
                curr.children[c-' '] = new TrieNode();
            }
            curr = curr.children[c-' '];
            List<String> li = curr.li;
            if(!li.contains(word)){
                li.add(word);
            }
            Collections.sort(li,(a,b) -> {
                int ca = hm.get(a);
                int cb = hm.get(b);
                if(ca == cb){
                    return a.compareTo(b);
                }
                return cb-ca;
            });
            if(li.size()>3){
                li.remove(li.size()-1);
            }
        }
    }
    private List<String> search(String search){
        TrieNode curr = root;
        for(int i=0;i<search.length();i++){
            char c = search.charAt(i);
            if(curr.children[c - ' '] == null) return new ArrayList<>();
            curr = curr.children[c - ' '];
        }
        return curr.li;
    }
    TrieNode root;
    public AutocompleteSystem(String[] sentences, int[] times) {
        this.hm = new HashMap<>();
        this.sb = new StringBuilder();
        this.root = new TrieNode();
        for(int i=0;i<sentences.length;i++){
            String sentence = sentences[i];
            hm.put(sentence, hm.getOrDefault(sentence,0)+times[i]);
            insert(sentence);
        }
        
    }
    
    public List<String> input(char c) {
        if(c=='#'){
            String search  = sb.toString();
            hm.put(search,hm.getOrDefault(search, 0)+1);
            insert(search);
            this.sb = new StringBuilder();
            return new ArrayList<>();
        }
        sb.append(c);
        return search(sb.toString());
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */