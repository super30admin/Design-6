// Time Complexity -
// Space Complexity - 
// This Solution worked on LeetCode


class AutocompleteSystem {
    HashMap<String,Integer> map = new HashMap<>();
    String search ="";
    public AutocompleteSystem(String[] sentences, int[] times) {   
        for(int i=0;i < sentences.length;i++){
            map.put(sentences[i],map.getOrDefault(sentences[i],0) + times[i]);
        }
    }
    
    public List<String> input(char c) {
        if(c == '#'){
            map.put(search,map.getOrDefault(search,0) + 1);
            search = "";
            return new ArrayList<>();
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> {
            if(a.count == b.count){
                return (a.sentence.compareTo(b.sentence));    
            }
            else{
                return (b.count - a.count);
            }
        });
        search += c;
        ArrayList<String> result = new ArrayList<>();
        for(String key : map.keySet()){
            if(key.startsWith(search)){
                pq.add(new Pair(key,map.get(key)));
            }
        }
        while(!pq.isEmpty() && result.size() < 3){
            Pair popped = pq.poll();
            result.add(popped.sentence);    
        }
        return result;
    }
    
    class Pair{
        String sentence;
        int count;
        public Pair(String sentence, int count){
            this.sentence = sentence;
            this.count = count;
        }
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
