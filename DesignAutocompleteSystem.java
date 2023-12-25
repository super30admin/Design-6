// Time Complexity : O(p+q+mlogm), where m is the length of list to be sorted, p is the length of search query and q is the number of nodes in the trie
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : -


class AutocompleteSystem {

    class TrieNode{
        TrieNode[] children;
        List<String> li;

        public TrieNode(){
            this.children = new TrieNode[256];
            this.li = new ArrayList<>();
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
            curr.li.add(sentence);
        }
    }

    private List<String> startsWith(String prefix){
        TrieNode curr = root;
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(curr.children[c - ' '] == null){
                return new ArrayList<>();
            }
            curr = curr.children[c - ' '];
        }
        return curr.li;
    }

    private TrieNode root;
    private HashMap<String, Integer> map;
    StringBuilder search;
    public AutocompleteSystem(String[] sentences, int[] times) {
        this.root = new TrieNode();
        map = new HashMap<>();
        search = new StringBuilder();
        for(int i = 0; i < sentences.length; i++){
            String sent = sentences[i];
            int count = times[i];
            if(!map.containsKey(sent))
                insert(sent);
            map.put(sent, map.getOrDefault(sent, 0) + count);
        }
    }
    public List<String> input(char c) {
        PriorityQueue<String> pq = new PriorityQueue<>((s1,s2) -> {
            if(map.get(s1) == map.get(s2)){
                return s2.compareTo(s1);
            }
            return map.get(s1) - map.get(s2);
        });
        if(c == '#'){
            String searchString = search.toString();
            if(!map.containsKey(searchString))
                insert(searchString);
            map.put(searchString, map.getOrDefault(searchString, 0) + 1);
            search = new StringBuilder();
            return new ArrayList<>();
        }
        search.append(c);
        List<String> startsWith = startsWith(search.toString()); 
        //search for input string in map
        for(String sentence : startsWith){
            pq.add(sentence);
            if(pq.size() > 3)
                pq.poll();
        }
        List<String> result = new ArrayList<>();
        while(!pq.isEmpty()){
            result.add(0, pq.poll());
        }
        return result;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */