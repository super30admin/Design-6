import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
class AutocompleteSystem {
    Map<String,Integer> map;
    String inp="";
    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map=new HashMap<>();
        for(int i=0;i<times.length;i++){
            
            map.put(sentences[i],map.getOrDefault(sentences[i],0)+times[i]);
            
        }
    }
    
    public List<String> input(char c) {
        if(c=='#'){
            map.put(inp,map.getOrDefault(inp,0)+1);
            inp="";
            return new ArrayList<>();
        }
        inp=inp+c;
        PriorityQueue<String> pq=new PriorityQueue<>((a,b)->{
            int cnta=map.get(a);
            int cntb=map.get(b);
            if(cnta!=cntb){
                return cnta-cntb;
            }else{
                int cmp=b.compareTo(a);
                return cmp;
            }
        });

        //search if any of string in map start with inp
        for(String key:map.keySet()){
            if(key.startsWith(inp)){
                pq.add(key);
                if(pq.size()>3){
                    pq.poll();
                }
            }
        }
        List<String> res=new ArrayList<>();
        while(!pq.isEmpty()){
            res.add(0,pq.poll());
        }
        return res;
    }
}
*/

//optimised
//TC: O(n)
//SC: O(n)
/*
class AutocompleteSystem {

    class TrieNode{
        TrieNode [] children;
        List<String> li;
        public TrieNode(){
            this.children=new TrieNode[100];
            li=new ArrayList<>();
        }
    }

    private void insert(TrieNode root,String sentence){
        TrieNode curr=root;
        for(int i=0;i<sentence.length();i++){
            char c=sentence.charAt(i);
            if(curr.children[c-' ']==null){
                curr.children[c-' ']=new TrieNode();
            }
            curr=curr.children[c-' '];
            curr.li.add(sentence);
        }
    }

    private List<String> search(TrieNode root,String inp){
        TrieNode curr=root;
        for(int i=0;i<inp.length();i++){
            char c=inp.charAt(i);
            if(curr.children[c-' ']==null){
                return new ArrayList<>();
            }
            curr=curr.children[c-' '];
        }
        return curr.li;
    }

    Map<String,Integer> map;
    String inp="";
    TrieNode root;
    public AutocompleteSystem(String[] sentences, int[] times) {
        root=new TrieNode();
        this.map=new HashMap<>();
        for(int i=0;i<times.length;i++){
            if(!map.containsKey(sentences[i])){
                insert(root,sentences[i]);
            }
            map.put(sentences[i],map.getOrDefault(sentences[i],0)+times[i]);
            
        }
    }
    
    public List<String> input(char c) {
        if(c=='#'){
            if(!map.containsKey(inp)){
                insert(root,inp);
            }
            map.put(inp,map.getOrDefault(inp,0)+1);
            inp="";
            return new ArrayList<>();
        }
        inp=inp+c;
        PriorityQueue<String> pq=new PriorityQueue<>((a,b)->{
            int cnta=map.get(a);
            int cntb=map.get(b);
            if(cnta!=cntb){
                return cnta-cntb;
            }else{
                int cmp=b.compareTo(a);
                return cmp;
            }
        });

        //search if any of string in map start with inp

        List<String> li=search(root,inp);
        for(String key:li){
                pq.add(key);
                if(pq.size()>3){
                    pq.poll();
                }
            
        }
        List<String> res=new ArrayList<>();
        while(!pq.isEmpty()){
            res.add(0,pq.poll());
        }
        return res;
    }
}

*/
/*
//TC: O(n), n= no. of words strting with inp
//SC: O(m*n) = no of words and letters
class AutocompleteSystem {

    class TrieNode{
        Map<Character,TrieNode> children;
        List<String> li;
        public TrieNode(){
            this.children=new HashMap<>();
            li=new ArrayList<>();
        }
    }

    private void insert(String sentence){
        TrieNode curr=root;
        for(int i=0;i<sentence.length();i++){
            char c=sentence.charAt(i);
            if(!curr.children.containsKey(c)){
                curr.children.put(c,new TrieNode());
            }
            curr=curr.children.get(c);
            curr.li.add(sentence);
        }
    }

    private List<String> search(String input){
        TrieNode curr=root;
        for(int i=0;i<input.length();i++){
            char c=input.charAt(i);
            if(!curr.children.containsKey(c)){
                return new ArrayList<>();
            }
            curr=curr.children.get(c);
        }
        return curr.li;
    }

    Map<String,Integer> map;
    String inp="";
    TrieNode root;
    public AutocompleteSystem(String[] sentences, int[] times) {
        root=new TrieNode();
        this.map=new HashMap<>();
        for(int i=0;i<times.length;i++){
            if(!map.containsKey(sentences[i])){
                insert(sentences[i]);
            }
            map.put(sentences[i],map.getOrDefault(sentences[i],0)+times[i]);
            
        }
    }
    
    public List<String> input(char c) {
        if(c=='#'){
            if(!map.containsKey(inp)){
                insert(inp);
            }
            map.put(inp,map.getOrDefault(inp,0)+1);
            inp="";
            return new ArrayList<>();
        }
        inp=inp+c;
        PriorityQueue<String> pq=new PriorityQueue<>((a,b)->{
            int cnta=map.get(a);
            int cntb=map.get(b);
            if(cnta!=cntb){
                return cnta-cntb;
            }else{
                int cmp=b.compareTo(a);
                return cmp;
            }
        });

        //search if any of string in map start with inp

        List<String> li=search(inp);
        for(String key:li){
                pq.add(key);
                if(pq.size()>3){
                    pq.poll();
                }
            
        }
        List<String> res=new ArrayList<>();
        while(!pq.isEmpty()){
            res.add(0,pq.poll());
        }
        return res;
    }
}
*/
//TC: O(1)
//SC: O(n)
class AutocompleteSystem {

    class TrieNode{
        Map<Character,TrieNode> children;
        List<String> li; //contain only top 3
        public TrieNode(){
            this.children=new HashMap<>();
            li=new ArrayList<>();
        }
    }

    private void insert(String sentence){
        TrieNode curr=root;
        for(int i=0;i<sentence.length();i++){
            char c=sentence.charAt(i);
            if(!curr.children.containsKey(c)){
                curr.children.put(c,new TrieNode());
            }
            curr=curr.children.get(c);
            if(!curr.li.contains(sentence)){
                curr.li.add(sentence);
            }
            List<String> top3=curr.li;
            Collections.sort(top3,(a,b)->{
                int ca=map.get(a);
                int cb=map.get(b);
                if(ca==cb){
                    return a.compareTo(b);
                }
                //hottest to hot
                return cb-ca;
            });
            if(top3.size()>3){
                top3.remove(3);
            }
        }
    }

    private List<String> search(String input){
        TrieNode curr=root;
        for(int i=0;i<input.length();i++){
            char c=input.charAt(i);
            if(!curr.children.containsKey(c)){
                return new ArrayList<>();
            }
            curr=curr.children.get(c);
        }
        return curr.li;
    }

    Map<String,Integer> map;
    String inp="";
    TrieNode root;
    public AutocompleteSystem(String[] sentences, int[] times) {
        root=new TrieNode();
        this.map=new HashMap<>();
        for(int i=0;i<times.length;i++){
            
            map.put(sentences[i],map.getOrDefault(sentences[i],0)+times[i]);
            insert(sentences[i]);
        }
    }
    
    public List<String> input(char c) {
        if(c=='#'){
            
            map.put(inp,map.getOrDefault(inp,0)+1);
            insert(inp);
            inp="";
            return new ArrayList<>();
        }
        inp=inp+c;
        

        //search if any of string in map start with inp

        return search(inp);
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */