//Time Complexity :O(N*M) for AutoComplete.
//Space Complexity :O(N)
//Did this code successfully run on Leetcode :yes
//Any problem you faced while coding this : Nope.


//Your code here along with comments explaining your approach

class AutocompleteSystem {
    TrieNode root;
    StringBuilder prefix;
    class TrieNode{
        Map<Character,TrieNode> children;
        Map<String,Integer> counts;
        boolean isWord;
        public TrieNode(){
            this.children = new HashMap<>();
            this.counts = new HashMap<>();
            this.isWord = false;
        }
    }
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        prefix = new StringBuilder("");
        for(int i = 0; i < times.length; i++){
            build(sentences[i],times[i]);
        }
    }
    
    private void build(String sentence, int count){
        TrieNode cur = root;
        for(char ch : sentence.toCharArray()){
            if(!cur.children.containsKey(ch)){
                cur.children.put(ch, new TrieNode());
            }
            cur = cur.children.get(ch);
            cur.counts.put(sentence,cur.counts.getOrDefault(sentence,0)+count);
        }
        cur.isWord = true;
    }
    
    public List<String> input(char c) {
        if('#' == c){
            build(prefix.toString(),1);
            prefix = new StringBuilder("");
            return new ArrayList<String>();
        }
        prefix.append(c);
        TrieNode cur = root;
        for(char ch : prefix.toString().toCharArray()){
            if(!cur.children.containsKey(ch)){return new ArrayList<>();}
            cur = cur.children.get(ch);
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>(){
            public int compare(Pair p1, Pair p2){
                return (p1.c == p2.c)?(p1.s).compareTo(p2.s):p2.c-p1.c;
            }
        });
        for(String s : cur.counts.keySet()){
            pq.offer(new Pair(s,cur.counts.get(s)));
        }
        List<String> result = new ArrayList<>();
        for(int i = 0; i < 3&&!pq.isEmpty();i++){
            result.add(pq.poll().s);
        }
    return result;
    }
    class Pair{
        String s;
        int c;
        public Pair(String s, int c){
            this.s = s;
            this.c = c;
        }
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */