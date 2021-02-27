//Problem 126: Design Search Autocomplete System
//TC:O(n*l*log3)=>n*l//n for the map, l for 'starts' with function
//SC:O(n), for hashmap

/*
BruteForce:
//TC:O(n*l*log3)-n*l//n for the map, l for 'starts' with function
//SC:O(n), for hashmap
 Each sentence and its frequency count are stored in the hashmap and then each input is checked and traversed throught the hashmap to get the matching string and top 3 strings are returned

If the input char is '#'' it means end of the string and screen has to be cleared/reset, then the string prefix is added to the hashmap along with its frequency and an empty array list is returned.
  
*/

import java.util.*;
class AutocompleteSystem {
    //n for number of sentences
    //l for current string length
   //TC:O(n*l*log3)-n*l//n for the map, l for starts with function
   //SC:O(n), for hashmap
   Map<String,Integer> map;
   StringBuilder sb;
   PriorityQueue<Pair> minHeap;
   public AutocompleteSystem(String[] sentences, int[] times) {
       sb  = new StringBuilder();
       map = new HashMap<>();
       minHeap = new PriorityQueue<>((a,b)->{
             if(a.count==b.count){
                 return b.sentence.compareTo(a.sentence);//because here lexicographically bigger sentence have to be removed , so it should be above the lower one i.e I did sorting in descending order because if sentence has to be removed from the heap the it should be the sentence with bigger lexicographical order.[so max should be on top]
             }
           return a.count-b.count;
       });
       
       for(int i=0;i<sentences.length;i++){
           map.put(sentences[i],map.getOrDefault(sentences[i],0)+times[i]);
       }
       
   }
   
   public List<String> input(char c) {
       if(c=='#'){
           map.put(sb.toString(),map.getOrDefault(sb.toString(),0)+1);
           sb.setLength(0);
           return new ArrayList<>();
       }
       
       sb.append(c);
       //n for number of sentences
       //l for current string length
       //TC:O(n*l*log3)-n*l//n for the map, l for starts with
       for(String key:map.keySet()){
           if(key.startsWith(sb.toString())){
               
                minHeap.add(new Pair(key,map.get(key)));
               
                if(minHeap.size()>3){
                    minHeap.poll();
                }   
           }
       }
       List<String> result = new LinkedList<>();
       while(!minHeap.isEmpty()){
           result.add(0,minHeap.poll().sentence);//add all polled sentence on head of the linkedlist because output should match the insertion order
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

/**
* Your AutocompleteSystem object will be instantiated and called as such:
* AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
* List<String> param_1 = obj.input(c);
*/