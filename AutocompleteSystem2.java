import java.util.*;

class AutocompleteSystem2 {
    StringBuilder input;
    class TrieNode{
        HashMap<String, Integer> map;
        HashMap<Character, TrieNode> children;
        public TrieNode(){
            map = new HashMap<>();
            children = new HashMap<>();
        }
    }
    TrieNode root;
    private void insert(String word, int times){
        TrieNode curr = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(!curr.children.containsKey(c)){
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
            curr.map.put(word, curr.map.getOrDefault(word, 0) + times); 
        }
    }
    private HashMap<String, Integer> search(String word){
        TrieNode curr = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(!curr.children.containsKey(c)){
                return new HashMap<>();
            }
            curr = curr.children.get(c);
        }
        return curr.map;
    }
    
    public AutocompleteSystem2(String[] sentences, int[] times) {
        root = new TrieNode();
        input = new StringBuilder();
        for(int i = 0; i < sentences.length; i++){
            insert(sentences[i],times[i]);
        }
    }
    
    public List<String> input(char c) {
        if(c == '#'){
            String in = input.toString();
            insert(in, 1);
            input = new StringBuilder();
            return new ArrayList<>();
        }
        input.append(c);
        HashMap<String, Integer> count = search(input.toString());

        PriorityQueue<String> pq = new PriorityQueue<>((a,b)->{
            if(count.get(a) == count.get(b)){
                return b.compareTo(a);
            } 
            return count.get(a) - count.get(b);
        });
        for(String key: count.keySet()){
            String in = input.toString();
            if(key.startsWith(in)){
                pq.add(key);
                if(pq.size() > 3){
                    pq.poll();
                }
            }
        }
        List<String> result = new ArrayList<>();
        while(!pq.isEmpty()){
            result.add(0, pq.poll());

        }
        return result;
    }
}
    

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */