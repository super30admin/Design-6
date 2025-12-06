// Time Complexity : O(1) .Sorting is done on 3 or 4 elements always, so it is constant
// Space Complexity : O(n*l) where n is the unique number of words and l is the average length of words.
// Did this code successfully run on Leetcode : Yes
// Approach : The idea is to maintain a list of top 3 sentences at each node of Trie, that are already sorted. This way, when the user
// enters a character the top3 at that trie node will be returned in O(1) time. To store the top 3 at each trie node, while first inserting
// and when a input char comes, we update the top 3 list by sorting by the times, if times are equal then sorting by lexograpgic order.
// Now when a new word comes we add to the map and update times and if it's not in the list of top3, we add it as fourth string, sort the
// list and remove the last one. This way, the top3 list always stays updated.

class AutocompleteSystem {
    HashMap<String, Integer> map;
    String search;
    TrieNode root;

    class TrieNode {
        TrieNode[] children;
        List<String> top3Results; //store top 3 sentences at each node of the Trie

        public TrieNode() {
            this.children = new TrieNode[256];
            this.top3Results = new ArrayList<>();
        }
    }

    private void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            //construct trie node
            if (curr.children[c - ' '] == null) {
                curr.children[c - ' '] = new TrieNode();
            }
            curr = curr.children[c - ' '];

            List<String> list = curr.top3Results;
            if (!list.contains(word)) { //new word comes in
                list.add(word);
            }

            Collections.sort(list, (a, b) -> {
                if (map.get(a).equals(map.get(b)))
                {
                    return a.compareTo(b); // if times are equal, sort lexographically
                }
                return map.get(b) - map.get(a); //sort top 3 as per the times
            });

            if (list.size() > 3) list.remove(list.size() - 1); //remove last one
        }
    }

    private List<String> searchPrefix(String prefix) {
        TrieNode curr = root;
        for (char c : prefix.toCharArray()) {
            //search for the prefix word
            if (curr.children[c - ' '] == null) return new ArrayList<>();
            curr = curr.children[c - ' '];
        }
        return curr.top3Results; //return top 3
    }

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.search = "";
        this.map = new HashMap<>();
        this.root = new TrieNode();
        for (int i = 0; i < sentences.length; i++) {
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);//insert word and times in the map
            insert(sentences[i]); //insert into a Trie Node
        }
    }

    public List<String> input(char c) {
        if (c == '#') { //sentence is ended
            map.put(search, map.getOrDefault(search, 0) + 1); //add to map
            insert(search);//add to trie node
            search = ""; //reset search
            return new ArrayList<>();
        }
        search += c; //append to search term
        return searchPrefix(search);
    }
}
