// TC: O(L) L = length of word
// SC: O(M × L)
class AutocompleteSystem {
    int REQ_AUTO_COMP_RESULTS = 3;
    HashMap<String, Integer> map;
    TrieNode root;
    String search;
    class TrieNode{
        TrieNode[] children;
        List<String> list;

        TrieNode(){
            this.children = new TrieNode[128];
            this.list = new ArrayList<>();
        }
    }
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        this.map = new HashMap<>();
        search = "";

        for(int i=0; i< sentences.length; i++){
            if(!map.containsKey(sentences[i])){
                insert(root, sentences[i]);
                map.put(sentences[i], 0);
            }
            map.put(sentences[i], map.get(sentences[i]) + times[i]);
        }

    }

    public List<String> input(char c) {

        if(c == '#'){
            if(!map.containsKey(search)){
                insert(root, search);
            }
            map.put(search, map.getOrDefault(search, 0) +1);
            search = "";
            return new ArrayList<>();
        }
        // appending key strokes
        this.search = this.search + c;

        // Heap to sort by freq and lexiographically
        PriorityQueue<String> pq = new PriorityQueue<>( (a, b) -> {
            if (map.get(a) == map.get(b)) {
                return b.compareTo(a);
            }
            return map.get(a) - map.get(b);
        });

        // get list of strings from trie for search term
        List<String> li = search(root, search);

        for(String word: li){
            pq.add(word);
            if(pq.size() > this.REQ_AUTO_COMP_RESULTS){
                pq.poll();
            }
        }
        List<String> result = new ArrayList<>();

        while(!pq.isEmpty()){
            result.add(0,pq.poll());
        }

        return result;
    }

    private List<String> search(TrieNode root, String word){
        TrieNode curr = root;
        for(int i=0; i< word.length(); i++){
            char ch = word.charAt(i);
            if(curr.children[ch - ' '] != null){
                curr = curr.children[ch - ' '];
            }else{
                return new ArrayList<String>();
            }

        }

        return curr.list;

    }

    private void insert(TrieNode root, String word){
        TrieNode curr = root;
        for(int i=0; i< word.length(); i++){
            char ch = word.charAt(i);
            if(curr.children[ch - ' '] == null){
                curr.children[ch - ' '] = new TrieNode();
            }
            curr = curr.children[ch - ' '];
            curr.list.add(word);
        }
    }
}
