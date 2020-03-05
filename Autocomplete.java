/*
 * Time complexity : O(N log k)
 * Space complexity : O(N)
 */

class AutocompleteSystem {

    HashMap<String, Integer> map;
    
    String search;
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        
        search = "";
        
        int i = 0;
        for(String s : sentences){
            map.put(s, map.getOrDefault(s, 0) + times[i]);
            i++;
        }
    }
    
    public List<String> input(char c) {
        List<String> result = new ArrayList<>();
        
        if(c == '#'){
            map.put(search, map.getOrDefault(search, 0) + 1);
            search = "";
            return result;
        }
        
        search = search + c;
        
        PriorityQueue<Pair> queue = new PriorityQueue<>(3, (a, b) -> {
            if(a.degree == b.degree){
                return b.sentence.compareTo(a.sentence);
            }    
            
            return a.degree - b.degree;
        });
        
        
        for(String key : map.keySet()){
            if(key.startsWith(search)){
               
                 if(queue.size() < 3){
                    queue.add(new Pair(map.get(key), key));    
                }else{
                     
                    System.out.println("Size is greater");
                    Pair p = queue.peek();
                     
                    System.out.println(p.degree + " : "+p.sentence);
                    Pair nextPair = new Pair(map.get(key), key);
                    
                    if(p.degree < nextPair.degree){
                        queue.poll();
                        queue.add(nextPair);
                    }else if(p.degree == nextPair.degree && nextPair.sentence.compareTo(p.sentence) < 0){
                        queue.poll();
                        queue.add(nextPair);
                    }
                }
            }
        }
        
        int i = 0;
        while(i < 3 && !queue.isEmpty()){
            result.add(0, queue.poll().sentence);
            i++;
        }
        
        return result;
        
    }
    
    class Pair{
        int degree;
        String sentence;
        
        Pair(int count, String sentence){
            this.degree = count;
            this.sentence = sentence;
        }
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */