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

        // Solution with single hashmap to reduce space complexity
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

    public class AutocompleteSystem2 {

        public AutocompleteSystem2() {

        }

        public class TrieNode {
            HashMap<Character, TrieNode> children;
            //Map<String, Integer> map;
            List<String> words;

            public TrieNode() {
                children = new HashMap<>();
                //map = new HashMap<>();
                words = new ArrayList<>();
            }
        }

        TrieNode root;
        StringBuilder currString = new StringBuilder();
        Map<String, Integer> map;

        private void addValue(String sentence, int times) {
            TrieNode curr = root;
            for (int i = 0; i < sentence.length(); i++) {
                char c = sentence.charAt(i);
                if (!curr.children.containsKey(c)) {
                    curr.children.put(c, new TrieNode());
                }
                curr = curr.children.get(c);
                List<String> temp = curr.words;
                if (!temp.contains(sentence)) {
                    temp.add(sentence);
                }
                Collections.sort(temp, (a, b) -> {
                    if (map.get(a) == map.get(b)) {
                        return a.compareTo(b);
                    }
                    return map.get(b) - map.get(a);
                });
                while (temp.size() > 3) {
                    temp.remove(temp.size() - 1);
                }
                curr.words = temp;
            }
        }

        public AutocompleteSystem2(String[] sentences, int[] times) {
            root = new TrieNode();
            map = new HashMap();
            for (int i = 0; i < sentences.length; i++) {
                map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
                addValue(sentences[i], times[i]);
            }
        }

        private List<String> findValue(String word) {
            TrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!curr.children.containsKey(c)) {
                    return new ArrayList<String>();
                }
                curr = curr.children.get(c);
            }
            return curr.words;
        }

        public List<String> input(char c) {
            List<String> result = new ArrayList<>();
            if (c == '#') {
                map.put(currString.toString(), map.getOrDefault(currString.toString(), 0) + 1);
                addValue(currString.toString(), 1);
                currString = new StringBuilder();
                return result;
            }
            currString.append(c);
            return findValue(currString.toString());
        }
    }
}
