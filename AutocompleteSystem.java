import java.sql.Array;
import java.util.*;

public class AutocompleteSystem {
    Map<String, Integer> map;
    String search;
    TrieNode root;

    class TrieNode {
        TrieNode[] children;
        List<String> topResults;

        public TrieNode() {
            this.children = new TrieNode[256];
            this.topResults = new ArrayList<>();
        }
    }

        private void insert(String sentence){
            TrieNode curr = root;
            for(int i = 0; i < sentence.length(); i++){
                char c = sentence.charAt(i);
                if(curr.children[c - ' '] == null){
                    curr.children[c - ' '] = new TrieNode();
                }
                curr = curr.children[c - ' '];
            }
            List<String> list = curr.topResults;
            Collections.sort(list, (String a, String b) -> {
                if(map.get(a) == map.get(b)){
                    return a.compareTo(b);
                }
                return map.get(b) - map.get(a);
            });

            if(list.size() > 3){
                list.remove(list.size() - 1);
            }
        }

        private List<String> search(String sentence){
            TrieNode curr = root;
            for(int i = 0; i < sentence.length(); i++){
                char c = sentence.charAt(i);
                if(curr.children[c - ' '] == null){
                    return new ArrayList<>();
                }
                curr = curr.children[c - ' '];
            }
            return curr.topResults;
        }

    public AutocompleteSystem(String[] sentences, int[] times){
        this.map = new HashMap<>();
        this.search = "";
        this.root = new TrieNode();

        for(int i = 0; i < sentences.length; i++){
            String sentence = sentences[i];
            int freq = times[i];
            map.put(sentence, map.getOrDefault(sentence, 0) + freq);
            insert(sentence);
        }
    }

    public List<String> input(char c){
        if(c == '#'){
            map.put(search, map.getOrDefault(search, 0) + 1);
            insert(search);
            search = "";
            return new ArrayList<>();
        }
        this.search += c;
        return search(search);
    }
}
