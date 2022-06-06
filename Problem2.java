import java.util.*;

public class Problem2 {

    // Solution with list on each Node
    class AutocompleteSystem {
        public class TrieNode {
            HashMap<Character, TrieNode> children;
            Map<String, Integer> map;

            public TrieNode() {
                children = new HashMap<>();
                map = new HashMap<>();
            }
        }

        TrieNode root;
        StringBuilder currString = new StringBuilder();
        private void addValue(String sentence, int times) {
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

        public AutocompleteSystem(String[] sentences, int[] times) {
            root = new TrieNode();
            for (int i = 0; i < sentences.length; i++) {
                addValue(sentences[i], times[i]);
            }
        }

        private Map<String, Integer> findValue(String word) {
            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!curr.children.containsKey(c)) {
                    return new HashMap<String, Integer>();
                }
                curr = curr.children.get(c);
            }
            return curr.map;
        }

        public List<String> input(char c) {
            List<String> result = new ArrayList<>();
            if (c == '#') {
                addValue(currString.toString(), 1);
                currString = new StringBuilder();
                return result;
            }
            currString.append(c);

            Map<String, Integer> map = findValue(currString.toString());

            PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
                if (map.get(a) == map.get(b)) {
                    return b.compareTo(a);
                }
                return map.get(a) - map.get(b);
            });

            for (String s : map.keySet()) {
                pq.add(s);
                if (pq.size() > 3) {
                    pq.poll();
                }
            }
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
}
