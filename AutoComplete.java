
//Complexity
// Time - O(len * a) + O(prefix +  n + klogk) 
// where a -> # of sentences, len -> avg length of each sentence
// n -> number of nodes searched for the prefix
// k -> top k searches

// Space - O(len * a) + O(prefix +  k) 
// where a -> # of sentences, len -> avg length of each sentence
// n -> number of nodes searched for the prefix
// k -> top k searches

class AutocompleteSystem {
    
    class Trie{
        Map<Character, Trie> children;
        Map<String, Integer> countMap;
        
        Trie(){
            children = new HashMap<>();
            countMap = new HashMap<>();
        }
    }
    
    Trie trie;
    String prefix;

    public AutocompleteSystem(String[] sentences, int[] times) {
        trie = new Trie();
        prefix = "";
        for(int i = 0; i < sentences.length; i++){
            addToTrie(sentences[i], times[i]);
        }
        
    }
    
    private void addToTrie(String sentence, int count){
        Trie curr = trie;
        
        for(char c: sentence.toCharArray()){
            Trie next = curr.children.get(c);
            if(next == null){
                next = new Trie();
                curr.children.put(c, next);
            }
            curr = next;
            curr.countMap.put(sentence, curr.countMap.getOrDefault(sentence, 0) + count);
        }
    }
    
    public List<String> input(char c) {
        if(c == '#'){
            addToTrie(prefix, 1);
            prefix = "";
            return new ArrayList<String>();
        }
        
        prefix = prefix + c;
        Trie searched = search();
        List<String> res = new ArrayList<>();
        if(searched == null) return res; 

        PriorityQueue<Map.Entry<String, Integer>> q = new PriorityQueue<>((a, b) -> a.getValue() == b.getValue() ? a.getKey().compareTo(b.getKey()) : b.getValue() - a.getValue());
        q.addAll(searched.countMap.entrySet());
        
        int k = 3;
       
        while(!q.isEmpty() && k > 0){
            res.add((String) q.poll().getKey());
            k--;
        }
        
        return res;
    }
    
    private Trie search(){
        Trie curr = trie;
       
        for(char c: prefix.toCharArray()){
            if(curr.children.containsKey(c)){
                curr = curr.children.get(c);
            }else{
                return null;
            }
            
        }  
        return curr;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */