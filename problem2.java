import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class AutocompleteSystem{
    class TrieNode{
        HashMap<Character, TrieNode> children;
        List<String> words;
        public TrieNode(){
            children = new HashMap<>();
            words = new ArrayList<>();
        }
    }

    TrieNode root;

    private void insert(String sentence, int times){
        TrieNode curr = root;
        for(int i = 0; i < sentence.length(); i++){
            char c = sentence.charAt(i);
            if(!curr.children.containsKey(c)){
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
            List<String> temp = curr.words;
            if(!temp.contains(sentence)){
                temp.add(sentence);
            }
            Collections.sort(temp, (a, b) -> {
                if(map.get(a) ==  map.get(b)) {
                    return a.compareTo(b);
                }
                return map.get(b) - map.get(a);
            });

            if(temp.size() > 3){
                temp.remove(temp.size() - 1);
            }
            curr.words = temp;
        }
    }

    private List<String> search(String sentence){
        TrieNode curr = root;
        for(int i = 0; i < sentence.length(); i++){
            char c = sentence.charAt(i);
            if(!curr.children.containsKey(c)){
                return new ArrayList<>();
            }
            curr = curr.children.get(c);
        }
        return curr.words;
    }

    HashMap<String, Integer> map;
    StringBuilder currString;

    public AutocompleteSystem(String[] sentences, int[] times){
        root = new TrieNode();
        map = new HashMap<>();
        currString = new StringBuilder();
        for(int i = 0; i < sentences.length; i++){
            String sentence = sentences[i];
            map.put(sentence, map.getOrDefault(sentence, 0) + times[i]);
            insert(sentence, times[i]);
        }
    }

    public List<String> input(char c){
        if(c == '#'){
            String inputString = currString.toString();
            map.put(inputString, map.getOrDefault(inputString, 0) + 1);
            insert(inputString, 1);
            currString = new StringBuilder();
            return new ArrayList<>();
        }
        currString.append(c);
        return search(currString);
    }
}

//time complexity O(n) where n is total sentences in trie
//space complexity O(n) where n is total sentences in trieNode and map