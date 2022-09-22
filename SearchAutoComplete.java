class AutocompleteSystem {

    HashMap<String, Integer>hm= new HashMap<>();
    StringBuilder sb= new StringBuilder();
    public AutocompleteSystem(String[] sentences, int[] times) {
    
        for(int i=0;i<sentences.length;i++){
            
            if(hm.containsKey(sentences[i])){
            
               
            }else{
                
                //new data comes in
                               
                hm.put(sentences[i],times[i]);
            
            }
        }
        
        
    }
    
    public List<String> input(char c) {
        List<String> ans= new ArrayList<>();
        if(c=='#'){
            if(hm.containsKey(sb.toString())){
                
                hm.put(sb.toString(),hm.get(sb.toString())+1);
                
            }else{
                
                hm.put(sb.toString(),1);
            }
            sb= new StringBuilder();
            return ans;
        
        }else{
            
            
        PriorityQueue<String> pq = new PriorityQueue <>((a,b)->{ 
        
        if(hm.get(a)==hm.get(b)){
            
            return b.compareTo(a);
        }
        else{ return hm.get(a)-hm.get(b);}
        });
            
            
            sb.append(c);
            
            for(String s: hm.keySet()){
                
                if(s.startsWith(sb.toString())){
                    pq.add(s);
                    if(pq.size()>3){
                        
                        pq.poll();
                    }
                    
                }
            }
            
            
         while(!pq.isEmpty()){
             
             ans.add(0,pq.poll());
         }   
          return ans;  
        }
        
        
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
