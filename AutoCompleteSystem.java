//TC : O(n log k)
//SC : O(1)

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
            map.put(searchString,map.getOrDefault(searchString,0)+1);
            searchString = "";
            return new ArrayList<>();
        }else{
            searchString += c;
            //Pq -> 
            PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->{
                if(a.count==b.count){
                    return a.sentence.compareTo(b.sentence);
                    
                }else{
                    return a.count-b.count;
                }
            });

            for(String key :map.KeySet()){
                if(key.startsWith(searchString)){
                    if(pq.size()< 3){
                        pq.add(new Pair(key,map.get(key)));
                    }
                    else{
                        
                        Pair top = pq.peek();
                        Pair incoming = new Pair(key,map.get(key));
                        if(top.count < incoming.count){
                            pq.poll();
                           
                        }
                        else if(top.count == incoming.count && incoming.sentence.compareTo(top.sentence)<0){
                            pq.poll();
                            
                        }
                        pq.add(incoming);

                    }
                    
                }
            }

            List<String> result = new ArrayList<>();

            while(!pq.isEmpty() && result.size()<3){
                result.add(0,pq.poll().sentence);
            }
            return result;

        }
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

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */