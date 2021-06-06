//Time Complexity:O(n* avg length of each word)
//Space Complexity:O(n) n is the length of sentence
class AutocompleteSystem {
    HashMap<String, Integer> map;
    StringBuilder sb;
    public AutocompleteSystem(String[] sentences, int[] times) {
        map= new HashMap<>();
        sb= new StringBuilder();
        for(int i=0;i<sentences.length;i++){
            map.put(sentences[i], map.getOrDefault(sentences[i],0)+times[i]);
        }
    }
    
    public List<String> input(char c) {
        if(c=='#'){
            String s =sb.toString();
            map.put(s, map.getOrDefault(s,0)+1);
            sb= new StringBuilder();
            return new ArrayList<>();
        }
        sb.append(c);
        PriorityQueue<String> pq = new PriorityQueue<>((a,b)-> {
            if(map.get(a)==map.get(b)){
                return b.compareTo(a);
            }
            return map.get(a) - map.get(b);
        });
        for(String sentence : map.keySet()){
            String s=sb.toString();
            if(sentence.startsWith(s)){
                pq.add(sentence);
                if(pq.size()>3){
                    pq.poll();
                }
            }
        }
        List<String> res = new ArrayList<>();
        while(!pq.isEmpty()){
            res.add(0,pq.poll());
        }
        return res;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */