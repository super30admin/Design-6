// Time Complexity : O(nl)
// Space Complexity :O(nl)
class AutocompleteSystem {
    StringBuilder sb;
    
    class TrieNode{
        HashMap<Character,TrieNode> children;
        HashMap<String,Integer> map;
        public TrieNode(){
            children = new HashMap<>();
            map = new HashMap<>();
        }
    }
    
    TrieNode root;
    
    private void insert(String word, int times){
        TrieNode curr= root;
        for(int i=0;i<word.length();i++){
            char c= word.charAt(i);
            if(!curr.children.containsKey(c)){
                curr.children.put(c,new TrieNode());
            }
            curr= curr.children.get(c);
            curr.map.put(word,curr.map.getOrDefault(word,0)+times);
        }
    }
    
    private HashMap<String,Integer> search(String word){
        TrieNode curr=root;
        for(int i=0;i<word.length();i++){
            char c= word.charAt(i);
            if(curr.children.get(c)==null){
                return new HashMap<>();
            }
            
            curr=curr.children.get(c);
            
        }
        
        return curr.map;
    }
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        sb=new StringBuilder();
        root=new TrieNode();
        for(int i=0;i<sentences.length;i++){
            String str=sentences[i];
            insert(str,times[i]);
        }
    }
    
    public List<String> input(char c) {
        if(c=='#'){
            String sentence=sb.toString();
            insert(sentence, 1);
            sb=new StringBuilder();
            return new ArrayList<>();
        }
        sb.append(c);
        
        HashMap<String,Integer> map=search(sb.toString());
        PriorityQueue<String> pq=new PriorityQueue<>((a,b) -> {
            if(map.get(a)==map.get(b)){
                return b.compareTo(a);
            }
            return map.get(a)-map.get(b);
        });
        
        for(String key:map.keySet()){
            
                pq.add(key);
                if(pq.size()>3){
                    pq.poll();
                }
           
        }
        
        List<String> res=new ArrayList<>();
        while(pq.size()!=0){
            res.add(0,pq.poll());
        }
        
        return res;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */