// Time Complexity : O(n)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Approach

// We design this using Hashmap and priority queuw
// We use the HashMap to store string and frequency
// When we get the input we check the map and add it to the pq
// and give out the top 3

class AutocompleteSystem {

    HashMap<String, Integer> map;
    StringBuilder input;

    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        input = new StringBuilder();
        for (int i = 0; i < sentences.length; i++) {
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
        }
    }

    public List<String> input(char c) {
        if (c == '#') {
            String in = input.toString();
            map.put(in, map.getOrDefault(in, 0) + 1);
            input = new StringBuilder();
            return new ArrayList<>();
        }
        input.append(c);
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            if (map.get(a) == map.get(b)) {
                return b.compareTo(a);
            }
            return map.get(a) - map.get(b);
        });
        for (String key : map.keySet()) {
            if (key.startsWith(input.toString())) {
                pq.add(key);
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

// Time Complexity : O(n)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Approach

// We design this using Trie, Hashmap and priority queuw
// We create a TrieNode with Hashmaps
// When we get the input we check the map and add it to the pq
// and give out the top 3

class AutocompleteSystem {
    class TrieNode {
        HashMap<String, Integer> map;
        HashMap<Character, TrieNode> children;

        public TrieNode() {
            map = new HashMap<>();
            children = new HashMap<>();

        }
    }

    TrieNode root;
    StringBuilder input;

    public void insert(String sentence, int times) {
        TrieNode curr = root;
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
            curr.map.put(sentence, curr.map.getOrDefault(sentence, 0) + times);
        }
    }

    public HashMap<String, Integer> search(String prefix) {
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!curr.children.containsKey(c)) {
                return new HashMap<>();
            }
            curr = curr.children.get(c);
        }
        return curr.map;
    }

    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        input = new StringBuilder();
        for (int i = 0; i < sentences.length; i++) {
            insert(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        if (c == '#') {
            String in = input.toString();
            insert(in, 1);
            input = new StringBuilder();
            return new ArrayList<>();
        }
        input.append(c);
        HashMap<String, Integer> map = search(input.toString());
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            if (map.get(a) == map.get(b)) {
                return b.compareTo(a);
            }
            return map.get(a) - map.get(b);
        });
        for (String key : map.keySet()) {
            if (key.startsWith(input.toString())) {
                pq.add(key);
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