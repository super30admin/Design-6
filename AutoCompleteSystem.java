class AutocompleteSystem {
    
    HashMap<String, Integer> map;
    StringBuilder input;
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        input = new StringBuilder();
        map = new HashMap<>();
        for(int i = 0; i < sentences.length; i++){
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
        }
        
        
    }
    
    public List<String> input(char c) {
        // System.out.println("Got Char: " + c);
        if(c == '#'){
            String in = input.toString();
            map.put(in, map.getOrDefault(in,0) + 1);
            input = new StringBuilder();
            return new ArrayList<>();
        }
        
        input.append(c);
        
        PriorityQueue<String> pq = new PriorityQueue<>((a,b) -> {
            if(map.get(a) == map.get(b)){
                return b.compareTo(a);
            }
            return map.get(a)-map.get(b);
        });
        
        for(String s: map.keySet()){
            String inp = input.toString();
            //Checks the hashmap to see if there are words contained that starts with input. 
            if(s.startsWith(inp)){
                pq.add(s);
                //if it does needs to be added to our PQ, aka auto suggest results. 
                if(pq.size() > 3){
                    pq.poll();
                }
            }
        }
        
        List<String> result = new ArrayList<>();
        while(!pq.isEmpty()){
            result.add(0,pq.poll());;
        }
        return result;
        
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */

