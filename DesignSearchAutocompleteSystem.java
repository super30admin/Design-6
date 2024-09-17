/*
Approach: Using hashmap, priority queue (min heap)

We will maintain a hashmap and a priority queue. A hashmap will store the history of sentences that we have encountered so far and priority queue will give us the top 3 sentences with the highest count or lexicographically if they have same count.

We will first store all the inital sentences and their count in the hashmap while initializing the system.
We will then intialize  our input with an empty string and we will keep appending to it the characters that will be encountered when user inputs them.

Now when use enters new character we check,

"#" -> end of the sentence. Now if our hashmap contains the same sentence then increment its count else create a new <key,value> pair as a <sentence,1>.

other character -> append it to the input string. Check if any sentence starts from that string in hashmap key set. If yes then we enter that <sentence,count> pair into the min heap. If at any time heap size increases to 3, we will poll from the heap. Thus maintaining the top 3 frequent sentences starting with our given input.

TC: O(n x m); n = length of the hashmap; m = avg size of a sentence
SC: O(n)
*/


class AutocompleteSystem {
    
    HashMap<String, Integer> map;
    String input;
    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        
        for(int i = 0; i < sentences.length; i++){
            map.put(sentences[i], map.getOrDefault(sentences[i] , 0) + times[i]);
        }
        
        input = "";
    }
    
    public List<String> input(char c) {
        if (c == '#'){
            map.put(input, map.getOrDefault(input, 0) + 1);
            input = "";
            return new ArrayList<>();
        }
        
        input += c;
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> {
            if (a.count == b.count){
                return b.sentence.compareTo(a.sentence);
            }
            return a.count - b.count;
        });
        
        for(String s: map.keySet()){
            if(s.startsWith(input)){
                pq.add(new Pair(s, map.get(s)));
                if(pq.size() > 3){
                    pq.poll();
                }
            }
        }
        
        List<String> result = new ArrayList<>();
        
        while (!pq.isEmpty()){
            result.add(0, pq.poll().sentence);
        }
        
        return result;
    }
    
    class Pair{
        String sentence;
        int count;
        private Pair(String sentence, int count){
            this.sentence = sentence;
            this.count = count;
        }
    }
}


/*
Approach: Using hashmap, priority queue and trie

In previous apporach one the bottleneck was we have to search through the entire hashmap and check all the sentences that starts with the given input.

In this apporach only this we will eliminate this bottleneck. We will maintain certain structure which will give us only the <sentence,count> pairs starting with given input. 

We will maintain a trie where each node will contain a hashmap having <sentence,count> pairs of all the sentences starting with sentence that made till that point in the tree. For example

                        i
                       / \
                      _   s
                     /     \
                    l       l
                   /         \
                  o           a
                 /             \
                v               n
               /                 \
              e                   d
                  
Node "i" will contain a hashmap having <sentence,count> pairs of all the sentences starting with "i" .
Node "s" will contain a hashmap having <sentence,count> pairs of all the sentences starting with "is".
Node "l" will contain a hashmap having <sentence,count> pairs of all the sentences starting with "isl" 
and so on...


TC: TC: (n x m)
SC: O(n)
*/
class AutocompleteSystem {
    
    class TrieNode{
        HashMap<String, Integer> map;
        TrieNode[] children;
        
        private TrieNode(){
            map = new HashMap<>();
            children = new TrieNode[256];
        }
    }
    
    TrieNode root;
    
    // TC: O(m); m = length of longest string
    private void insert(String sentence, int count){
        TrieNode curr = root;
        for (int i = 0; i < sentence.length(); i++){
            char c = sentence.charAt(i);
            if(curr.children[c - ' '] == null){
                curr.children[c - ' '] = new TrieNode();
            }
            
            curr = curr.children[c - ' '];
            curr.map.put(sentence, curr.map.getOrDefault(sentence, 0) + count);
        }
    }
    
    // TC: O(m); m = length of longest string
    private HashMap<String, Integer> search(String sentence){
        TrieNode curr =  root;
        for (int i = 0; i < sentence.length(); i++){
            char c = sentence.charAt(i);
            if(curr.children[c - ' '] == null){
                return new HashMap<>();
            }
            curr = curr.children[c - ' '];     
        }
        return curr.map;
    }
    
    String inp;
    
    // TC: O(n x m); where m = avg length of the string; n = no of sentences
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();    
        for(int i = 0; i < sentences.length; i++){
            insert(sentences[i], times[i]);
        }
        inp = "";
    }
    
    // TC: (n log k)
    public List<String> input(char c) {
        if (c == '#'){
            insert(inp, 1); // O(m)
            inp = "";
            return new ArrayList<>();
        }
        
        inp += c;
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> {
            if (a.count == b.count){
                return b.sentence.compareTo(a.sentence);
            }
            return a.count - b.count;
        });
        
        HashMap<String, Integer> map = search(inp); // O(m)
        for(String s: map.keySet()){
            if(s.startsWith(inp)){
                // O(n log k); where n = length of the hashmap searched; k = size of the pq
                pq.add(new Pair(s, map.get(s))); 
                if(pq.size() > 3){
                    pq.poll();
                }
            }
        }
        
        List<String> result = new ArrayList<>();
        
        while (!pq.isEmpty()){
            result.add(0, pq.poll().sentence);
        }
        
        return result;
    }
    
    class Pair{
        String sentence;
        int count;
        private Pair(String sentence, int count){
            this.sentence = sentence;
            this.count = count;
        }
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */