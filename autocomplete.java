// Time Complexity :
// insert() -> O(L * log 3) ≈ O(L) where L is sentence length
// search() -> O(L)
// input() -> O(L)
// Space Complexity : O(N * L) for Trie storage
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach in three sentences only
// We use a Trie where each node stores the top 3 most frequent sentences for its prefix.
// While inserting a sentence, we update the top 3 list at every prefix node based on frequency and lexicographical order.
// For each input character, we either search the current prefix or insert the completed sentence when '#' is entered.

class AutocompleteSystem {
    HashMap<String, Integer> map;
    String search;
    TrieNode root;

    class TrieNode {
        HashMap<Character, TrieNode> children;
        List<String> top3;

        public TrieNode() {
            this.children = new HashMap<>();
            this.top3 = new ArrayList<>();
        }
    }

    // insert sentence into Trie and update top 3 suggestions
    private void insert(String word) {
        TrieNode curr = root;

        for(int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if(!curr.children.containsKey(ch)) {
                curr.children.put(ch, new TrieNode());
            }

            curr = curr.children.get(ch);

            if(!curr.top3.contains(word)) {
                curr.top3.add(word);
            }

            // sort by frequency desc, lexicographically asc
            Collections.sort(curr.top3, (a, b) -> {
                if(map.get(a).equals(map.get(b))) return a.compareTo(b);
                return map.get(b) - map.get(a);
            });

            // keep only top 3
            if(curr.top3.size() > 3) {
                curr.top3.remove(curr.top3.size() - 1);
            }
        }
    }

    // get top 3 suggestions for prefix
    private List<String> search(String prefix) {
        TrieNode curr = root;

        for(int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);

            if(!curr.children.containsKey(ch)) {
                return new ArrayList<>();
            }

            curr = curr.children.get(ch);
        }

        return curr.top3;
    }

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map = new HashMap<>();
        this.search = "";
        this.root = new TrieNode();

        // build initial Trie
        for(int i = 0; i < sentences.length; i++) {
            String curr = sentences[i];
            int cnt = times[i];

            map.put(curr, map.getOrDefault(curr, 0) + cnt);
            insert(curr);
        }
    }

    public List<String> input(char c) {

        // complete current sentence
        if(c == '#') {
            map.put(search, map.getOrDefault(search, 0) + 1);
            insert(search);
            search = "";
            return new ArrayList<>();
        }

        search += c;

        // return suggestions for current prefix
        return search(search);
    }
}