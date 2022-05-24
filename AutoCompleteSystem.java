//TC : O(n) - For adding sentences in Trie and Iterating over sentences array

//SC.: O(n) == Using priority queue and map
class AutocompleteSystem {
    
    class TrieNode{
        HashSet<String> list;
        HashMap<Character, TrieNode> child;
        
        public TrieNode(){
            list = new HashSet<>();
            child = new HashMap<>();
        }
    }
    TrieNode root;
    public void insertWord(String word){ //Adding a sentence in a Trie
        TrieNode curr = root;
        
        for(int i = 0; i< word.length(); i++){
            char ch = word.charAt(i);
            if(!curr.child.containsKey(ch)){
                curr.child.put(ch, new TrieNode() );
            }
            curr = curr.child.get(ch);
            curr.list.add(word);
        }
    }
     public Set<String> search(String word) { //Getting a Set associated with a Sentence in Trie
        TrieNode curr = root;
        for(int c = 0; c< word.length(); c++){
            char ch = word.charAt(c);
            if(!curr.child.containsKey(ch)){
                return new HashSet<>();
            }
            curr = curr.child.get(ch);
        }
        return curr.list;
    }
    
    
    
    
    
    HashMap<String, Integer> map; 
    PriorityQueue<String> pq; //Using min-Heap
    StringBuilder in;

    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        root = new TrieNode();
        pq = new PriorityQueue<>((a,b) ->  // Adding sentences in PriorityQueue
                                {if(map.get(a) == map.get(b)){
                                    return b.compareTo(a);
                                }
                                return map.get(a) - map.get(b);}
                                );
        for(int i = 0; i< sentences.length; i++){
            String curr = sentences[i];
            
            map.put(curr, map.getOrDefault(curr, 0) + times[i]);
            insertWord(curr);
            
        }
        
        in = new StringBuilder();
        
    }
    public List<String> input(char c) {
        if(c == '#'){
            String temp = in.toString();
            map.put(temp, map.getOrDefault(temp, 0) + 1);
            insertWord(temp);
            in = new StringBuilder();
            return new ArrayList<>();
        }
        
        in.append(c);
        
        String inputStr = in.toString();
        
        Set<String> tempSet = search(inputStr);
        for(String word : tempSet){
            if(word.startsWith(in.toString()) ){
                pq.add(word);
                if(pq.size() > 3)   pq.poll();
            }
        }
        
        List<String> result = new ArrayList<>();
        
        while(!pq.isEmpty()){
            result.add(0, pq.poll());
        }
        return result;
    }
}















/***

//Brute-Froce solution

class AutocompleteSystem {
    HashMap<String, Integer> map; 
    PriorityQueue<String> pq; //Using min-Heap
    StringBuilder in;

    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        pq = new PriorityQueue<>((a,b) -> 
                                {if(map.get(a) == map.get(b)){
                                    return b.compareTo(a);
                                }
                                return map.get(a) - map.get(b);}
                                );
        for(int i = 0; i< sentences.length; i++){
            String curr = sentences[i];
            
            map.put(curr, map.getOrDefault(curr, 0) + times[i]);
        }
        
        in = new StringBuilder();
        
    }
    public List<String> input(char c) {
        if(c == '#'){
            String temp = in.toString();
            map.put(temp, map.getOrDefault(temp, 0) + 1);
            
            in = new StringBuilder();
            return new ArrayList<>();
        }
        
        in.append(c);
        
        String inputStr = in.toString();
        for(String word : map.keySet()){
            if(word.startsWith(in.toString()) ){
                pq.add(word);
                if(pq.size() > 3)   pq.poll();
            }
        }
        
        List<String> result = new ArrayList<>();
        
        while(!pq.isEmpty()){
            result.add(0, pq.poll());
        }
        return result;
    }
}


 
 */