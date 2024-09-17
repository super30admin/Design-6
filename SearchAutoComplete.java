/*
Brute Force: Using HashMap:
*/
class AutocompleteSystem {
    //map store (sentence, count) where count is number of times sentence has been typed earlier.
    Map<String, Integer> database; 
    // current search word
    String data = ""; 
    
    public AutocompleteSystem(String[] sentences, int[] times) {  
        database = new HashMap<>(); 
        // initialize database
        for (int i = 0; i < sentences.length; i++) { 
            database.put(sentences[i], database.getOrDefault(sentences[i], 0) + times[i]);
        }
    }
    
    /*
    Time Complexity input function/search: O(N + mlogm)
    where N is number of sentences, here I am iterating over the map which has N sentences stored 
    and I am getting all the sentences which starts with given input. Lets say we have m sentences starting 
    with given query
    Now to sort these sentences according to their occurance count and ascii value need MlogM time.
    */
    public List<String> input(char c) {
        //search char is #, update db with incrementing the count of current search data & clean the data
        if ('#' == c) { 
            database.put(data, database.getOrDefault(data, 0) + 1);
            data = "";
            return new ArrayList<>();
        }
        //add current char to search query
        data += c;
        // min heap to return the top 3 historical hot sentences that have prefix the 
        //same as the part of sentence already typed
        PriorityQueue<Pair> queue = new PriorityQueue<>((a, b) -> (a.count == b.count ? a.sentence.compareTo(b.sentence) : b.count - a.count));
        
        // iterate over all the sentence in the database and use startsWith to compare two strings
        //sentence which starts with current search query, add a pair of sentence and count in the minheap
        for (String s : database.keySet()) {
            if (s.startsWith(data))
                queue.offer(new Pair(s, database.get(s)));
        }
        //add the 1st three sentence priority queue in the result list
        List<String> result = new ArrayList<>();
        while(!queue.isEmpty() && result.size() < 3) {
            Pair a = queue.poll();
            result.add(a.sentence);
        }
        return result;
    }
    //pair class to store sentence and count
    private class Pair {
        String sentence;
        int count;
        public Pair(String s, int c) {
            sentence = s;
            count = c;
        }
    }
}

/*
Trie Approach:
Time Complexity: O(p+q+mlogm) time. 
Here, p is length of current search query, q is to the number of nodes in the trie considering the sentence formed till now as the root node. 
Again, I need to sort the list of length m indicating the options available for current search query, which takes O(mlogm) time.
*/
class AutocompleteSystem {
    private class Pair{
        String s;
        int count;
        public Pair(String s, int count){
            this.s = s;
            this.count = count;
        }
    }
    private class Trie{
        TrieNode root;
        public  class TrieNode{
            Map<Character, TrieNode> children;
            Map<String, Integer> count;
            public TrieNode(){
                children = new HashMap<>();
                count = new HashMap<>();
            }
        }
        public Trie(){
            root = new TrieNode();
        }
        private void add(String s, int count){
            TrieNode cur = root;
            for(char ch : s.toCharArray()){
                if(!cur.children.containsKey(ch))
                    cur.children.put(ch, new TrieNode());
                cur = cur.children.get(ch);
                cur.count.put(s, cur.count.getOrDefault(s,0)+count);
            }
        }
        private Map<String, Integer> search(String s){
            TrieNode cur = root;
            for(char c: s.toCharArray()){
                if(!cur.children.containsKey(c)) 
                    return null; 
                cur = cur.children.get(c);
            }
            return cur.count;
        }
    }
    //member variables
    Trie trie;
    String input;
    /*
    Now, for implementing the AutoComplete function, I will consider each character of the every word 
    given in sentences array, and add an entry corresponding to each such character at one level of the trie. 
    At the leaf node of every word, I will update the count with the corresponding number 
    of times this sentence has been typed.
    */
    public AutocompleteSystem(String[] sentences, int[] times) {
        trie = new Trie();
        input ="";
        //build trie, add sentences to trie and save frequency count to the 
        buildTrie(sentences, times);
    }
    private void buildTrie(String[] sentences, int[] times){
        for(int i=0; i<sentences.length; i++){
            trie.add(sentences[i], times[i]);
        }
    }
    private void buildHeap( Map<String, Integer> count, Queue<Pair>q){
        for(String s: count.keySet()){
            q.add(new Pair(s, count.get(s)));
        }
    }
    public List<String> input(char c) {
        //base case
        //The user finished the input, save the sentence "i a" as a historical sentence in system.
        //and this input will be counted as a new search.
        if(c == '#'){
            trie.addWord(input , 1);
            //reset
            input = "";
            return new ArrayList<>();
        }
        ///And the 
        input =  input+c;
        
        //get the frequency count of all the sentences which starts with current input 
        Map<String, Integer> frequencyMap  = trie.search(input);
        
        //There are no sentences that have prefix with current input.
        if(frequencyMap == null)  return new ArrayList<>();
        
        //max heap
        Queue<Pair>q =new PriorityQueue<>((a,b)->(a.count==b.count) ? a.s.compareTo(b.s) : b.count-a.count);
        
        //build heap
        buildHeap(frequencyMap, q);
        
        List<String> res  = new ArrayList<>();
        
        int i=0;
        while(i < 3 && !q.isEmpty()){
            res.add(q.remove().s);
            i++;
        }
        return res;
    }
}




/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
