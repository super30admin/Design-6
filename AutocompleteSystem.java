class AutocompleteSystem {   
    class Trie{
        Trie[] child;
        List<String> li;
        public Trie(){
            child = new Trie[256];
            li=new ArrayList<>();
        }
    }
    private void insert(String s){
        Trie curr=root;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(curr.child[c-' ']==null){
                curr.child[c-' ']=new Trie();
            }
            curr=curr.child[c-' '];
            if(!curr.li.contains(s)){
                curr.li.add(s);
            }                
            Collections.sort(curr.li,(a,b)->{
                int ca=map.get(a);
                int cb=map.get(b);
                if(ca==cb) 
                    return a.compareTo(b);
                return cb-ca; 
            });
            if(curr.li.size()>3)
            curr.li.remove(curr.li.size()-1);
        }
    }
    private List<String> search(String s){
        Trie curr=root;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(curr.child[c-' ']==null) return new ArrayList<>();
            curr=curr.child[c-' '];
        }
        return curr.li;
    }
    HashMap<String,Integer> map;
    StringBuilder sb;
    Trie root;
    public AutocompleteSystem(String[] sentences, int[] times) {
        root=new Trie();
        map=new HashMap<>();
        sb=new StringBuilder();
        for(int i=0;i<times.length;i++){
            String sent=sentences[i]; 
            map.put(sent,map.getOrDefault(sent,0)+times[i]);
            insert(sent);            
        }
    }
    
    public List<String> input(char c) {        
        if(c=='#'){
            String st =sb.toString(); 
            map.put(st,map.getOrDefault(st,0)+1);
            insert(st);
            sb=new StringBuilder();
            return new ArrayList<>();
        }
        sb.append(c);

        return search(sb.toString());
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
