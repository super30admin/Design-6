// Problem-1: Phone directory

class PhoneDirectory {
    HashSet<Integer> set;
    PriorityQueue<Integer> q;
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        set = new HashSet<>();
        q = new PriorityQueue<>();
        
        for(int i=0; i < maxNumbers; i++){
            set.add(i); q.add(i);
        }
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(q.isEmpty()) return -1;
            int numToReturn = q.poll();
            set.remove(numToReturn);
            return numToReturn;
        
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return set.contains(number);
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if(!set.contains(number)){ // need to check in the set to avoid adding duplicate elements in the queue
        q.add(number); set.add(number);
            
        }
    }
}



// problem -2: Autocomplete-Design

// HashMap + min PQ solution
// TC = O(n + m) where m is number of sentences and n is number of new inputs to maintain (for the input() method)
class AutocompleteSystem {
    HashMap<String, Integer> map; // frequency map to store sentences history
    String input; // new input that user is adding
    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        input = ""; 
        for(int i=0; i< sentences.length; i++){ 
            map.put( sentences[i], map.getOrDefault( sentences[i], 0) + times[i]); // creating frq map
        }
    }
    
    // need to create new priority queue each time user input a character to maintain the popularity frquency of the words
    public List<String> input(char c) {
        if(c == '#'){ // if this is the end of the sentence
            map.put(input, map.getOrDefault( input, 0) +1);
            input = "";
            return new ArrayList<>(); // return empty list when #
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> {
            if( a.frequency == b.frequency){
                return b.sentence.compareTo(a.sentence);
            }
                return a.frequency - b.frequency;
            
        });
        
        input += c;
        
        for(String s: map.keySet()){ // iterate all the sentences currently in the dictionary and check their prefixes with cur word
            if (s.startsWith(input)){ // maintaining min heap of top three elements only
                pq.add(new Pair(s, map.get(s)));
                if(pq.size() > 3){
                    pq.poll();
                }
            }
        }
        
        List<String> result = new ArrayList<>();
        
        while(!pq.isEmpty() ){ 
            result.add(0, pq.poll().sentence); // need to maintain ascending order
        }
        return result;
    }
    
    
    class Pair{
        String sentence;
        int frequency;
        Pair(String word, int count){
            sentence = word;
            frequency = count;
        }
    }
}

// HashMap + max PQ solution
// TC = O(n + mlog m) where m is number of sentences and n is number of new inputs to maintain (for the input() method)
class AutocompleteSystem {
    HashMap<String, Integer> map; // frequency map to store sentences history
    String input; // new input that user is adding
    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        input = ""; 
        for(int i=0; i< sentences.length; i++){ 
            map.put( sentences[i], map.getOrDefault( sentences[i], 0) + times[i]); // creating frq map
        }
    }
    
    // need to create new priority queue each time user input a character to maintain the popularity frquency of the words
    public List<String> input(char c) {
        if(c == '#'){ // if this is the end of the sentence
            map.put(input, map.getOrDefault( input, 0) +1);
            input = "";
            return new ArrayList<>(); // return empty list when #
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> {
            if( a.frequency == b.frequency){
                return a.sentence.compareTo(b.sentence);
            }
                return b.frequency - a.frequency;
            
        });
        
        input += c;
        
        for(String s: map.keySet()){ // iterate all the sentences currently in the dictionary and check their prefixes with cur word
            if(s.startsWith(input)){ // if the match exists in the dictionary
                pq.add( new Pair(s, map.get(s))); // adding the relevant words to input char in the pq to return 
            }
        }
        
        List<String> result = new ArrayList<>();
        
        while(!pq.isEmpty() && result.size() < 3){ // adding top three only
            result.add(pq.poll().sentence);
        }
        return result;
    }
    
    
    class Pair{
        String sentence;
        int frequency;
        Pair(String word, int count){
            sentence = word;
            frequency = count;
        }
    }
}
