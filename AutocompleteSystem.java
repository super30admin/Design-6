import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

//Time Complexity : O(n) for initializing, O(1) for input operation
//Space Complexity : O(n); where n is number of sentences
public class AutocompleteSystem {    
    /**Approach: Trie+ HashMap**/    
    class TrieNode{
        List<String> list; 
        HashMap<Character, TrieNode> children;
        public TrieNode(){
            this.list= new ArrayList<>();
            this.children= new HashMap<>();
        }
    }
    private void insert(String sentence){
        TrieNode curr= root;
        for(char c: sentence.toCharArray()){
            curr.children.putIfAbsent(c, new TrieNode());  
            curr= curr.children.get(c);  
            if(!curr.list.contains(sentence)){ 
                curr.list.add(sentence);
            }
            //Store only top 3 hot sentences
            List<String> curList= curr.list;
            Collections.sort(curList, (a,b)->{
                if(map.get(a) == map.get(b)){
                    return a.compareTo(b);
                }
                return map.get(b) - map.get(a);
            });
            if(curr.list.size() > 3) curList.remove(curList.size()-1);   
        }
    }
    private List<String> search(String input){
        TrieNode curr= root;
        for(char c: input.toCharArray()){
            if(!curr.children.containsKey(c)){
                return new ArrayList<>();
            }
            curr= curr.children.get(c);
        }
        return curr.list;
    }
    
    TrieNode root;
    HashMap<String, Integer> map;
    StringBuilder input;   
    public AutocompleteSystem(String[] sentences, int[] times) { //O(n)
        map= new HashMap<>();
        input = new StringBuilder();
        root= new TrieNode();        
        for(int i=0; i<sentences.length; i++){
            String sentence = sentences[i];
            map.put(sentence, map.getOrDefault(sentence,0)+times[i]);
            insert(sentence);
        }
    }
    
    public List<String> input(char c) { //O(1)
        if(c == '#'){
            String sentence = input.toString();
            map.put(sentence, map.getOrDefault(sentence,0)+1);
            insert(sentence);
            input= new StringBuilder();
            return new ArrayList<>();
        }
        input.append(c);
        String in= input.toString();        
        return search(in);
    }
	
	// Driver code to test above
	public static void main (String[] args) {
		String[] sentences= {"i love you", "island", "iroman", "i love leetcode"};
		int[] times= {5, 3, 2, 2};
		AutocompleteSystem ob= new AutocompleteSystem(sentences,times);
		System.out.println("Hot sentences for input i: "+ob.input('i')); 
		System.out.println("Hot sentences for input blank: "+ob.input(' '));
		System.out.println("Hot sentences for input i a: "+ob.input('a'));
		System.out.println("Hot sentences for input i a#: "+ob.input('#'));
	}
}
