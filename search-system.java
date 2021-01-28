// O(Mlog K) for input, O(NM) for constructor (inserting all sentences (M being avg length) in trie)
class AutocompleteSystem {
    class TrieNode {        
        TrieNode[] children;
        HashMap<String, Integer> map;
        private TrieNode() {
            children = new TrieNode[256];
            map = new HashMap<>();
        }        
    }
    
    TrieNode root;
    private void insert(String sentence, int count) {
        TrieNode cur = root;
        for(int i=0;i<sentence.length();i++) {
            char ch = sentence.charAt(i);
            if(cur.children[ch - ' '] == null) {
                cur.children[ch-' '] = new TrieNode();
            }
            cur = cur.children[ch - ' '];
            cur.map.put(sentence, cur.map.getOrDefault(sentence, 0) + count);
        }
        
        
    }
    
    private HashMap<String, Integer> search(String sentence) {
        TrieNode curr = root;
        
        for(int i=0;i<sentence.length();i++) {
            char ch = sentence.charAt(i);
            if(curr.children[ch - ' '] == null) {
                return new HashMap<>();
            }
            curr = curr.children[ch - ' '];
        }
        return curr.map;
    }
    
    String input;
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        for(int i=0;i<sentences.length;i++) {
            insert(sentences[i], times[i]);
        }
        input = "";

    }
    
    public List<String> input(char c) {
        if(c == '#') {
            insert(input, 1);
            input = "";
            return new ArrayList<>();
        }
        input+=c;
        
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            if(a.count == b.count) {
                return b.sentence.compareTo(a.sentence);
            }
            return a.count - b.count;
        });
        HashMap<String, Integer> map = search(input);
        for(String s : map.keySet()) {
            if(s.startsWith(input)) {
                pq.add(new Pair(s, map.get(s)));
                if(pq.size()>3) {
                    pq.poll();
                }
            }
        }
        
        List<String> result = new ArrayList<>();

        while(!pq.isEmpty()) {
            result.add(0, pq.poll().sentence);
        }

        return result;
    }
    
    class Pair {
        String sentence;
        int count;
        private Pair(String sentence, int count) {
            this.sentence = sentence;
            this.count = count;
        }
    }
    
}


/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
