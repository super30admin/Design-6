import java.util.*;

//TC : O(L) ; L- length of input
//SC : O(N) ; N - No. of sentences

public class DesignSearchAutoComplete {

    class TrieNode{
        Map<Character, TrieNode> children;
        List<String> li;  //acts as priorityQueue
        boolean isEnd;

        public TrieNode(){
            children = new HashMap<>();
            li = new ArrayList<>();
        }
    }

    TrieNode root;
    Map<String, Integer> map;
    StringBuilder searchTerm;

    private void insert(String sentence) {
        TrieNode curr = root;

        for(int i=0; i < sentence.length(); i++){
            char c = sentence.charAt(i);

            if(!curr.children.containsKey(c)){
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);

            if(!curr.li.contains(sentence))
                curr.li.add(sentence);

            List<String> currLi = curr.li;
            //Sort on priority
            Collections.sort(currLi, (a, b) -> {
                if(map.get(a) == map.get(b)){
                    return a.compareTo(b);
                }
                return map.get(b) - map.get(a);
            });
            //remove from list if size > 3
            if(currLi.size() > 3)
                currLi.remove(currLi.size() - 1);
        }
        curr.isEnd = true;
    }

    private List<String> searchPrefix(String search){
        TrieNode curr = root;

        for(int i=0; i < search.length(); i++){
            char c = search.charAt(i);
            if(!curr.children.containsKey(c)){
                return new ArrayList<>();
            }
            curr = curr.children.get(c);
        }
        return curr.li;
    }

    public DesignSearchAutoComplete(String[] sentences, int[] times) {
        root = new TrieNode();
        map = new HashMap<>();
        searchTerm = new StringBuilder();

        for(int i =0 ; i < sentences.length; i++){
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
            insert(sentences[i]);
        }
    }

    public List<String> input(char c) {

        if(c == '#'){
            String searchedTerm = searchTerm.toString();
            map.put(searchedTerm, map.getOrDefault(searchedTerm, 0) + 1);
            //insert to trie also
            insert(searchedTerm);

            searchTerm = new StringBuilder();

            return new ArrayList<>();
        }
        searchTerm.append(c);
        String searchedTerm = searchTerm.toString();
        //search in trie of map for searchTerm - gives m terms - Optimization - O(L)
        return searchPrefix(searchedTerm);
    }
}
