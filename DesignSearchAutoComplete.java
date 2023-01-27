//Time Complexity: O(1)
//Space Complexity: (nl) n string of average length l

/*
 * Maintain a hashmap of string and frequency and trienode. for every sentence add it to both.
 * for trienode maintain list of 3 strings at each index. also sort in decending order them based on frequency. 
 * if they are same then lexographically.
 */

class AutocompleteSystem {
    
    class TrieNode{
        TrieNode[] children;
        List<String> li;
        public TrieNode(){
            this.children = new TrieNode[256];
            this.li = new ArrayList<>();
        }
    }
    
    public void insert(String sentence){
        TrieNode curr = root;
        for(int i = 0; i < sentence.length(); i++){
            char c = sentence.charAt(i);
            if(curr.children[c - ' '] == null){
                curr.children[c - ' '] = new TrieNode();
            }
            curr = curr.children[c - ' '];
            List<String> li = curr.li;
            if(!curr.li.contains(sentence)){
                li.add(sentence);
            }
            Collections.sort(li, (s1, s2) -> {
                if(map.get(s1) == map.get(s2)) return s1.compareTo(s2);
                return map.get(s2) - map.get(s1);
            });
            if(li.size() > 3) li.remove(li.size()-1);
        }
    }
    
    public List<String> startsWith(String prefix){
        TrieNode curr = root;
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(curr.children[c - ' '] == null) return new ArrayList<>();
            curr = curr.children[c - ' '];
        }
        return curr.li;
    }
    
    TrieNode root;
    HashMap<String, Integer> map;
    StringBuilder search;
    public AutocompleteSystem(String[] sentences, int[] times) {
        this.root = new TrieNode();
        this.map = new HashMap<>();
        this.search = new StringBuilder();
        for(int i = 0; i < sentences.length; i++){
            String sent = sentences[i];
            int count = times[i];
            map.put(sent, map.getOrDefault(sent, 0) + count);
            insert(sent);
        }
    }
    
    public List<String> input(char c){
        if(c == '#'){
            String searchStr = search.toString();
            map.put(searchStr, map.getOrDefault(searchStr, 0) + 1);
            insert(searchStr);
            search = new StringBuilder();
            return new ArrayList<>();
        }
        search.append(c);
        return startsWith(search.toString());
    }
}