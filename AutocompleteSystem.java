// Time Complexity : O(N log k)-> O(N) -> N is the number of sentences and log k is very small
// Space Complexity : O(N)
// Did this code successfully run on Leetcode : Yes

//with HashMap and PriorityQueue
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class AutocompleteSystem {
    HashMap<String, Integer> map;
    String search;

    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        search = "";
        for(int i=0; i<sentences.length; i++){
            map.put(sentences[i],map.getOrDefault(sentences[i],0)+times[i]);
        }
    }

    public List<String> input(char c) {
        List<String> result = new ArrayList<>();
        if(c =='#'){
            // add the searched string to the HashMap
            map.put(search,map.getOrDefault(search,0)+1);
            // clear the search
            search = "";
            return result;
        }
        search += c;
        PriorityQueue<String> pq = new PriorityQueue<>((String a, String b)->{
            if(map.get(a) == map.get(b))
            {
                return b.compareTo(a);
            }
            return map.get(a) - map.get(b);
        });

        for(String sentence: map.keySet())
        {
            if(sentence.startsWith(search))
            {
                pq.add(sentence);
                if(pq.size()>3)
                {
                    pq.poll();
                }
            }
        }

        while(!pq.isEmpty())
        {
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