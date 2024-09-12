// Time Complexity :  O(n^2) becuase of using startsWith within forloop
// Space Complexity : O(n) 
// Did this code successfully run on Leetcode : Yes Its working
// Any problem you faced while coding this : No
class AutocompleteSystem {

    HashMap<String,Integer> map = new HashMap<>();
    String input_tracker  = "";
    public AutocompleteSystem(String[] sentences, int[] times) {
        for(int x = 0 ; x <sentences.length;x++)
        {
            map.put(sentences[x],times[x]);
        }
    }
    
    public List<String> input(char c) {
        if(c!='#')
        {
            input_tracker+=c;
            PriorityQueue<Pair<String,Integer>> pq = new PriorityQueue<>(
                (a,b)->{
                            return a.getValue()!=b.getValue()?b.getValue()-                                             a.getValue():a.getKey().compareTo(b.getKey());
                        }
            );
            
            for(String s:map.keySet())
            {
                if(s.startsWith(input_tracker))
                {
                    pq.add(new Pair(s,map.get(s)));
                }
            }
            
            List<String> out = new ArrayList<>(3);
            while(!pq.isEmpty() && out.size() <3) out.add(pq.poll().getKey());
            return out;
        }
        else
        {
            map.put(input_tracker,map.getOrDefault(input_tracker,0)+1);
            input_tracker ="";
            return new ArrayList<String>();
        }
        
    
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */