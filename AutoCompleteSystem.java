//TC,SC -O(n)
class AutocompleteSystem {
    class TrieNode{
        HashMap<Character, TrieNode> children;
        List<String> li;
        private TrieNode(){
            this.children = new HashMap<>();
            this.li = new ArrayList<>();
        }
    }
    private void insert(String word){
        TrieNode curr = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(!curr.children.containsKey(c)){
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
            List<String> li= curr.li;
            //duplicacy
            if(!li.contains(word)){
                li.add(word);
            }
            Collections.sort(li, (a,b) ->{
                int ca = map.get(a);
                int cb = map.get(b);
                if(ca == cb){
                    return a.compareTo(b);
                }
                return cb - ca;
            });
            if(li.size() > 3){
                li.remove(li.size() - 1);
            }
        }
    }
    private List<String> search(String search){
        TrieNode curr = root;
        for(int i = 0; i < search.length(); i++){
            char c = search.charAt(i);
            if(!curr.children.containsKey(c)) return new ArrayList<>();
           curr = curr.children.get(c);
        }
        return curr.li;
    }
    HashMap<String, Integer> map;
    StringBuilder sb;
    TrieNode root;
    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map = new HashMap<>();
        this.sb = new StringBuilder();
        this.root = new TrieNode();
        for(int i = 0; i < sentences.length; i++){
            String sentence = sentences[i];
            map.put(sentence, map.getOrDefault(sentence, 0) + times[i]);
            insert(sentence);
        }
    }
    
    public List<String> input(char c) { //O(1)
        if(c == '#'){
            String search = sb.toString();
            map.put(search, map.getOrDefault(search, 0) + 1);
            insert(search);
            this.sb = new StringBuilder();
            return new ArrayList<>();
        }
        sb.append(c);
        return search(sb.toString());
    }
}
