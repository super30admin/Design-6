//TC and SC is not needed for design problems
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None

public class designAutocompleteSearch {
    class TrieNode{
        // HashMap<String, Integer> map;
        List<String> pq;
        TrieNode [] children;
        public TrieNode(){
            pq = new ArrayList<>();
            children = new TrieNode[256];
        }
    }
    
    public void insert(String sentence, int time){
        TrieNode curr = root;
        for(int i = 0; i < sentence.length(); i++){
            char c = sentence.charAt(i);
            if(curr.children[c - ' '] == null){
                curr.children[c - ' '] = new TrieNode();
            }
            curr = curr.children[c - ' '];
            List<String> sentenceList = curr.pq;
            if(!sentenceList.contains(sentence)){
                sentenceList.add(sentence);
            }
            // li.add(sentence);
            Collections.sort(sentenceList,(a,b) -> {
                if(map.get(a) == map.get(b)){
                    return a.compareTo(b);
                }
                return map.get(b) - map.get(a);
            });
            if(sentenceList.size() > 3){
                sentenceList.remove(sentenceList.size() - 1);
            }
        }
    }
    private List<String> search(String prefix){
        TrieNode curr = root;
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(curr.children[c - ' '] == null){
               // return new HashMap<>();
                return new ArrayList<>();
            }
            curr = curr.children[c - ' '];
        }
        return curr.pq;
    }
    TrieNode root;
    HashMap<String, Integer> map;
    StringBuilder sb; // serach
    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        root = new TrieNode();
        sb = new StringBuilder();
        for(int i = 0; i < sentences.length; i++){
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
            insert(sentences[i], times[i]);
        }
    }
    
    public List<String> input(char c) {
        // #
        if(c == '#'){
            String st = sb.toString();
            map.put(st, map.getOrDefault(st, 0) + 1);
            insert(st, 1);
            sb = new StringBuilder();
            return new ArrayList<>();
        }
        sb.append(c);
        String se = sb.toString();
        return search(se);
    }
}
