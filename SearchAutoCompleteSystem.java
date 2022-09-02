import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

//Time Complexity: O(n)
//Space Complexity: O(n)

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

    private void insert(String sentence, int times) {
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

    StringBuilder input;

    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        input = new StringBuilder();
        for (int i = 0; i < sentences.length; i++) {
            String sentence = sentences[i];
            insert(sentence, times[i]);
        }
    }

    public List<String> input(char c) {
        if (c == '#') {
            String in = input.toString();
            input = new StringBuilder();
            insert(in, 1);
            return new ArrayList<>();
        }
        input.append(c);
        String in = input.toString();
        HashMap<String, Integer> map = search(in);

        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            if (map.get(a) == map.get(b)) {
                return b.compareTo(a);
            }
            return map.get(a) - map.get(b);

        });

        for (String key : map.keySet()) {
            if (key.startsWith(in)) {
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

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */