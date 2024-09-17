//time complexity-O(nm)
//Space complexity-O(nm)
//Ran on leetcode-Yes
//Solution with comments-
class AutocompleteSystem {
  private class TrieNode{
        HashMap<Character,TrieNode> children;//children of trie 
        ArrayList<String> list;//list of all the hot sentences in sorted order
        
        public TrieNode(){
            children = new HashMap<>();
            list = new ArrayList<>();
        }
    }
    
    TrieNode root= new TrieNode();
    StringBuilder sb= new StringBuilder();
    HashMap<String, Integer> map = new HashMap<>();
    
    public void add(String s){
        TrieNode curr=root;
        
        for(char ch: s.toCharArray()){
            if(!curr.children.containsKey(ch)){
                curr.children.put(ch,new TrieNode());
            }
            curr=curr.children.get(ch);  
            
            ArrayList<String> temp = curr.list;//storing the sorted sentences and limit to three in the list 
            if(!temp.contains(s)){
                temp.add(s);
            }
               Collections.sort(temp,(a,b)->{
                   if(map.get(a)==map.get(b)){
                       return a.compareTo(b);// lexical order of sentences with same count
                   }
                   else
                     return map.get(b)-map.get(a);
                       
               });
               
               if(temp.size()>3){
                   temp.remove(3);
               }
               
               curr.list=temp;
        }
    }
    
    public List<String> search(String s){//searches in trie
        TrieNode curr= root;
        char[] ch = s.toCharArray();
        for(int i=0;i<ch.length;i++){
            if(!curr.children.containsKey(ch[i])){
                return new ArrayList<>();
            }
           curr=curr.children.get(ch[i]);
        }
        return curr.list;
        
    }
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        for(int x=0;x<times.length;x++){
            map.put(sentences[x],map.getOrDefault(sentences[x],0)+times[x]);
            add(sentences[x]);
        }
    }
    
    public List<String> input(char c) {
        
        if(c=='#'){
            String input=String.valueOf(sb);
            sb=new StringBuilder();
            map.put(input,map.getOrDefault(input,0)+1);
            add(input);
            return new ArrayList<>();
        }
        sb.append(c);
        String input= String.valueOf(sb);
        return search(input);
        
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */