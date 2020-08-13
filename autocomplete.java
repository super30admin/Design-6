class AutocompleteSystem {
    class Trie{
        TrieNode root;
        class TrieNode{
            Map<Character, TrieNode> children;
            Map<String, Integer> count;
            public TrieNode(){
                this.children=new HashMap();
                this.count=new HashMap();
            }
        }
        public Trie(){
            root=new TrieNode();
            
        }
        public void insert(String sentence,int time){
            TrieNode curr=root;
            for(int i=0;i<sentence.length();i++){
                char c=sentence.charAt(i);
                if(!curr.children.containsKey(c)){
                    curr.children.put(c,new TrieNode());
                }
                curr=curr.children.get(c);
                curr.count.put(sentence,curr.count.getOrDefault(sentence,0)+time);
            } 
        }
        public Map<String,Integer> search(String prefix){
            TrieNode curr=root;
            for(int i=0;i<prefix.length();i++){
                char c=prefix.charAt(i);
                if(!curr.children.containsKey(c)){
                    return new HashMap();
                }
                curr=curr.children.get(c);
            }
            return curr.count;
        }
    }
    class Pair{
        String sentence;
        int count;
        public Pair(String sentence, int count){
            this.sentence=sentence;
            this.count=count;
        }
    }
    Trie trie;
    String input;
    public AutocompleteSystem(String[] sentences, int[] times) {
        trie=new Trie();
        input="";
        for(int i=0;i<sentences.length;i++){
            trie.insert(sentences[i],times[i]);
        }
    }
    
    public List<String> input(char c) {
        if(c=='#'){
            trie.insert(input,1);
            input="";
            return new ArrayList();
        }
        PriorityQueue<Pair> pq=new PriorityQueue<Pair>((a,b) -> {
            if(a.count==b.count){
                return b.sentence.compareTo(a.sentence);
            }else{
                return a.count-b.count;
            }
        });
        input+=c;
        Map<String,Integer> map=trie.search(input);
        for(String key:map.keySet()){
            pq.add(new Pair(key,map.get(key)));
            if(pq.size()>3){
                pq.poll();
            }
        }
        List<String> li=new ArrayList();
        while(!pq.isEmpty()){
            li.add(0,pq.poll().sentence);
        }
        return li;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */