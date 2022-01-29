import java.util.*;

class AutocompleteSystem3 {
    StringBuilder input;
    HashMap<String, Integer> map;
    class TrieNode{
        HashMap<Character, TrieNode> children;
        List<String> pq;
        public TrieNode(){
            children = new HashMap<>();
            pq = new ArrayList<>();
        }
    }
    TrieNode root;
    private void insert(String sentence, int times){
        TrieNode curr = root;
        for(int i = 0; i < sentence.length(); i++){
            char c = sentence.charAt(i);
            if(!curr.children.containsKey(c)){
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
            List<String> temp = curr.pq;
            if(!temp.contains(sentence)){
                            temp.add(sentence);

            }
            Collections.sort(temp, (a,b)->{
               if(map.get(a) == map.get(b)){
                   return a.compareTo(b);
               } 
                return map.get(b) - map.get(a);
            });
            if(temp.size()> 3){
                temp.remove(temp.size() - 1);
            }
        }
    }
    private List<String> search(String word){
        TrieNode curr = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(!curr.children.containsKey(c)){
                return new ArrayList<>();
            }
            curr = curr.children.get(c);
        }
        return curr.pq;
    }
    
    public AutocompleteSystem3(String[] sentences, int[] times) {
        root = new TrieNode();
        input = new StringBuilder();
        map = new HashMap<>();
        for(int i = 0; i < sentences.length; i++){
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
            insert(sentences[i],times[i]);
        }
    }
    
    public List<String> input(char c) {
        if(c == '#'){
            String in = input.toString();
            map.put(in, map.getOrDefault(in, 0) + 1);
            insert(in, 1);
            input = new StringBuilder();
            return new ArrayList<>();
        }
        input.append(c);
        
        return search(input.toString());
    }
}
    

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */