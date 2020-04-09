class AutocompleteSystem {

    private TrieNode root;
    
    // track where we are, as characters are being typed/input'd
    private StringBuilder prefix;
    private TrieNode persistentCursor;
    
    private int K;
    private Comparator<Map.Entry<String, Integer>> comparator ;

    public AutocompleteSystem(String[] sentences, int[] times) {
        // initialize root, prefix, K
        root = new TrieNode();
        prefix = "";
        K = 3;
        persistentCursor = root;
        
        // TODO: implement custom comparator
        comparator = new Comparator<>() {
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                if(entry1.getValue() == entry2.getValue()) {
                    // look at string's lex order
                    //TODO
                }
                // look at frequency
                //TODO
            }
        };
        
        // record sentences
        for (int i = 0; i < sentences.length; i++) {
            record(sentences[i], times[i]);
        }
    }
    
    // inserts sentence s the trie way
    // records frequency on the go at each node
    private void record(String s, int frequency) {
        // TODO complete this
    }
    
    // char c was typed in the prompt
    // if c == #, record it 
    // otherwise, return top K results
    public List<String> input(char c) {
        // if end of sentence: '#'
        // record this sentence
        if (c == '#') {
            record(prefix, 1);
            prefix.setLength(0); // reset prefix
            persistentCursor = root; // reset to root
            return new LinkedList<String>();
        }
        
        // consume current character
        prefix.append(c);
                
        if (persistentCursor.children.get(c) == null) {
            persistentCursor.children.put(c, new TrieNode());
        }
        persistentCursor = persistentCursor.children.get(c);
              

        // selection of topK entries, we are iterating on frequencyMap
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(comparator);
        
        for(Map.Entry<String,Integer> entry: persistentCursor.frequencyMap.entrySet()) {
            // TODO
        }
        
        // collect result from pq into list
        LinkedList<String> topK = new LinkedList<String>();
        while(pq.size() > 0) {
            // TODO
        }
        return topK;
    }
}

// plain old TrieNode
class TrieNode {
    Map<Character, TrieNode> children;
    boolean isWord;
    
    // to record sentence's frequency
    Map<String, Integer> frequencyMap;
    
    public TrieNode() {
        children = new HashMap<>();
        frequencyMap = new HashMap<>();
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
