package design;

public class Autocomplete_solution {
  
    class Pair{
        String sentence;
        int count;
        
        
        public Pair(String sentence, int count){
            this.sentence=sentence;
            this.count=count;
        }
    }
    HashMap<String , Integer> map;
    String input;
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        map=new HashMap<>();
        input="";
        for(int i=0;i<sentences.length;i++){
            map.put(sentences[i],map.getOrDefault(sentences[i],0)+times[i]);
        }
    }
    
    public List<String> input(char c) {
        //end of sentence
     if(c=='#') {
         map.put(input,map.getOrDefault(input,0)+1);
         input="";
         return new ArrayList();
     }
        
        input+=c;
        PriorityQueue<Pair> pq= new PriorityQueue<>((a,b)->{
           if(a.count==b.count) return b.sentence.compareTo(a.sentence);
            return a.count-b.count;
        });
        
        
        for(String key :map.keySet()){
            if(key.startsWith(input)){
    pq.add(new Pair(key, map.get(key)));
                if (pq.size()>3){
                    pq.poll();
                }
            }
        }
        
        
        
        
        List<String> result= new ArrayList<>();
        
        while(!pq.isEmpty()){
            result.add(0,pq.poll().sentence);
        }
        
        
        return result;
    }
}
