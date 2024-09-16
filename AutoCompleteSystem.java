// Time Complexity: O(n + m) or O(n) Kindly correct me.
 
class AutocompleteSystem {
    HashMap<String, Integer> map; 
    String input; 
    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        input = ""; 
        for(int i=0; i< sentences.length; i++){ 
            map.put( sentences[i], map.getOrDefault( sentences[i], 0) + times[i]); 
        }
    }

    // need to create new priority queue each time user input a character to maintain the popularity frquency of the words
    public List<String> input(char c) {
        if(c == '#'){
            map.put(input, map.getOrDefault( input, 0) +1);
            input = "";
            return new ArrayList<>(); 
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> {
            if( a.frequency == b.frequency){
                return b.sentence.compareTo(a.sentence);
            }
                return a.frequency - b.frequency;

        });

        input += c;

        for(String s: map.keySet()){ 
            if (s.startsWith(input)){ 
                pq.add(new Pair(s, map.get(s)));
                if(pq.size() > 3){
                    pq.poll();
                }
            }
        }

        List<String> result = new ArrayList<>();

        while(!pq.isEmpty() ){ 
            result.add(0, pq.poll().sentence); 
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