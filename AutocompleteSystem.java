import java.util.*;
import java.util.List;

class AutocompleteSystem {
    //o(n) time
    HashMap<String, Integer> map;
    StringBuilder input;
    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        input = new StringBuilder();
        for(int i = 0; i < sentences.length; i++){
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
        }
    }
    
    public List<String> input(char c) {
        if(c == '#'){
            String in = input.toString();
            map.put(in, map.getOrDefault(in, 0) + 1);
            input = new StringBuilder();
            return new ArrayList<>();
        }
        input.append(c);
        PriorityQueue<String> pq = new PriorityQueue<>((a,b)->{
            if(map.get(a) == map.get(b)){
                return b.compareTo(a);
            } 
            return map.get(a) - map.get(b);
        });
        for(String key: map.keySet()){
            String in = input.toString();
            if(key.startsWith(in)){
                pq.add(key);
                if(pq.size() > 3){
                    pq.poll();
                }
            }
        }
        List<String> result = new ArrayList<>();
        while(!pq.isEmpty()){
            result.add(0, pq.poll());

        }
        return result;
    }
}
    

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */