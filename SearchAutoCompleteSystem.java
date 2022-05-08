// Time Complexity : O(nk) where n is number of sentence of average length k, input O(1)
// Space Complexity : O(nk) where n is number of sentence of average length k
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
// Create a trie of sentences and hashmap of frequencies
// For each node we will maintain the top 3 words, from the prefix
// When autocomplete is triggered it would return those words

class AutocompleteSystem {
    Map<String, Integer> map;
    TrieNode trie;
    TrieNode currentQnode;
    StringBuilder query;
    class TrieNode{
        Map<Character, TrieNode> children;
        List<String> words;
        public TrieNode(){
            children = new HashMap<>();
            words = new ArrayList<>();
        }  
    
    void add(String s){
        if(!words.contains(s))
            words.add(s);
        //List<String> words = node.words;
           if(!words.contains(s)){
               words.add(s);
           }
            Collections.sort(words, (a,b) ->{
                int dif = map.get(b) - map.get(a);
                if(dif == 0)
                    return a.compareTo(b);
                return dif;
            });
            if(words.size() > 3){
                words.remove(words.size() - 1);
            }
    }
}
    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        trie = new TrieNode();
        query = new StringBuilder();
        currentQnode = trie;
        for(int i = 0; i < sentences.length; i++){
            insert(sentences[i], times[i]);
        }
    }
    
    public List<String> input(char c) {
        if(c =='#'){
            insert(query.toString(), 1);
            query = new StringBuilder();
            currentQnode = trie;
            return new ArrayList<>();
        }
        query.append(c);
        return prefixMatch(c);
    }
    public List<String> prefixMatch(char c){
        if(currentQnode != null)
            currentQnode = currentQnode.children.get(c);
        if(currentQnode == null){
            return new ArrayList<>();
        }
        return currentQnode.words;
    }
    public void insert(String s, int t){
        map.put(s, map.getOrDefault(s, 0)+t);
        TrieNode node = trie;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(!node.children.containsKey(c)){
                node.children.put(c, new TrieNode());
            }
            node = node.children.get(c);
            node.add(s);
        }
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */