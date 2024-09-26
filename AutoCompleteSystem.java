// Time Complexity : O(N*l*log 3) no of sentences*length of input*log 3 for inserting in min heap
// Space Complexity :O(N) map of all sentences + O(3) pq
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
// maintain hashmap of all sentences and their count, on keystroke check across all if matches
// on a match add to min heap if length>3 poll()
// comparator of min heap puts lesser count on top and higher lexicographical string on top

class AutocompleteSystem {
    
    HashMap<String, Integer> map;
    StringBuilder sb;
    String input = "";
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        sb = new StringBuilder();
        for(int i=0; i<sentences.length; i++){
            map.put(sentences[i], times[i]);
        }
    }
    
    public List<String> input(char c) {
        if(c=='#'){
            map.put(input, map.getOrDefault(input, 0)+1);
            sb.setLength(0);
            return new ArrayList<>();
        }
        sb.append(c);
        
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> {
            if(a.count==b.count){
                return -1*a.sentence.compareTo(b.sentence);
            }
            else{
                return a.count - b.count;
            }
        });
        
        input = sb.toString();
        for(String s: map.keySet()){
            
            if(s.startsWith(input)){
                pq.add(new Pair(s, map.get(s)));
                if(pq.size()>3){
                    pq.poll();
                }
            }
        }
        
        List<String> result = new ArrayList<>();
        while(!pq.isEmpty()){
            Pair p = pq.poll();
            result.add(0, p.sentence);
        }
        
        return result;
    }
    
    private class Pair{
        String sentence;
        int count;

        public Pair(String s, int c){
            sentence = s;
            count = c;
        }
    }
}