//TC : O(n log n)
//SC : O(n)

import java.util.HashMap;

class AutocompleteSystem {

    HashMap<String,Integer> map;
    String searchString;


    public AutocompleteSystem(String[] sentences, int[] times) {
        searchString="";
        map = new HashMap<>();
        //Creating Hashmap
        for(int i=0;i<sentences.length;i++){
            map.put(sentences[i],map.getOrDefault(sentences[i], 0)+times[i]);
        }
    }
    

    public List<String> input(char c) {

        if(c == '#'){
            map.put(searchString,map.getOrDefault(searchString, 0)+1); //increase the count of occurence in map
            searchString = ""; //reset searchstring
            return new ArrayList<>();
        }

        //For other incomplete search strings

        //Create a priority queue
        PriorityQueue<Pair>  pq= new PriorityQueue<>((a,b)->{
            if(a.count==b.count)
                return a.sentence.compareTo(b.sentence);
            else
                return b.count-a.count;
        });


        //Add the character to searchString
        searchString +=c;

        //Go through map and find the top priority, add it to PQ
        for(String key : map.keySet()){
            if(key.startsWith(searchString))
                pq.add(new Pair(key,map.get(key))); //n log n

        }
        List<String> result = new ArrayList<>();
        while(!pq.isEmpty() && result.size()<=3){
            result.add(pq.poll().sentence);
        }
        return result;

    }

    
    class Pair{
        String sentence;
        int count;
        Pair(String sentence, int count){
            this.sentence = sentence;
            this.count = count;
        }
    }
}

