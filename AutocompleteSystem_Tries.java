// Time Complexity :  O(p+q+mlogm) -->  p is length of current search query, q is to the number of nodes in the trie and m is all the sentences which starts with given input
// Space Complexity :
// Did this code successfully run on Leetcode (642): Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

class AutocompleteSystem {
    
    class Trie {
        TrieNode root;
        
        class TrieNode {
            Map<Character, TrieNode> children;
            Map<String, Integer> count;
            
            public TrieNode() {
                this.children = new HashMap<>();
                this.count = new HashMap<>();
            }
        }
        
        public Trie() {
            this.root = new TrieNode();
        }
        
        public void insert(String sentence, int time) {
            TrieNode curr = root;
            for (int i = 0; i < sentence.length(); i++) {
                char c = sentence.charAt(i);
                if (!curr.children.containsKey(c)) {
                    curr.children.put(c, new TrieNode());
                }
                curr = curr.children.get(c);
                curr.count.put(sentence, curr.count.getOrDefault(sentence, 0) + time);
            }
        }
        
        public Map<String, Integer> search(String prefix) {
            TrieNode curr = root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                if (!curr.children.containsKey(c)) return new HashMap<>();
                curr = curr.children.get(c);
            }
            return curr.count;
        }
    }
    
    Trie trie;
    String input;
    public AutocompleteSystem(String[] sentences, int[] times) {
        trie = new Trie();
        input = "";
        // build the trie
        for (int i = 0; i < sentences.length; i++) {
            trie.insert(sentences[i], times[i]);
        }
    }
    
    public List<String> input(char c) {
        if (c == '#') {
            trie.insert(this.input, 1);
            this.input = "";
            return new ArrayList<>(); 
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            if (a.count == b.count) return b.sentence.compareTo(a.sentence);
            else return a.count - b.count;
        });
        
        input += c;
        Map<String, Integer> count = trie.search(this.input);
        for (String key : count.keySet()) {
            pq.add(new Pair(key, count.get(key)));
            if (pq.size() > 3) pq.poll();
        }
        
        List<String> result = new ArrayList<>();
        while (!pq.isEmpty()) result.add(0, pq.poll().sentence);
        
        return result; 
    }
    
    class Pair {
        String sentence;
        int count;
        
        public Pair(String sentence, int count) {
            this.sentence = sentence;
            this.count = count;
        }
    }
}