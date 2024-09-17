/**
 * Time Complexity : O(input string length) calls * O(mnlog k) wheer n is number of sentences and m is avg sentence length
 * Space Complexity : O(m) where m <= n (n is the number of sentences and m is the possible number of sentences tries will make nodes for) 
 */
import java.util.*;
public class AutoCompleteSystem {
    class Pair{                                                                                            
        String s;
        int count;
        Pair(String s, int c){
            this.s =s;
            this.count = c;
        }
        public String getSt(){return this.s;}
        public int getC(){return this.count;}
        }
    class TrieNode{
        int count;
        TrieNode[] children;                                                                                       
        TrieNode(){
            children = new TrieNode[128];
        }
    }
    TrieNode root = new TrieNode();                                                                                         
    TrieNode head = root;
    String[] sentence;
    public AutoCompleteSystem(String[] sentences, int[] times) {
        for(int i = 0; i < sentences.length; i++){
            insert(sentences[i]);                                                                            
            root.count = times[i];                                                                      
        }
        sentence = sentences;                                                                                   // Keep a Global Copy of sentences to use it in other function
    }
    public void insert(String sentence){
        root = head;
        for(int j  = 0; j < sentence.length(); j++){
           char c = sentence.charAt(j);                                                        
           if(root.children[c] == null){
               root.children[c] = new TrieNode();
           }
            root = root.children[c];
        }
    }
    String search ="";
    public List<String> input(char c) {
        List<String> result = new ArrayList<String>();
        if(c == '#'){
            insert(search);                                                                                 
            search = "";
            return result;
        } else{
            PriorityQueue<Pair> pq = new PriorityQueue<Pair>((a,b) -> {
                if(a.count == b.count){
                    return b.s.compareTo(a.s);
                }
                return a.count-b.count;                                                                                                 
            });
            search+=c;
            for(int i = 0; i < sentence.length;i++){                                                        
            root=head;
            if(sentence[i].startsWith(search)){                                                             
            for(int j  = 0; j < sentence[i].length(); j++){
                c = sentence[i].charAt(j);
                root = root.children[c];                                                                    
            }
            if(pq.size() < 3){
            pq.add(new Pair(sentence[i], root.count));                                                      
            } else{
                if(pq.peek().getC() == root.count){
                    if(pq.peek().getSt().compareTo(sentence[i]) > 0){                                       
                        pq.poll();
                        pq.add(new Pair(sentence[i], root.count));
                    }
                } else 
                if(pq.peek().getC() < root.count){                                                          
                    pq.poll();
                    pq.add(new Pair(sentence[i], root.count));
                }
                }
            }
        }
            while(!pq.isEmpty()){
                Pair p = pq.poll();
                result.add(0, p.getSt());                                                                   
            }
    }
        return result;
    }
}