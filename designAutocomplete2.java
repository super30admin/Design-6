//Time Complexity : O(n)
//Space Complexity : O(n)
//Did this code successfully run on Leetcode :yes
//Any problem you faced while coding this : no

//Approach using a Trie
class AutocompleteSystem {
    
   // HashMap<String,Integer> map1;
   class TrieNode{
       
       HashMap<String,Integer> map;
       HashMap<Character,TrieNode> children;
       
       public TrieNode(){
           
           map = new HashMap<>();
           children = new HashMap<>();
       }
       
   
       
     
   }
        private void insertTrieNode(String sentence, int times){
           
           TrieNode curr = root;
           for(int i = 0; i < sentence.length(); i ++){
               
               char ch = sentence.charAt(i);
               
               if(!curr.children.containsKey(ch)){
                   
                   curr.children.put(ch, new TrieNode());
               }
               
               curr = curr.children.get(ch);
               curr.map.put(sentence,curr.map.getOrDefault((sentence),0)+times);
           }
           
       }
      private HashMap<String,Integer> searchTrieNode(String sentence){
           
           TrieNode curr = root;
           for(int i = 0; i < sentence.length(); i ++){
               
               char ch = sentence.charAt(i);
               
               if(!curr.children.containsKey(ch)){
                   
                   return new HashMap<String,Integer>();
               }
                curr = curr.children.get(ch);
           }
               
               return curr.map;
       }
    TrieNode root;
    StringBuilder sb;
    public AutocompleteSystem(String[] sentences, int[] times) {
        sb = new StringBuilder();
        //map1 = new HashMap<>();
        root = new TrieNode();
        for(int i = 0; i < sentences.length; i ++){
            
            //map1.put(sentences[i],map1.getOrDefault(sentences[i],0) + times[i]);
            
            
            insertTrieNode(sentences[i], times[i]);
        }
        
       
    }
    
    public List<String> input(char c) {
        
        if(c == '#'){
            
            String in = sb.toString();
           // map1.put(in,map1.getOrDefault(in,0) + 1);
            insertTrieNode(in, 1);
            sb = new StringBuilder();
            return new ArrayList<>();
        }
        
        sb.append(c);
        String in = sb.toString();
          HashMap<String,Integer> map1 = searchTrieNode(in);
        PriorityQueue<String> pq = new PriorityQueue<>((a,b) -> {
            
            if(map1.get(a) == map1.get(b)){
                
                return b.compareTo(a);
            }
            return map1.get(a) - map1.get(b);
        });
      
        for(String s : map1.keySet()){
            
            if(s.startsWith(in)){
               pq.add(s);
                
                if(pq.size() > 3){
                    
                    pq.poll();
                }
                
            }
        }
        List<String> result = new ArrayList<>();
        while(!pq.isEmpty()){
            
            result.add(0,pq.poll());
        }
        return result;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */