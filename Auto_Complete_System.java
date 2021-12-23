// Time Complexity : O(1)
// Space Complexity : O(N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class AutocompleteSystem {
    class TrieNode{
        HashMap<Character,TrieNode> children;
        HashMap<String,Integer> map;
        public TrieNode(){
            this.children=new HashMap<>();
            this.map=new HashMap<>();
        }
        
    }
    
    String s;
    TrieNode root;
    public AutocompleteSystem(String[] sentences, int[] times) {
        s="";
        root=new TrieNode();
        
        for(int i=0;i<sentences.length;i++){
            insert(sentences[i],times[i]);
        }
    }
    
    public void insert(String sentence, int times){
        TrieNode curr=root;
        
        for(int i=0;i<sentence.length();i++){
            char c=sentence.charAt(i);
            if(!curr.children.containsKey(c)){
                curr.children.put(c,new TrieNode());
            }
            curr=curr.children.get(c); 
            if(!curr.map.containsKey(sentence)){
                 curr.map.put(sentence,times);
            }else{
                curr.map.put(sentence,curr.map.get(sentence)+times);
            }
           
        }
    }
    
    public List<String> input(char c) {
      List<String> result=new ArrayList<>();
        if(c=='#'){
            insert(s,1);
            s="";
            return new ArrayList<>();
        }else{
            s+=c;
            TrieNode curr=root;
           for(int i=0;i<s.length();i++){
                char ch=s.charAt(i);
                if(!curr.children.containsKey(ch)){
                   return new ArrayList<>();
                }
                curr=curr.children.get(ch); 
           }
            HashMap<String, Integer> map =curr.map;
            PriorityQueue<String> pq=new PriorityQueue<>((a,b)->{
                if(map.get(a)==map.get(b)){
                    return b.compareTo(a);
                }
                
                return map.get(a)-map.get(b);
            });
                
             for(String s: map.keySet()){
                   pq.add(s);
                 if(pq.size()>3){
                     pq.poll();
                 }
               
             }  
            
            while(!pq.isEmpty()){
                result.add(0,pq.poll());
            }
            
            return result;
        }
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */