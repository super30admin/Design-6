// Time Complexity : O(n) n is sentences * times, Can be done using Trie also!
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None

import java.util.*;

class AutocompleteSystem {
    HashMap<String, Integer> map;
    StringBuilder sb;
    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map = new HashMap<>();
        this.sb = new StringBuilder();
        for(int i=0; i<sentences.length;i++){
            String sentence = sentences[i];
            map.put(sentence, map.getOrDefault(sentence,0)+times[i]);
        }
    }

    public List<String> input(char c) {
        if(c=='#'){
            String search = sb.toString();
            map.put(search, map.getOrDefault(search,0)+1);
            this.sb = new StringBuilder();
            return new ArrayList<>();
        }
        sb.append(c);
        PriorityQueue<String> pq = new PriorityQueue<>((a,b) -> {
            int ca = map.get(a);
            int cb = map.get(b);

            if(ca==cb){
                return b.compareTo(a);
            }
            return ca-cb;
        });
        String search = sb.toString();
        for(String key: map.keySet()){
            if(key.startsWith(search)){
                pq.add(key);
            }
            if(pq.size()>3){
                pq.poll();
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