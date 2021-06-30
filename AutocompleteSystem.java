import java.util.*;

public class AutocompleteSystem {
    //Normal Tries 
    //Extra: Since we may have capital letters and spaces, instead of TrieNode array as a children.
    //We have to use HashMap<Character, TrieNode>
    //Extra: To reduce the time complexity by searching entire sentence. 
    //Add extra hashmap to store the sentences and count.
    //Steps:
    //Insert all the sentences with occurances
    //If user types '#' get the current typed string and insert.
    //else search and get the hashmap
    //Iterate with min heap then sort based on Lexographical if the occurance is equal.

    //a.compareTo(b) - if a is smaller it will return negative. (e.g) "hello".compareTo("Man") returns -5
    
    class TrieNode {
        HashMap<Character, TrieNode> children;
        HashMap<String, Integer> sentences;
        TrieNode() {
            children = new HashMap();
            sentences = new HashMap();
        }
    }

    TrieNode root;
    
    StringBuilder sb;
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        sb = new StringBuilder();
        for(int i=0;i<sentences.length;i++) {
            insert(sentences[i], times[i]);
        }
        
    }
    
    public void insert(String sentence, int times) {
        TrieNode current = root;
        for(int i=0;i<sentence.length();i++) {
            char currentChar = sentence.charAt(i);
            if(!current.children.containsKey(currentChar)) {
                current.children.put(currentChar , new TrieNode());
            }
            current = current.children.get(currentChar);
            int newUpdatedTimes = current.sentences.getOrDefault(sentence, 0)+ times;
            current.sentences.put(sentence, newUpdatedTimes);   
        }
    }
    
    public HashMap<String, Integer> search(String sentence) {
        TrieNode current = root;
        for(int i=0;i<sentence.length();i++) {
            char currentChar = sentence.charAt(i);
            if(!current.children.containsKey(currentChar)) {
                return new HashMap();
            }
            current = current.children.get(currentChar);
        }
        return current.sentences;
    }
    
    public List<String> input(char c) {
        List<String> result = new ArrayList();
        if(c == '#') {
            String currentString = sb.toString();
            insert(currentString, 1);
            sb = new StringBuilder();
            return result;
        }
        sb.append(c);
        String currentString = sb.toString();
        HashMap<String, Integer> sentences = search(currentString);
        
        PriorityQueue<String> pq = new PriorityQueue<String>((a,b) -> {
           if(sentences.get(a) == sentences.get(b)) {
               return b.compareTo(a);
           } else {
               return sentences.get(a)-sentences.get(b);
           } 
        });
        
        for(String entry : sentences.keySet()) {
            pq.add(entry);
            if(pq.size() > 3) {
                pq.poll();
            }
        }
        
        while(!pq.isEmpty()) {
            result.add(0, pq.poll());
        }
        return result;
    }

    public static void main(String[] args) {
//         ["AutocompleteSystem","input","input","input","input"]
// [[["i love you","island","iroman","i love leetcode"],[5,3,2,2]],["i"],[" "],["a"],["#"]]
        AutocompleteSystem autocompleteSystem = new AutocompleteSystem(
            new String[] {"maple","mango","more", "must"},
            new int[] {4, 1,2,1}
        );
        System.out.println(autocompleteSystem.input('m'));
        System.out.println(autocompleteSystem.input(' '));
        System.out.println(autocompleteSystem.input('a'));
        System.out.println(autocompleteSystem.input('#'));

        System.out.println(autocompleteSystem.input('m'));
        System.out.println(autocompleteSystem.input(' '));
        System.out.println(autocompleteSystem.input('a'));
        System.out.println(autocompleteSystem.input('#'));


        System.out.println(autocompleteSystem.input('m'));
        System.out.println(autocompleteSystem.input(' '));
        System.out.println(autocompleteSystem.input('a'));
        System.out.println(autocompleteSystem.input('#'));
    }
    
}
