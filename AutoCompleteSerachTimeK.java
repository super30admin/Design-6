import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

//Time Complexity=O(k)
//Space Complexity=O(n)
public class AutoCompleteSerachTimeK {

    private class TrieNode{
        TrieNode[] children;
        List<String> li;

        public TrieNode(){
            children=new TrieNode[256];
            li=new ArrayList<>();
        }
    }

    public void insert(String word){
        TrieNode curr=root;
        for(int i=0;i<word.length();i++){
            char c=word.charAt(i);
            if(curr.children[c-' ']==null){
                curr.children[c-' ']=new TrieNode();
            }
            curr=curr.children[c-' '];
            curr.li.add(word);
        }
    }

    public List<String> serach(String word){
        TrieNode curr=root;
        for(int i=0;i<word.length();i++){
            char c=word.charAt(i);
            if(curr.children[c-' ']==null) return new ArrayList<>();
            curr=curr.children[c-' '];
        }
        return curr.li;
    }

    TrieNode root;
    HashMap<String,Integer> map;
    StringBuilder sb;
    public AutoCompleteSerachTimeK(String[] sentences, int[] times) {

        root=new TrieNode();
        map=new HashMap<>();
        sb=new StringBuilder();
        for(int i=0;i<sentences.length;i++){
            String sentence=sentences[i];
            if(!map.containsKey(sentence)) insert(sentence);
            map.put(sentence,map.getOrDefault(sentence,0)+times[i]);

        }
    }

    public List<String> input(char c) {
        if(c=='#'){
            String s=sb.toString();
            if(!map.containsKey(s)) insert(s);
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
        String se =sb.toString();
        List<String> li=serach(se);
        for(String s:li){
            pq.add(s);
            if(pq.size()>3){
                pq.poll();
            }
        }
        List<String> result=new ArrayList<>();
        while(!pq.isEmpty()){
            result.add(0,pq.poll());
        }

        return result;
    }
}
