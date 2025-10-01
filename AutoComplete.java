// Time Complexity :  O(M) where M is the total number of unique sentences.
// Space Complexity : O(M) for storing all strings in the map


// Approach : keeping track of all sentences and then 
// counts using a map and When a character is typed, it checks for matches and uses a min-heap to always keep the top 3 by frequency 

class AutoComplete {
    HashMap<String, Integer> map;
    String search;

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.search = "";
        this.map = new HashMap<>();

        for(int i=0; i<sentences.length; i++){
            map.put(sentences[i], map.getOrDefault(sentences[i], 0)+ times[i]);
        }
    }
    
    public List<String> input(char c) {
        
        if(c == '#'){
            map.put(search, map.getOrDefault(search, 0)+1);
            search = "";
            return new ArrayList<>();
        }

        search += c;

        PriorityQueue<String> pq = new PriorityQueue<>((a,b) ->{
            if(map.get(a) == map.get(b)){
                return b.compareTo(a);
            }   
            return map.get(a) - map.get(b);
        });

        List<String> matches = new ArrayList<>();

        for(String sentence: map.keySet()){
            if(sentence.startsWith(search)){
                pq.add(sentence);
                if(pq.size() > 3){
                    pq.poll();
                }
            }
        }

        List<String> result = new ArrayList<>();

        while(!pq.isEmpty()){
            result.add(pq.poll());
        }

        Collections.reverse(result);
        return result;
    }
}
