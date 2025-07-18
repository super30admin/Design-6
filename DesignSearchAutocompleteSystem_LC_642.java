import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/* Brute force
maintain map with sentences and the frequency
Time Complexity - O(N)
Space Complexity - O(N)
Did this code successfully run on Leetcode :  Yes
Any problem you faced while coding this :  No
*/
public class DesignSearchAutocompleteSystem_LC_642 {
    HashMap<String, Integer> map;
    String searchTerm ;
    public DesignSearchAutocompleteSystem_LC_642(String[] sentences, int[] times) {
        this.map = new HashMap<>();
        this.searchTerm = "";
        for(int i = 0; i<sentences.length; i++) {
            String sentence = sentences[i];
            map.put(sentence, map.getOrDefault(sentence, 0) + times[i]);
        }
    }


    public List<String> input(char c) {
        if(c == '#'){
            map.put(searchTerm, map.getOrDefault(searchTerm, 0)+1);
            this.searchTerm = "";
            return new ArrayList<>();
        }
        this.searchTerm += c;
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            int cntA = map.get(a);
            int cntB = map.get(b);
            if(cntA == cntB) {
                int curr = b.compareTo(a); //for ascii comparision
                return curr;
            }
            return cntA- cntB;
        });
        for(String sentence : map.keySet()) {
            if(sentence.startsWith(searchTerm)) {
                pq.add(sentence);
                if(pq.size() > 3){
                    pq.poll();
                }
            }
        }
        List<String> output = new ArrayList<>();
        while(!pq.isEmpty()) {
            output.add(0, pq.poll());
        }
        return output;
    }
}
