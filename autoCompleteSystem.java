// Problem2 Design Autocomplete System (https://leetcode.com/problems/design-search-autocomplete-system/)

// Time Complexity : O(1)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach in three sentences only
/*
 * Here, take a trie with sentences and top 3 elements at each element. When a new word comes add it to map and then to trie.
 */
//1
class AutocompleteSystem{
    HashMap<String, Integer> map;
    String input;
    public AutocompleteSystem(String[] sentences, int[] times){
        this.map = new HashMap<>();
        this.input = "";
        for(int i = 0; i < sentences.length; i++){
            int count = times[i];
            int str = sentenes[i];
            map.put(str, map.getOrDefault(str, 0) + count);
        }
    }
    
    public List<String> input(char c){
        if(ch == '#'){
            map.put(input, map.getOrDefault(str, 0) + 1);
            input = "";
            return new ArrayList<>();
        }
        this.input = input + c;
        PriorityQueue<String> pq = new PriorityQueue<>((a,b) -> {
            int countA = map.get(a);
            int countB = map.get(b);
            if(countA == countB) return b.compareTo(a);
            else return countA-countB;
        });
        for(String key: map.keySet()){
            if(key.startsWith(input)){
                pq.add(key);
                if(pq.size()>3){
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
// 2 
class AutocompleteSystem{
    class TrieNode{
        TrieNode []children;
        List<String> list;
        public TrieNode(){
            this.children = new TrieNode[100];
            this.list = new ArrayList<>();
        }
    }
    public void insert(String sentence){
        TrieNode node = root;
        for(int i = 0; i < sentence.length(); i++){
            char c = sentence.charAt(i);
            if(node.children[c - ' '] == null){
                node.children[c - ' '] = new TrieNode();
            }
            node.li.add(sentence);
            node = node.children[c - ' ']; 
        }
    }
    public List<String> search(String input){
        TrieNode node = root;
        for(int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            if(node.children[c - ' '] == null){
                return new ArrayList<>();
            }
            node = node.children[c - ' ']; 
        }
        return node.list;
    }
    HashMap<String, Integer> map;
    String inp;
    TrieNode root
    public AutocompleteSystem(String[] sentences, int[] times){
        this.root = new TrieNode();
        this.map = new HashMap<>();
        this.inp = "";
        for(int i = 0; i < sentences.length; i++){
            int count = times[i];
            int str = sentenes[i];
            if(!map.containsKey(str)){
                insert(sentence[i]);
            }
            map.put(str, map.getOrDefault(str, 0) + count);
        }
    }
    
    public List<String> input(char c){
        if(ch == '#'){
            if(!map.containsKey(inp)){
                insert(inp);
            }
            map.put(inp, map.getOrDefault(str, 0) + 1);
            inp = "";
            return new ArrayList<>();
        }
        this.inp = inp + c;
        PriorityQueue<String> pq = new PriorityQueue<>((a,b) -> {
            int countA = map.get(a);
            int countB = map.get(b);
            if(countA == countB) return b.compareTo(a);
            else return countA-countB;
        });
        List<String> li = search(inp);
        for(String word: li){
                pq.add(key);
                if(pq.size()>3){
                    pq.poll();
                }
        }
        List<String> result = new ArrayList<>();
        while(!pq.isEmpty()){
            result.add(0, pq.poll());
        }
        return result;
    }
    
}
// 3
class AutocompleteSystem{
    class TrieNode{
        TrieNode []children;
        List<String> list; // contains only top 3
        public TrieNode(){
            this.children = new TrieNode[100];
            this.list = new ArrayList<>();
        }
    }
    public void insert(String sentence){
        TrieNode node = root;
        for(int i = 0; i < sentence.length(); i++){
            char c = sentence.charAt(i);
            if(node.children[c - ' '] == null){
                node.children[c - ' '] = new TrieNode();
            }
            node.li.add(sentence);
            node = node.children[c - ' ']; 
            List<String> top3 = node.li;
            Collections.sort(top3, (a,b)->{
                int countA = map.get(a);
                int countB = map.get(b);
                if(countA == countB) return a.compareTo(b);
                return countB-countA;
            });
            if(top3.size()>3){
                top3.remove(top3.size()-1);
            }
        }
    }
    public List<String> search(String input){
        TrieNode node = root;
        for(int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            if(node.children[c - ' '] == null){
                return new ArrayList<>();
            }
            node = node.children[c - ' ']; 
        }
        return node.list;
    }
    HashMap<String, Integer> map;
    String inp;
    TrieNode root
    public AutocompleteSystem(String[] sentences, int[] times){
        this.root = new TrieNode();
        this.map = new HashMap<>();
        this.inp = "";
        for(int i = 0; i < sentences.length; i++){
            int count = times[i];
            int str = sentenes[i];
            map.put(str, map.getOrDefault(str, 0) + count);
            insert(sentence[i]);
        }
    }
    
    public List<String> input(char c){
        if(ch == '#'){
            map.put(inp, map.getOrDefault(str, 0) + 1);
            insert(inp);
            inp = "";
            return new ArrayList<>();
        }
        this.inp = inp + c;
        return search(inp);
    }
    
}