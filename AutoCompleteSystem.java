//Using HashMap and Trie
//TC - O(1)
class AutocompleteSystem {
    class TrieNode {
        HashMap<Character, TrieNode> children;
        List<String> top3;

        public TrieNode() {
            this.children = new HashMap<>();
            this.top3 = new ArrayList<>();
        }
    }

    private void insert(TrieNode root, String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
            List<String> list = curr.top3;
            if (!list.contains(word)) {
                list.add(word);
            }
            Collections.sort(list, (a, b) -> {
                int countA = map.get(a);
                int countB = map.get(b);
                if (countA == countB) {
                    return a.compareTo(b);
                }
                return countB - countA;

            });
            if (list.size() > 3) {
                list.remove(list.size() - 1);
            }
        }
    }

    private List<String> search(TrieNode root, String prefix) {
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            // if (curr.children[c - ' '] == null) {
            // return new ArrayList<>();
            // }
            // curr = curr.children[c - ' '];
            if (!curr.children.containsKey(c)) {
                return new ArrayList<>();
            }
            curr = curr.children.get(c);
        }
        return curr.top3;
    }

    private HashMap<String, Integer> map;
    private StringBuilder input;
    private TrieNode root;

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map = new HashMap<>();
        this.input = new StringBuilder();
        this.root = new TrieNode();
        for (int i = 0; i < times.length; i++) {
            String str = sentences[i];
            int count = times[i];
            map.put(str, map.getOrDefault(str, 0) + count);
            // insert the string in Trie
            insert(root, str);
        }
    }

    public List<String> input(char c) {
        // reset the search string
        // increase the frequency in the map if already exists in the map, if not create
        // in the
        // map with frequency 1
        if (c == '#') {
            String in = input.toString();
            map.put(in, map.getOrDefault(in, 0) + 1); // increase frq or create new entry in map
            insert(root, in);
            input = new StringBuilder(); // reset the string
            return new ArrayList<>();
        }
        input.append(c);
        String searchTerm = input.toString();
        return search(root, searchTerm);
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */