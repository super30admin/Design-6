class AutocompleteSystem {
    
    HashMap<String, Integer> map;
    String input ="";

    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        for(int i= 0; i<sentences.length;i++){
            map.put(sentences[i],map.getOrDefault(sentences[i],0)+times[i]);
        }
    }
    
    public List<String> input(char c) {
        if(c == '#'){
            map.put(input,map.getOrDefault(input,0)+1);
            input = "";
            return new ArrayList<>();
        }
        input+=c;
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->
        {
            if(a.count == b.count){
                return b.sentence.compareTo(a.sentence);
            }else{
                return a.count - b.count;
            }
        });
        //Parse the map and find the matching sentence with prefixe i.e. input
        for(String s: map.keySet()){
            if(s.startsWith(input)){
                //Add to priority queue
                pq.add(new Pair(s,map.get(s)));
                if(pq.size()>3){
                    pq.poll();
                }
            }
        }
        List<String> result = new ArrayList<>();
        while(!pq.isEmpty()){
            Pair p = pq.poll();
            result.add(0,p.sentence);
        }
        return result;
        }
            private class Pair{
            String sentence;
            int count;
            public Pair(String s, int c){
                sentence = s;
                count = c;
            }
        }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */