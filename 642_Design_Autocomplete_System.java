    /*  Explanation
    # Leetcode problem link : https://leetcode.com/problems/design-search-autocomplete-system/
    Time Complexity for operators : O(n min(str,input_string) log3)
    Extra Space Complexity for operators : o(n) .. hashmap
    Did this code successfully run on Leetcode : NA
    Any problem you faced while coding this : No
# Your code here along with comments explaining your approach
        # Basic approach : 
        # Optimized approach: 
                              
            # 1. 
                    A) 
    */ 
class AutocompleteSystem {

    String input_string;
    HashMap<String, Integer>  hm;
    public AutocompleteSystem(String[] sentences, int[] times) {
        hm = new HashMap<>();
        input_string = "";
        
        for(int x =0; x < sentences.length; x++){// O(n)
            hm.put(sentences[x], times[x]);
        }
    }
    
    public List<String> input(char c) {
        if(c == '#'){
            if(input_string != " "){
                hm.put(input_string, hm.getOrDefault(input_string, 0) + 1);
                input_string = "";
            }
            
            return new ArrayList<>();
        }
        
        input_string += c;
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> (a.repeat != b.repeat ? a.repeat - b.repeat : b.sentence.compareTo(a.sentence)));
        
        
        for(String str : hm.keySet()){ // O(n min(str,input_string) log3)
            if(str.startsWith(input_string)){
                pq.add(new Node(str, hm.get(str)));
            }
            
            if(pq.size()> 3)
                pq.poll();
        }
        
        List<String> output = new ArrayList<>();
        
        // only upto 3 elements gets popped from the priority queue so constsnt time.
        
        while(!pq.isEmpty() && output.size() < 3){
            output.add(pq.poll().sentence);
        }
        // below is constant as only 3 elements in list everytime
        Collections.reverse(output);
        
        return output;
    }
    
    public class Node{
        String sentence;
        int repeat;
        
        Node(String sentence, int repeat){
            this.sentence = sentence;
            this.repeat = repeat;
        }
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */