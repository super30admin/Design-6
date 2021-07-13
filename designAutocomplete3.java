//Time Complexity : O(1)
//Space Complexity : O(n)
//Did this code successfully run on Leetcode :yes
//Any problem you faced while coding this : no

//Approach using a global hashmap and trie
class AutocompleteSystem {
    
   // HashMap<String,Integer> map1;
      HashMap<String,Integer> map;
   class TrieNode{
       
     List<String> pq;
       HashMap<Character,TrieNode> children;
       
       public TrieNode(){
           
           pq = new ArrayList<>();
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
               List<String> temp = curr.pq;
               if(!temp.contains(sentence)){
                   temp.add(sentence);
               }
               
               Collections.sort(temp,(a,b) ->{
                   
                   if(map.get(a) == map.get(b)){
                       
                       return a.compareTo(b);
                   }
                   else{
                      return map.get(b) - map.get(a);
                       
                   }
               });
               if(temp.size() > 3){
                   
                   temp.remove(3);
               }
               curr.pq = temp;
           }
           
       }
      private List<String> searchTrieNode(String sentence){
           
           TrieNode curr = root;
           for(int i = 0; i < sentence.length(); i ++){
               
               char ch = sentence.charAt(i);
               
               if(!curr.children.containsKey(ch)){
                   
                   return new ArrayList<>();
               }
                curr = curr.children.get(ch);
           }
               
               return curr.pq;
       }
    TrieNode root;
    StringBuilder sb;
    public AutocompleteSystem(String[] sentences, int[] times) {
        sb = new StringBuilder();
        map = new HashMap<>();
        root = new TrieNode();
        for(int i = 0; i < sentences.length; i ++){
            
            map.put(sentences[i],map.getOrDefault(sentences[i],0) + times[i]);
            
            
            insertTrieNode(sentences[i], times[i]);
        }
        
       
    }
    
    public List<String> input(char c) {
        
        if(c == '#'){
            
            String in = sb.toString();
           map.put(in,map.getOrDefault(in,0) + 1);
            insertTrieNode(in, 1);
            sb = new StringBuilder();
            return new ArrayList<>();
        }
        
        sb.append(c);
        String in = sb.toString();
        return searchTrieNode(in);
        
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */