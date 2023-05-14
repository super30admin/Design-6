class AutocompleteSystem {

    TrieNode root;
    String prefix;
    
    class Pair {
        int count;
        String sentence;
        
        public Pair(String sentence, int count){
            this.count = count;
            this.sentence = sentence;
        }
    }
    class TrieNode {
        Map<Character, TrieNode> children;
        Map<String, Integer> count;
        
        public TrieNode(){
            children = new HashMap<Character, TrieNode>();
            count = new HashMap<String, Integer>();
        }
    }
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        prefix = "";
        
        for(int i = 0; i < sentences.length; i++)
            addToDictionary(sentences[i], times[i]);
    }
    
    public void addToDictionary(String sentence, int count) {
        TrieNode cur = root;
        for(Character ch : sentence.toCharArray()) {
            TrieNode next = cur.children.get(ch);
            if(next  == null){
                next = new TrieNode();
                cur.children.put(ch, next);
            }
            cur = next;
            cur.count.put(sentence, count + cur.count.getOrDefault(sentence, 0));
        }
    }
    
    public List<String> input(char c) {
        if(c == '#'){
            addToDictionary(prefix, 1);
            prefix = "";
            return new ArrayList<>();
        }
        prefix += c;
        TrieNode cur = root;
        
        for(Character ch : prefix.toCharArray()) {
            cur = cur.children.get(ch);
            if(cur == null)
                return new ArrayList<>();
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.count == b.count ? a.sentence.compareTo(b.sentence) : b.count - a.count);
        for(String sentence : cur.count.keySet())
            pq.add(new Pair(sentence, cur.count.get(sentence)));
        
        List<String> result = new ArrayList<String>();
        for(int i = 0; i < 3 && !pq.isEmpty(); i++) 
            result.add(pq.poll().sentence);
        
        return result;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
