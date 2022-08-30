//TC :O(p+q+mlogm) 
//SC :O(P+Q)

class AutocompleteSystem {

    private class Pair{
       String s;
        int count;
        public Pair(String s, int count){
            this.s = s;
            this.count = count;
        }
 
    }
 
    private class Trie{
 
        TrieNode root;
        public  class TrieNode{
            Map<Character, TrieNode> children;
            Map<String, Integer> count;
 
            public TrieNode(){
 
                children = new HashMap<>();
                count = new HashMap<>();
            }
 
        }
 
        public Trie(){
 
            root = new TrieNode();
 
        }
 
        private void add(String s, int count){
            TrieNode cur = root;
            
            for(char ch : s.toCharArray()){
 
                if(!cur.children.containsKey(ch))
                    cur.children.put(ch, new TrieNode());
                cur = cur.children.get(ch);
                cur.count.put(s, cur.count.getOrDefault(s,0)+count);
 
            }
 
        }
 
        private Map<String, Integer> search(String s){
 
            TrieNode cur = root;
 
            for(char c: s.toCharArray()){
                if(!cur.children.containsKey(c)) 
                    return null; 
                cur = cur.children.get(c);
            }
 
            return cur.count;
        }
 
    }
 
    //member variables
 
    Trie trie;
 
    String input;
 
    public AutocompleteSystem(String[] sentences, int[] times) {
 
        trie = new Trie();
 
        input ="";
        buildTrie(sentences, times);
 
    }
 
    private void buildTrie(String[] sentences, int[] times){
 
        for(int i=0; i<sentences.length; i++){
 
            trie.add(sentences[i], times[i]);
 
        }
 
    }
 
    private void buildHeap( Map<String, Integer> count, Queue<Pair>q){
 
        for(String s: count.keySet()){
 
            q.add(new Pair(s, count.get(s)));
 
        }
 
    }
 
    public List<String> input(char c) {
 
        if(c == '#'){
 
            trie.addWord(input , 1);
 
            input = "";
 
            return new ArrayList<>();
 
        }
 
        input =  input+c;
 
        Map<String, Integer> frequencyMap  = trie.search(input);
 
 
        if(frequencyMap == null)  return new ArrayList<>();
 
 
        Queue<Pair>q =new PriorityQueue<>((a,b)->(a.count==b.count) ? a.s.compareTo(b.s) : b.count-a.count);
 
 
        buildHeap(frequencyMap, q);
 
        List<String> res  = new ArrayList<>();
 
        int i=0;
 
        while(i < 3 && !q.isEmpty()){
 
            res.add(q.remove().s);
 
            i++;
 
        }
 
        return res;
 
    }
 
 }