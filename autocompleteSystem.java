// Time Complexity : O(1)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Trie with PQ at each node
class AutocompleteSystem {
    class TrieNode{
        // HashMap<String, Integer> map;
        List<String> pq;
        TrieNode[] children;
        public TrieNode(){
            // map = new HashMap<>();
            pq = new ArrayList<>(); // stores top 3 results @ each node
            children = new TrieNode[256];
        }
    }
    
    
    public void insert(String sentence, int times){
        TrieNode curr = root;
        for(int i=0; i< sentence.length(); i++){
            char c = sentence.charAt(i);
            if(curr.children[c-' '] == null){
                curr.children[c-' '] = new TrieNode();
            }
            curr = curr.children[c-' '];
            // curr.map.put(sentence, curr.map.getOrDefault(sentence, 0) + times);
            // at every TrieNode add hashmap containing sentence
            List<String> li = curr.pq;
            if(!li.contains(sentence)){
                li.add(sentence);
            }
            Collections.sort(li, (a,b) ->{
                if(map.get(a) == map.get(b)){
                    return a.compareTo(b); // list has highest count elem in the beginning
                }
                return map.get(b) - map.get(a);
            });
            if(li.size() > 3){
                li.remove(li.size() - 1);
            }
            curr.pq = li;
        }
    }
    
    private List<String> search(String prefix){
        TrieNode curr = root;
        for(int i=0; i< prefix.length(); i++){
            char c = prefix.charAt(i);
            if(curr.children[c-' '] == null){
                return new ArrayList<>();
                // if word doesn't exist in Trie (zebra -> z or e doesn't exit)
            }
            curr = curr.children[c-' '];
            // curr.map.put(sentence, map.getOrDefault(sentence, 0) + times);
            // at every TrieNode add hashmap containing sentence
        }
        return curr.pq;
    }
    
    HashMap<String, Integer> map;
    StringBuilder sb;
    TrieNode root;
    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        root = new TrieNode();
        sb = new StringBuilder();
        for(int i=0; i<sentences.length; i++){
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
            insert(sentences[i], times[i]); // add all sentences to TrieNode
        }
        // System.out.print(map);
    }
    
    public List<String> input(char c) {
        // #
        if(c == '#'){
            String str = sb.toString();
            map.put(str, map.getOrDefault(str, 0) + 1);
            insert(str, 1);
            sb = new StringBuilder();
            return new ArrayList<>();
        }
        
        // search it in the Trie
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

// *******************************************

// Time Complexity : O(1)
// Space Complexity : O(n * l)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// TrieNode with HashMap at each node
class AutocompleteSystem {
    class TrieNode{
        HashMap<String, Integer> map;
        TrieNode[] children;
        public TrieNode(){
            map = new HashMap<>();
            children = new TrieNode[256];
        }
    }
    
    
    public void insert(String sentence, int times){
        TrieNode curr = root;
        for(int i=0; i< sentence.length(); i++){
            char c = sentence.charAt(i);
            if(curr.children[c-' '] == null){
                curr.children[c-' '] = new TrieNode();
            }
            curr = curr.children[c-' '];
            curr.map.put(sentence, curr.map.getOrDefault(sentence, 0) + times);
            // at every TrieNode add hashmap containing sentence
        }
    }
    
    private HashMap<String, Integer> search(String prefix){
        TrieNode curr = root;
        for(int i=0; i< prefix.length(); i++){
            char c = prefix.charAt(i);
            if(curr.children[c-' '] == null){
                return new HashMap<>();
                // if word doesn't exist in Trie (zebra -> z or e doesn't exit)
            }
            curr = curr.children[c-' '];
            // curr.map.put(sentence, map.getOrDefault(sentence, 0) + times);
            // at every TrieNode add hashmap containing sentence
        }
        return curr.map;
    }
    
    // HashMap<String, Integer> map;
    StringBuilder sb;
    TrieNode root;
    public AutocompleteSystem(String[] sentences, int[] times) {
        // map = new HashMap<>();
        root = new TrieNode();
        sb = new StringBuilder();
        for(int i=0; i<sentences.length; i++){
            // map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
            insert(sentences[i], times[i]); // add all sentences to TrieNode
        }
        // System.out.print(map);
    }
    
    public List<String> input(char c) {
        // #
        if(c == '#'){
            String str = sb.toString();
            // map.put(str, map.getOrDefault(str, 0) + 1);
            insert(str, 1);
            sb = new StringBuilder();
            return new ArrayList<>();
        }
        
        // search it in the Trie
        sb.append(c);
        String se = sb.toString();
        HashMap<String, Integer> map = search(se);
        
        // (a, b) -> a-b
        PriorityQueue<String> pq = new PriorityQueue<>((a,b) ->{
            if(map.get(a) == map.get(b)){
                return b.compareTo(a);
                // return lexicographically worse
            }
            return map.get(a) - map.get(b);
            // return Math.min(map.get(a), map.get(b));
            // lesser count on top of PriorityQueue
        });
        
        for(String key: map.keySet()){
            if(key.startsWith(se)){
                pq.add(key);
                if(pq.size() > 3) pq.poll();
            }
        }
        List<String> result =  new ArrayList<>();
        while(!pq.isEmpty()){
            result.add(0, pq.poll());
            // 0th index because top result is at the bottom of PQ
        }
        return result;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */

// ******************************************

// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Brute Force approach:
class AutocompleteSystem {
    HashMap<String, Integer> map;
    StringBuilder sb;
    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        sb = new StringBuilder();
        for(int i=0; i<sentences.length; i++){
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
        }
        // System.out.print(map);
    }
    
    public List<String> input(char c) {
        // #
        if(c == '#'){
            String str = sb.toString();
            map.put(str, map.getOrDefault(str, 0) + 1);
            sb = new StringBuilder();
            return new ArrayList<>();
        }
        PriorityQueue<String> pq = new PriorityQueue<>((a,b) ->{
            if(map.get(a) == map.get(b)){
                return b.compareTo(a);
                // return lexicographically worse
            }
            return map.get(a) - map.get(b);
            // return Math.min(map.get(a), map.get(b));
            // lesser count on top of PriorityQueue
        });
        sb.append(c);
        String search = sb.toString();
        for(String key: map.keySet()){
            if(key.startsWith(search)){
                pq.add(key);
                if(pq.size() > 3) pq.poll();
            }
        }
        List<String> result =  new ArrayList<>();
        while(!pq.isEmpty()){
            result.add(0, pq.poll());
            // 0th index because top result is at the bottom of PQ
        }
        return result;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
