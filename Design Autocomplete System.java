// Time Complexity : O(N*M + NlogK)
// Space Complexity : O(N*N*M)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
// I am taking 2 hashes on any given key value. The first hash is to find out the bucket where the (k,v) is to be placed and the second hash is to find the location within the bucket where the element is to be placed.
class TrieNode{
    TrieNode[] children;
    HashMap<String,Integer> words;
    public TrieNode(){
        this.children = new TrieNode[128];
        this.words = new HashMap<>();
    }
}
class AutocompleteSystem {
    TrieNode root;
    StringBuilder sb;
    HashMap<String,Integer> map;
    private void insert(String word,int times){
        TrieNode curr = root;
        for(int i = 0 ; i < word.length() ; i++){
            char c = word.charAt(i);
            if(curr.children[c-' '] == null){
                curr.children[c-' '] = new TrieNode();
            }
            curr = curr.children[c-' '];
            curr.words.put(word,curr.words.getOrDefault(word,0)+times);
        }
    }
    private HashMap<String,Integer> search(String word){
        TrieNode curr = root;
        for(int i = 0 ; i < word.length() ; i++){
            char c = word.charAt(i);
            if(curr.children[c-' '] == null){
                return new HashMap<>();
            }
            curr = curr.children[c-' '];
        }
        return curr.words;
    }
    public AutocompleteSystem(String[] sentences, int[] times) {
        
        sb = new StringBuilder();
        root = new TrieNode();
        for(int i = 0 ; i < sentences.length ; i++){
            insert(sentences[i],times[i]);
        }
        
    }
    
    public List<String> input(char c) {
        if(c == '#'){
            insert(sb.toString(),1);
            sb = new StringBuilder();
        }else{
            sb.append(c);
            HashMap<String,Integer> resultMap = search(sb.toString());
            PriorityQueue<String> pq = new PriorityQueue<>((a,b)->{
                if(resultMap.get(a)==resultMap.get(b)) return b.compareTo(a);
                return resultMap.get(a)-resultMap.get(b);
            });
            for(String sentence : resultMap.keySet()){
                pq.add(sentence);
                if(pq.size() > 3) pq.poll();
            }
            List<String> result = new ArrayList<>();
            while(!pq.isEmpty()){
                result.add(0,pq.poll());
            }
            return result;
        }
        return new ArrayList<>();
        
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
