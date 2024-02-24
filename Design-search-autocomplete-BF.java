/*
Time Complexity: O(N log k)

Space Complexity: O(2N)

Did this code successfully run on Leetcode : Yes

Approach: Using a hashmap and min heap - storing the cache in the hashmap and having the hottest words
in the min heap to give out top 3 hottest searches.

Prob: 642. Design Search Autocomplete System

*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

class AutocompleteSystem {
    private HashMap<String,Integer> map;
    private StringBuilder inputStr;
    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map = new HashMap<>();
        this.inputStr = new StringBuilder();
        for(int i=0;i<sentences.length;i++){
            String sentence = sentences[i];
            int cnt = times[i];
            map.put(sentence, cnt); 
        }
    }
    
    public List<String> input(char c) {
        if(c == '#'){
            String in = inputStr.toString(); 
            inputStr = new StringBuilder();
            map.put(in,map.getOrDefault(in,0) + 1); 
            return new ArrayList<>();
        }
        inputStr.append(c);
        PriorityQueue<String> pq = new PriorityQueue<>((a,b) -> {
            int countA = map.get(a);
            int countB = map.get(b);
            if(countA == countB){
                return b.compareTo(a);
            }
            return countA - countB;
        });
        // find words starting with this inpurstr
        String prefix = inputStr.toString();
        for(String key : map.keySet()){
            if(key.startsWith(prefix)){
                pq.add(key);
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