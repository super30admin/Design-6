class AutocompleteSystem {
    StringBuilder input;
    HashMap<String,Integer> map;
    public AutocompleteSystem(String[] sentences, int[] times) {
        
        this.map = new HashMap<>();
        this.input = new StringBuilder();
        for(int i =0;i< times.length;i++){
            String str = sentences[i];
            int cnt = times[i];
            map.put(str,map.getOrDefault(str, 0) +cnt);
        }
        
        
    }
    
    public List<String> input(char c) {
        if(c == '#'){
            String in = input.toString();
            input = new StringBuilder();
            map.put(in, map.getOrDefault(in, 0)+1);
            return new ArrayList<>();
        }
        input.append(c);
        PriorityQueue<String> q = new PriorityQueue<>((a,b)-> {
               int cnt =  map.get(a) - map.get(b);
               if(cnt == 0){
                return b.compareTo(a); //smaller Ascii value should go down
               }
               return cnt;
        } );
        String searchTerm = input.toString();
        for(String key : map.keySet()){
            if(key.startsWith(searchTerm)){
                q.add(key);
                if(q.size()>3){
                    q.poll();
                }
            }
        }

        List<String> result = new ArrayList<>();
        while(!q.isEmpty()){
            result.add(0,q.poll());
        }

        return result;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */