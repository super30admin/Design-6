import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

//Time Complexity=O(n)
//Space Complexity=O(n)
public class AutocompleteSystem {

    HashMap<String,Integer> map;
    StringBuilder sb;
    public AutocompleteSystem(String[] sentences, int[] times) {
        map=new HashMap<>();
        sb=new StringBuilder();
        for(int i=0;i<sentences.length;i++){
            String sentence=sentences[i];
            map.put(sentence,map.getOrDefault(sentence,0)+times[i]);
        }
    }

    public List<String> input(char c) {
        if(c=='#'){
            String s=sb.toString();
            map.put(s,map.getOrDefault(s,0)+1);
            this.sb=new StringBuilder();
            return new ArrayList<>();
        }
        sb.append(c);

        PriorityQueue<String> pq=new PriorityQueue<>((a, b)->{
            int ca=map.get(a);
            int cb=map.get(b);
            if(ca==cb) return b.compareTo(a);
            return ca-cb;
        });
        String serach =sb.toString();
        for(String s:map.keySet()){
            if(s.startsWith(serach)){
                pq.add(s);

                if(pq.size()>3){
                    pq.poll();
                }
            }
        }
        List<String> li=new ArrayList<>();
        while(!pq.isEmpty()){
            li.add(0,pq.poll());
        }

        return li;
    }
}
