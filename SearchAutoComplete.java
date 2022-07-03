// Time Complexity :O(n)+mlogm where n is no of sentences given and m is the size of result
// Space Complexity :O(m) we are using a priorityQueue
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :No

class AutocompleteSystem {
    private HashMap<String, Integer> map;
    private StringBuilder str;

    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        // add all sentences and freq in hashmap
        for (int i = 0; i < sentences.length; i++) {
            map.put(sentences[i], times[i]);
        }
        str = new StringBuilder();

    }

    public List<String> input(char c) {
        if (c == '#') {// if #, add string in map and make string empty
            String inp = str.toString();
            map.put(inp, map.getOrDefault(inp, 0) + 1);
            str = new StringBuilder();
            return new ArrayList<>();
        }
        str.append(c);
        // make priorityQueue on the basis of
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            int cnt1 = map.get(a);
            int cnt2 = map.get(b);
            // if freq are same, check lexicographical order
            if (cnt1 == cnt2) {
                return b.compareTo(a);
            }
            // else on the basis of frequencies
            return cnt1 - cnt2;
        });
        // check string in each key of map
        for (String s : map.keySet()) {
            String search = str.toString();

            if (s.startsWith(search)) {
                pq.add(s);
                if (pq.size() > 3) {
                    pq.poll();
                }
            }

        }
        List<String> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(0, pq.poll());
        }
        return result;
    }
}

// -----------------USING Trie-------------------
// Time Complexity :O(n)+O(mlogm) where n is no of words matching and m is size
// of result
// Space Complexity :O(m) priorityQueue space, we'll not consider trie space as
// we consider only user method extra space
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :
class AutocompleteSystem {
    private HashMap<String, Integer> map;
    private StringBuilder str;

    class TrieNode {
        private TrieNode[] children;
        private List<String> li;

        public TrieNode() {
            children = new TrieNode[256];
            li = new ArrayList<>();
        }

        // inserting a word in trie
        public void insert(String word) {
            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (curr.children[c - ' '] == null) {
                    curr.children[c - ' '] = new TrieNode();
                }
                curr = curr.children[c - ' '];
                curr.li.add(word);
            }

        }

        // to return list containing prefix word
        public List<String> search(String word) {
            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (curr.children[c - ' '] == null) {
                    return new ArrayList<>();
                }
                curr = curr.children[c - ' '];
            }
            return curr.li;
        }
    }

    TrieNode root;

    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        root = new TrieNode();
        // add all sentences and freq in hashmap
        for (int i = 0; i < sentences.length; i++) {
            String st = sentences[i];
            map.put(st, map.getOrDefault(st, 0) + times[i]);
            root.insert(sentences[i]);
        }
        str = new StringBuilder();

    }

    public List<String> input(char c) {
        if (c == '#') {// if #, add string in map and trie if it is not there already and make string
                       // empty

            String inp = str.toString();
            if (!map.containsKey(inp)) {
                root.insert(inp);
            }
            map.put(inp, map.getOrDefault(inp, 0) + 1);
            str = new StringBuilder();
            return new ArrayList<>();
        }
        str.append(c);
        // make priorityQueue
        String search = str.toString();
        List<String> li = root.search(search);

        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            int cnt1 = map.get(a);
            int cnt2 = map.get(b);
            if (cnt1 == cnt2) {
                return b.compareTo(a);
            }
            return cnt1 - cnt2;
        });
        for (String s : li) {
            pq.add(s);
            if (pq.size() > 3) {
                pq.poll();
            }
        }

        List<String> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(0, pq.poll());
        }
        return result;
    }
}

// -------------------------optimized-----------------
// Time Complexity :constant
// Space Complexity :O(l) where l is length of string currenctly created
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :took a long time to code and debug

class AutocompleteSystem {
    private HashMap<String, Integer> map;
    private StringBuilder str;

    class TrieNode {
        private TrieNode[] children;
        private List<String> pq;

        public TrieNode() {
            children = new TrieNode[256];
            pq = new ArrayList<>();
        }

        // inserting a word in trie such that each trienode only contains the result og
        // that particular string
        public void insert(String word) {
            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (curr.children[c - ' '] == null) {
                    curr.children[c - ' '] = new TrieNode();
                }
                curr = curr.children[c - ' '];
                if (!curr.pq.contains(word)) {
                    curr.pq.add(word);
                }
                // reorder the list
                Collections.sort(curr.pq, (a, b) -> {
                    int cnta = map.get(a);
                    int cntb = map.get(b);
                    if (cnta == cntb) {
                        return a.compareTo(b);
                    }
                    return cntb - cnta;
                });
                if (curr.pq.size() > 3) {
                    curr.pq.remove(curr.pq.size() - 1);
                }
            }

        }

        // to return list containing prefix word
        public List<String> search(String word) {
            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (curr.children[c - ' '] == null) {
                    return new ArrayList<>();
                }
                curr = curr.children[c - ' '];
            }
            return curr.pq;
        }
    }

    TrieNode root;

    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        root = new TrieNode();
        str = new StringBuilder();
        // add all sentences and freq in hashmap and trie
        for (int i = 0; i < sentences.length; i++) {
            String st = sentences[i];
            map.put(st, map.getOrDefault(st, 0) + times[i]);
            root.insert(sentences[i]);
        }

    }

    public List<String> input(char c) {
        if (c == '#') {// if #, insert in trie and map if it is not there already and make string
                       // empty

            String inp = str.toString();
            map.put(inp, map.getOrDefault(inp, 0) + 1);
            root.insert(inp);
            str = new StringBuilder();
            return new ArrayList<>();
        }
        str.append(c);
        String srch = str.toString();
        return root.search(srch);
    }
}
