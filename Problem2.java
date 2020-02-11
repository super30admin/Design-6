// Time Complexity - O(n) where n is the number of sentences in the HashMap
// Space Complexity - O(n) where n is the number of sentences in the HashMap
// This Solution worked on LeetCode


class AutocompleteSystem {
    HashMap<String,Integer> map = new HashMap<>();
    String search ="";
    public AutocompleteSystem(String[] sentences, int[] times) {   // Add the sentences in the input in the HashMap with times as values
        for(int i=0;i < sentences.length;i++){
            map.put(sentences[i],map.getOrDefault(sentences[i],0) + times[i]);
        }
    }
    
    public List<String> input(char c) {
        if(c == '#'){               // if the character is # add the search string in the HashMap
            map.put(search,map.getOrDefault(search,0) + 1);   // Increment the value if string already exists
            search = "";
            return new ArrayList<>();
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> { // Custom Comparater for Max Heap if count is different the greater count will be on top ; else if the count is same sort the string lexicographically
            if(a.count == b.count){
                return (a.sentence.compareTo(b.sentence));    
            }
            else{
                return (b.count - a.count);
            }
        });
        search += c;            // Add the new character to the Search String
        ArrayList<String> result = new ArrayList<>();
        for(String key : map.keySet()){
            if(key.startsWith(search)){         // Search the search string in the HashMap Key
                pq.add(new Pair(key,map.get(key))); // If string match found add the Key value pair to the Max Heap
            }
        }
        while(!pq.isEmpty() && result.size() < 3){  // Get the top 3 maxheap entries and add the sentences to the result
            Pair popped = pq.poll();
            result.add(popped.sentence);    
        }
        return result;
    }
    
    class Pair{                 // Create the Pair with sentence and count 
        String sentence;
        int count;
        public Pair(String sentence, int count){
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
