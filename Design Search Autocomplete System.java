// HashMap Solution
// Time Complexity = O(Nn)

class AutocompleteSystem {
    private HashMap<String, Integer> map;
    private StringBuilder sb; 
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map = new HashMap<>();
        this.sb = new StringBuilder();
        for(int i = 0; i < sentences.length; i++){
            String sentence = sentences[i];
            map.put(sentence, map.getOrDefault(sentence, 0) + times[i]);
        }
    }
    
    public List<String> input(char c) {
        PriorityQueue<String> pq; pq = new PriorityQueue<>((a,b) -> {
            if(map.get(a) == map.get(b)){
                return b.compareTo(a);
            }
            return map.get(a) - map.get(b);
        });
        
        if(c == '#'){
            String search = sb.toString();
            map.put(search, map.getOrDefault(search, 0) + 1);
            this.sb = new StringBuilder();
            return new ArrayList<>();
        }        
        sb.append(c);     
        String prefix = sb.toString();
        for(String sentence:map.keySet()){ // going over all keys N
            if(sentence.startsWith(prefix)){
                pq.add(sentence); //nlog3
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

/*
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */

// Trie
// Time Complexity = O(n)

class AutocompleteSystem {
    private HashMap<String, Integer> map;
    private StringBuilder sb;

    class TrieNode{
        List <String> startsWith;
        TrieNode [] children;
        public TrieNode(){
            this.startsWith = new ArrayList<>();
            this.children = new TrieNode[256];
        }
    }

    private TrieNode root;
    
    private void insert(String word){
        TrieNode curr = root;
        for(int i = 0; i< word.length(); i++){
            char c = word.charAt(i);
            if(curr.children[c - ' '] == null){
                curr.children[c - ' '] = new TrieNode();
            }
            curr = curr.children[c - ' '];
            curr.startsWith.add(word);
        }
    }
    
    private List<String> search(String prefix) {
        TrieNode curr = root;
        for(int i =0; i<prefix.length(); i++){
            char c = prefix.charAt(i);
            if(curr.children[c - ' '] == null){
                return new ArrayList <>();
            }
            curr = curr.children[c - ' '];
        }
        return curr.startsWith;
    }
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map = new HashMap<>();
        this.sb = new StringBuilder();
        this.root = new TrieNode();
        
        for(int i = 0; i < sentences.length; i++){
            String sentence = sentences[i];
            if(!map.containsKey(sentence))
                insert(sentence);
            map.put(sentence, map.getOrDefault(sentence, 0) + times[i]);
        }
    }
    
    public List<String> input(char c) {
        PriorityQueue<String> pq; pq = new PriorityQueue<>((a,b) -> {
            if(map.get(a) == map.get(b)){
                return b.compareTo(a);
            }
            return map.get(a) - map.get(b);
        });
        
        if(c == '#'){
            String search = sb.toString();
            if(!map.containsKey(search))
                insert(search);
            map.put(search, map.getOrDefault(search, 0) + 1);
            this.sb = new StringBuilder();
            return new ArrayList<>();
        }        
        sb.append(c);     
        String prefix = sb.toString();
        List<String> li = search(prefix);
        for(String sentence : li){
            pq.add(sentence);
            if(pq.size() > 3){
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

/*
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */

