// Time Complexity : O(n*min(key,input_str)*log3) ,  (n for all the keys, check till the length of input_str or key, log 3 for pq minheap)
// Space Complexity : O(n) 
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

//lets take a hashmap to keep all the sentences with its freq and an input str which keep getting updated with each input
//in th einput func, lets take a priority queue which keeps track of the order of sentences , we use minheap here
//if the char is #, then we just update the freq to hashmap if str is already present, or add the new k,v pair
//reverse the order of output and return

import java.util.*;

class AutoCompleteSystem{
    HashMap<String, Integer> dict;
    String input_str;

    public AutoCompleteSystem(String[] sentences, int[] times){
        dict=new HashMap<>();
        input_str="";

        for(int i=0;i<sentences.length;i++){
            dict.put(sentences[i], times[i]);
        }
    }
    public List<String> input(char c){
        if(c=='#'){
            if(input_str!=""){
                    dict.put(input_str,dict.getOrDefault(input_str,0)+1);
                    input_str="";
            }
            return new ArrayList<>();
        }
        input_str+=c;
         //create PQ
         PriorityQueue<Pair> pq=new PriorityQueue<>((a,b)->a.count!=b.count ? a.count-b.count:b.sentence.compareTo(a.sentence));

        for(String key:dict.keySet()){
            if(key.startsWith(input_str)){
                pq.add(new Pair(key,dict.get(key)));
                if(pq.size()>3){
                    pq.remove();
                }
            }
        }
        List<String> output=new ArrayList<>();
        while(!pq.isEmpty()&& output.size()<3){
            output.add(pq.remove().sentence);
        }
        Collections.reverse(output);
        return output;

       
    }
    class Pair{
        String sentence;
        int count;
        Pair(String sentence, int count){
            this.sentence=sentence;
            this.count=count;
        }
    }
}
public class SearchAutoComplete{

    public static void main(String[] args){
        int[] times={5,3,2,2};
        String[] sentences={"i love you","island","ironman","i love leetcode"};

        AutoCompleteSystem search = new AutoCompleteSystem(sentences,times);
        System.out.println(search.input('i'));
        System.out.println(search.input(' '));
        System.out.println(search.input('a'));
        System.out.println(search.input('#'));
    }

}