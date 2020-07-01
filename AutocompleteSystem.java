class AutocompleteSystem {

    /**
     * Time complexity: O(nlog3) -> O(n) where n is number of sentences;
     * Space complexity: O(n) where n is number of sentences.
     * 
     * Approach:
     * Maintain min heap and add the sentences starting with input string into the min heap.
     */

    Map<String, Integer> map;
    String input;
    public AutocompleteSystem(String[] sentences, int[] times) {
        
        map = new HashMap<>();
        input = "";
        for(int i=0; i<sentences.length; i++) {
            map.put(sentences[i], times[i]);
        }
    }
    
    public List<String> input(char c) {
        
        List<String> result = new ArrayList<>();
        
        if(c == '#') {
            map.put(input, map.getOrDefault(input, 0) + 1);
            input = "";
            return result;
        }
        
        input += c;
        
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            if(a.count == b.count) {
                return b.str.compareTo(a.str);
            }
            return a.count - b.count;
        });
        
        for(String s: map.keySet()) {
            if(s.startsWith(input)){
                if(pq.size() < 3) {
                    pq.add(new Pair(s, map.get(s)));
                }
                else {
                    Pair top = pq.peek();
                    
                    if(top.count == map.get(s)) {
                        if(top.str.compareTo(s) > 0) {
                            pq.poll();
                            pq.add(new Pair(s, map.get(s)));
                        }
                    }
                    else if(top.count < map.get(s)){
                        pq.poll();
                        pq.add(new Pair(s, map.get(s)));
                    }
                }
                
            }
        }
        
        while(pq.size() > 0) {
            result.add(0, pq.poll().str);
        }
        
        return result;
    }
    
    class Pair{
        String str;
        int count;
        
        public Pair(String s, int c){
            str = s;
            count = c;
        }
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */