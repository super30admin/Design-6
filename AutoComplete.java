// Time Complexity : 
/*              Approach 1: Use HashMap : O(input string length) calls * O(nlog k) where n is the number of sentences and k is the heap size as 3.
                Approach 2: Use Tries:  O(input string length) calls * O(mnlog k) wheer n is number of sentences and m is avg sentence length
// Space Complexity : 
                Approach 1: Use HashMap : O(n) where n is the number of sentences in the hashmap
                Approach 2: Use Tries:  O(m) where m <= n (n is the number of sentences and m is the possible number of sentences tries will make nodes for) 
// Did this code successfully run on Leetcode : NO (tries approach didnt work)
// Any problem you faced while coding this : Tries DIDNT Work. Can you please review the mistake?
/* Algorithm: In hashmap solution, put the sentences and the occurance numbers. Whenever the character is typed, append it into the search string since
we are going to search the complete string one by one, The priority queue will store the pairs of sentences and the counts, if we use min heap we can
optimise. If the count of the occurances are equal, compare the lexicographic ordering of the strings. If the count is greater, put that into the min heap
and poll the top of the queue. If the character is # denotes end of the input and return the result got till now after adding it to the hashmap.
*/
// APPROACH 1 : HASHMAP
class AutocompleteSystem {
    class Pair{
        String s;                                                                   // Pair of sentence and count
        int count;
        Pair(String se, int c){
            this.s = se;
            this.count = c;
        }
        public String getSentence(){return this.s;}
        public int getCount(){return this.count;}
        
    }
    HashMap<String, Integer> store;
    public AutocompleteSystem(String[] sentences, int[] times) {
        store = new HashMap<>();
        for(int i =0; i < sentences.length; i++){
            store.put(sentences[i], times[i]);                                                          // Map of sentences and times
        }
    }   
    String search ="";                                                                          // Global input query
    public List<String> input(char c) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)-> {
                if(a.count == b.count){
                    return b.s.compareTo(a.s);                                                                      // Min heap equal count
                }
            return a.count - b.count;});                                                                // Min heap
        List<String> result = new ArrayList<>();
        if(c == '#'){
            store.put(search, store.getOrDefault(search, 0) + 1);                                                   // Enter the search string into map
            search = "";
            return result;}                                                                                     // Return result got till now
        else{
        search += c;                                                                                // The search string length increases
        for(String s: store.keySet()){
            if(s.startsWith(search)){
            if(pq.size() < 3){
                pq.add(new Pair(s, store.get(s)));                                                      
            } else {
                if(pq.peek().getCount() == store.get(s)){                                                               // If PQ peek and incoming sentence count is equal
                    if(pq.peek().getSentence().compareTo(s) > 0){
                        pq.poll();
                        pq.add(new Pair(s, store.get(s)));                                                          // Put the lexicographic pair
                    }
                } else
                if(pq.peek().getCount() < store.get(s)){
                    pq.poll();                                                                                  // Incoming sentence count is greater
                    pq.add(new Pair(s, store.get(s)));                                                              // Put the new sentence pair
                }
            }
        }
        }
        while(!pq.isEmpty()){
            Pair p = pq.poll();
            result.add(0, p.getSentence());                                                 // Put from PQ to the result from the start position
        }
    }
        return result;
    }
}

// APPROACH 2: TRIES
class AutocompleteSystem {
    class Pair{                                                                                             // Pair of sentence and times
        String s;
        int count;
        Pair(String s, int c){
            this.s =s;
            this.count = c;
        }
        public String getSt(){return this.s;}
        public int getC(){return this.count;}
        }
    class TrieNode{
        int count;
        TrieNode[] children;                                                                                        // TrieNode of count and children
        TrieNode(){
            children = new TrieNode[128];
        }
    }
    TrieNode root = new TrieNode();                                                                                         // Root 
    TrieNode head = root;
    String[] sentence;
    public AutocompleteSystem(String[] sentences, int[] times) {
        for(int i = 0; i < sentences.length; i++){
            insert(sentences[i]);                                                                            // Insert the sentences in form of trie node
            root.count = times[i];                                                                      // the last trie Node (last character of every sentence) we store the times/frequency at the end of the sentence
        }
        sentence = sentences;                                                                                   // Keep a Global Copy of sentences to use it in other function
    }
    public void insert(String sentence){
        root = head;
        for(int j  = 0; j < sentence.length(); j++){
           char c = sentence.charAt(j);                                                         // Insert the characters of the sentence in form of the trie nodes
           if(root.children[c] == null){
               root.children[c] = new TrieNode();
           }
            root = root.children[c];
        }
    }
    String search ="";
    public List<String> input(char c) {
        List<String> result = new ArrayList<String>();
        if(c == '#'){
            insert(search);                                                                                 // Insert the search string as trie nodes
            search = "";
            return result;
        } else{
            PriorityQueue<Pair> pq = new PriorityQueue<Pair>((a,b) -> {
                if(a.count == b.count){
                    return b.s.compareTo(a.s);
                }
                return a.count-b.count;                                                                                                 // Min heap
            });
            search+=c;
            for(int i = 0; i < sentence.length;i++){                                                        // Use the global copy of sentence array
            root=head;
            if(sentence[i].startsWith(search)){                                                             // If the setnece starts with search string
            for(int j  = 0; j < sentence[i].length(); j++){
                c = sentence[i].charAt(j);
                root = root.children[c];                                                                    // Reach till the end of the sentence in the Trie
            }
            if(pq.size() < 3){
            pq.add(new Pair(sentence[i], root.count));                                                      // Add the sentence count and sentence as a Pair to the PQ
            } else{
                if(pq.peek().getC() == root.count){
                    if(pq.peek().getSt().compareTo(sentence[i]) > 0){                                       // Put the lexicographic sentence pair in the PQ
                        pq.poll();
                        pq.add(new Pair(sentence[i], root.count));
                    }
                } else 
                if(pq.peek().getC() < root.count){                                                          // If the count of the sentence is greater put it into the queue
                    pq.poll();
                    pq.add(new Pair(sentence[i], root.count));
                }
                }
            }
        }
            while(!pq.isEmpty()){
                Pair p = pq.poll();
                result.add(0, p.getSt());                                                                   // Put all the sentences from PQ to the result
            }
    }
        return result;
    }
}



