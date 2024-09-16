// Time Complexity : AutocompleteSystem: O(n)
//                   input:  O(n logn)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
class AutocompleteSystem {
    
    Map<String, Integer> mapFreq = new HashMap<>();
    String input_str = "";
    

    public AutocompleteSystem(String[] sentences, int[] times) {
        for(int i=0; i<sentences.length; i++){
            mapFreq.put(sentences[i], times[i]);
        }  
    }
    
    public List<String> input(char c) {
        if(c=='#'){ 
            List<String> res = new ArrayList<>();
			mapFreq.put(input_str, mapFreq.getOrDefault(input_str, 0) + 1);
			input_str="";
			return res;
        }
        
        input_str += c;
        PriorityQueue<Node> maxHeap = new PriorityQueue<>( (a,b) -> a.count==b.count ? 
                                                         a.sentence.compareTo(b.sentence) :
                                                         b.count - a.count);
        
        for(String key : mapFreq.keySet()){
            if (key.startsWith(input_str)){
                maxHeap.add(new Node(key, mapFreq.get(key))); 
            }
        }
        
        List<String> res = new ArrayList<>();
        
        while(!maxHeap.isEmpty() && res.size()<3){
            res.add(maxHeap.remove().sentence);
        }
        return res; 
    }
}

class Node{
    String sentence;
    int count;
    public Node(String sentence, int count){
        this.sentence = sentence;
        this.count = count;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
