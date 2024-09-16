//Time Complexity : O(n^2)
//Space Complexity : O(n)
//Did this code successfully run on Leetcode :yes
//Any problem you faced while coding this : no

//Approach using a global hashmap
class AutocompleteSystem {
    
    HashMap<String,Integer> map1;
   
    StringBuilder sb;
    public AutocompleteSystem(String[] sentences, int[] times) {
        sb = new StringBuilder();
        map1 = new HashMap<>();
        for(int i = 0; i < sentences.length; i ++){
            
            map1.put(sentences[i],map1.getOrDefault(sentences[i],0) + times[i]);
        }
        
       
    }
    
    public List<String> input(char c) {
        
        if(c == '#'){
            
            String in = sb.toString();
            map1.put(in,map1.getOrDefault(in,0) + 1);
            sb = new StringBuilder();
            return new ArrayList<>();
        }
        
        sb.append(c);
        String in = sb.toString();
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