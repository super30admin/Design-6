/*
 * Time Complexity: O(nlogn + n) Heapify Operation included
 * Space Complexity: O(n)
 * */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class AutocompleteSystem {
    Map<String, Integer> map;
    String search;
    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        search = "";
        for(int i = 0; i < sentences.length; i++){
            String curr = sentences[i];
            map.put(curr, map.getOrDefault(curr, 0) + times[i]);
        }
    }
    
    public List<String> input(char c) {
        if(c == '#'){
            map.put(search, map.getOrDefault(search, 0) + 1);
            search = "";
            return new ArrayList<>();
        }
        search += c;
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            if(a.count == b.count){
                return a.sentence.compareTo(b.sentence);
            }
            return b.count - a.count;
        });
        for(String key: map.keySet()){
            if(key.startsWith(search)){
                pq.add(new Pair(key, map.get(key)));
            }
        }
        List<String> result = new ArrayList<>();
        while(!pq.isEmpty() && result.size() < 3){
            Pair p = pq.poll();
            result.add(p.sentence);
        }
        return result;
    }
    
    class Pair{
        String sentence; int count;
        public Pair(String sentence, int count){
            this.sentence = sentence;
            this.count = count;
        }
    }
}