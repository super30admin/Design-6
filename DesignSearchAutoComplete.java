// Brute force soln | Time: O(NLogK) | Space: O(1)

// we have used HashMap to keep track of the frequencies of search on each sentences
// Tries basically to store and retrieve the match results in O(Length of search keyword) time
class AutocompleteSystem {
    class TrieNode {
        TrieNode[] children;
        // maintaining list at every level for faster retrieval of result
        List<String> li;

        public TrieNode() {
            // we have creaated the ascii space of all the characters, we are also going to include SPACE and alphabets
            children = new TrieNode[128];
            li = new ArrayList<>();
        }
    }

    Map<String, Integer> map;
    StringBuilder s;
    TrieNode root;

    public AutocompleteSystem(String[] sentences, int[] times) {
        s = new StringBuilder();
        map = new HashMap<>();
        root = new TrieNode();
        // creating Tries on given sentences
        // dictionary of sentences and no of times searched
        for (int i = 0; i < sentences.length; i++) {
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
            insert(sentences[i]);
        }
    }

    private void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            if (curr.children[c - ' '] == null) {
                curr.children[c - ' '] = new TrieNode();
            }
            curr = curr.children[c - ' '];
            curr.li.add(word);
        }
    }

    private List<String> startsWith(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            if (curr.children[c - ' '] == null) {
                return new ArrayList<>();
            }
            curr = curr.children[c - ' '];
        }
        return curr.li;
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        // if user inputs #
        // all the previously entered characters in search field, will be put into the map and added to the trie
        if (c == '#') {
            if (!map.containsKey(s.toString())) {
                insert(s.toString());
            }
            map.put(s.toString(), map.getOrDefault(s.toString(), 0) + 1);
            s = new StringBuilder();
            return res;
        }
        s.append(c);
        // so once we get the results matching the searchKeyword
        // we are going to maintain a min Heap to retain top K elements
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            int frequency1 = map.get(a);
            int frequency2 = map.get(b);
            if (frequency1 == frequency2) {
                return b.compareTo(a);
            }
            return frequency1 - frequency2;
        });
        List<String> extracts = startsWith(s.toString());
        for (String extract : extracts) { // O(No of words starting a particular characters)
            pq.add(extract);
            if (pq.size() > 3) { // O(LogK)
                pq.poll();
            }
        }
        // once we retained the top K elements,
        // we create a list out of it, return as a result
        while (!pq.isEmpty()) {
            res.add(0, pq.poll());
        }
        return res;
    }
}

// HashMap soln
class AutocompleteSystem {
    class TrieNode {
        Map<Character, TrieNode> node;
        List<String> li;

        public TrieNode() {
            node = new HashMap<>();
            li = new ArrayList<>();
        }
    }

    Map<String, Integer> map;
    StringBuilder s;
    TrieNode root;

    public AutocompleteSystem(String[] sentences, int[] times) {
        s = new StringBuilder();
        map = new HashMap<>();
        root = new TrieNode();
        for (int i = 0; i < sentences.length; i++) {
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
            insert(sentences[i]);
        }
    }

    private void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            if (!curr.node.containsKey(c)) {
                curr.node.put(c, new TrieNode());
            }
            curr = curr.node.get(c);
            curr.li.add(word);
        }
    }

    private List<String> startsWith(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            if (!curr.node.containsKey(c)) {
                return new ArrayList<>();
            }
            curr = curr.node.get(c);
        }
        return curr.li;
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {
            if (!map.containsKey(s.toString())) {
                insert(s.toString());
            }
            map.put(s.toString(), map.getOrDefault(s.toString(), 0) + 1);
            s = new StringBuilder();
            return res;
        }
        s.append(c);
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> { // O(N)
            int frequency1 = map.get(a);
            int frequency2 = map.get(b);
            if (frequency1 == frequency2) {
                return b.compareTo(a);
            }
            return frequency1 - frequency2;
        });
        List<String> extracts = startsWith(s.toString());
        for (String extract : extracts) {
            pq.add(extract);
            if (pq.size() > 3) {
                pq.poll();
            }
        }
        while (!pq.isEmpty()) {
            res.add(0, pq.poll());
        }
        return res;
    }
}

//Time: O(1)
//Optimised soln

class AutocompleteSystem {
    class TrieNode {
        Map<Character, TrieNode> node;
        List<String> li;

        public TrieNode() {
            node = new HashMap<>();
            li = new ArrayList<>();
        }
    }

    Map<String, Integer> map;
    StringBuilder s;
    TrieNode root;

    public AutocompleteSystem(String[] sentences, int[] times) {
        s = new StringBuilder();
        map = new HashMap<>();
        root = new TrieNode();
        for (int i = 0; i < sentences.length; i++) {
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
            insert(sentences[i]);
        }
    }

    private void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            if (!curr.node.containsKey(c)) {
                curr.node.put(c, new TrieNode());
            }
            curr = curr.node.get(c);
            if (!curr.li.contains(word))
                curr.li.add(word);
            Collections.sort(curr.li, (a, b) -> {
                if (map.get(a) == map.get(b)) return a.compareTo(b);
                return map.get(b) - map.get(a);
            });
            if (curr.li.size() > 3) {
                curr.li.remove(curr.li.size() - 1);
            }
        }

    }

    private List<String> startsWith(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            if (!curr.node.containsKey(c)) {
                return new ArrayList<>();
            }
            curr = curr.node.get(c);
        }
        return curr.li;
    }

    public List<String> input(char c) {

        if (c == '#') {
            map.put(s.toString(), map.getOrDefault(s.toString(), 0) + 1);

            insert(s.toString());

            s = new StringBuilder();
            return new ArrayList<>();
        }
        s.append(c);


        return startsWith(s.toString());
    }
}
