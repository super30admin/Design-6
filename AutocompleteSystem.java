//Time Complexity: O(n) where n is the number of sentences
//Space Complexity:O(n)
//LeetCode : passed all test cases


class AutocompleteSystem {
    HashMap<String,Integer> map = new HashMap<>();
    
    String input = "";
    public AutocompleteSystem(String[] sentences, int[] times) {
        for(int i = 0; i<times.length;i++){
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
        }
    }
    
    public List<String> input(char c) {
        if(c == '#'){
            map.put(input, map.getOrDefault(input, 0) + 1);
            input = "";
            return new ArrayList<>();
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->{
            if(a.count == b.count){
                return b.text.compareTo(a.text);
            }else{
                return a.count - b.count;
            }
        });
        input += c;
        
        
        for(String key : map.keySet()){
            if(key.startsWith(input)){
                pq.add(new Pair(key,map.get(key)));
                if(pq.size()>3){
                    pq.poll();
                }
            }
        }
        
        List<String> list = new ArrayList<>();
        
        while(!pq.isEmpty()){
            list.add(0,pq.poll().text);
        }
        
        return list;
    }
    

}
class Pair{
        String text;
        int count;
        public Pair(String text, int count){
            this.text = text;
            this.count = count;
        }
        
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */