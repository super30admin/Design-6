import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

//Time Complexity=O(1)
//Space Complexity=O(n)
public class AutoCompleteOptimal {

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

            List<String> li=curr.li;

            if(!li.contains(word)){
                li.add(word);
            }

            Collections.sort(li,(a, b)->{
                int ca=map.get(a);
                int cb=map.get(b);
                if(ca==cb) return a.compareTo(b);
                return cb-ca;
            });

            if(li.size()>3){
                li.remove(li.size()-1);
            }
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
    public AutoCompleteOptimal(String[] sentences, int[] times) {

        root=new TrieNode();
        map=new HashMap<>();
        sb=new StringBuilder();
        for(int i=0;i<sentences.length;i++){
            String sentence=sentences[i];
            map.put(sentence,map.getOrDefault(sentence,0)+times[i]);
            insert(sentence);
        }
    }

    public List<String> input(char c) {
        if(c=='#'){
            String s=sb.toString();
            map.put(s,map.getOrDefault(s,0)+1);
            insert(s);
            this.sb=new StringBuilder();
            return new ArrayList<>();
        }
        sb.append(c);
        String se =sb.toString();

        return serach(se);
    }
}
