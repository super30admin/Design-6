// "static void main" must be defined in a public class.
public class Main {
    
    private class TrieNode {
        HashMap<Character, TrieNode> children;
        HashMap<String, Integer> count;
        
        public TrieNode(){
            children = new HashMap<>();
            count = new HashMap<>();
        }
    }
    
    private TrieNode root = new TrieNode();
    StringBuilder sb = new StringBuilder();
    
    private void add(String s, int time){
        TrieNode curr = root;
        for(char c : s.toCharArray()){
            if(!curr.children.containsKey(c)){
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
            count.put(s, curr.count.getOrDefault(s,0)+time);
        }
    }
    
    private HashMap<String, Integer> search(String s){
        TrieNode curr = root;
        for(char c: s.toCharArray()){
            if(!curr.children.containsKey(c))
                return null;
            curr = curr.children.get(c);
        }
        return curr.count;
    }
    
    public AutocompleteSystem(String[] sentences, int[] times){
        // Create Trie
        for(int i = 0; i < sentences.length; i++){
            add(sentences[i], times[i]);
        }
    }
    
    public List<String> input(char c){
        if(c == '#'){
            String input = sb.toString();
            add(input, 1);
            
            sb = new StringBuilder();
            return new ArrayList<>();
        }
        
        sb.append(c);
        String input = sb.toString();
        
        HashMap<String, Integer> map = search(input);
        if(map == null)
            return new ArrayList<>();
        
        // Heap - PQ - MinHeap
        Queue<String> minHeap = new PriorityQueue<>((a,b) -> {
            if(map.get(a) == map.get(b))
                return b.compareTo(a);  // because we want dict in sorted order
            return map.get(a) - map.get(b); // normal sorting for minHeap
        });
        
        // Get top 3 values
        for(String s : map.keySet()){
            minHeap.add(s);
            if(minHeap.size() > 3)
                minHeap.poll();
        }
        
        // Prepare answer
        ArrayList<String> result = new ArrayList<>();
        while(!minHeap.isEmpty()){
            result.add(0, minHeap.poll());      // since its a minheap - we want answers with highest count first.
        }
        
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}