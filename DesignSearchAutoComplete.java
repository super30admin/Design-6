public class DesignSearchAutoComplete {

}

class AutocompleteSystem {
    class TrieNode {
        TrieNode[] children;
        List<String> li;

        public TrieNode() {
            this.children = new TrieNode[256];
            this.li = new ArrayList<>();
        }

    }

    private void insert(String sentences) {
        TrieNode curr = root;
        for (int i = 0; i < sentences.length(); i++) {
            char c = sentences.charAt(i);
            if (curr.children[c - ' '] == null) {
                curr.children[c - ' '] = new TrieNode();
            }
            curr = curr.children[c - ' '];
            curr.li.add(sentences);
        }

    }

    private List<String> search(String prefix) {
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (curr.children[c - ' '] == null) {
                return new ArrayList<>();
            }
            curr = curr.children[c - ' '];
        }
        return curr.li;
    }

    HashMap<String, Integer> map;
    StringBuilder input;
    TrieNode root;

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.map = new HashMap<>();
        this.input = new StringBuilder();
        this.root = new TrieNode();
        for (int i = 0; i < sentences.length; i++) {
            String sentence = sentences[i];
            if (!map.containsKey(sentence)) {
                insert(sentence);
            }
            map.put(sentence, map.getOrDefault(sentence, 0) + times[i]);
        }

    }

    public List<String> input(char c) {
        if (c == '#') {
            String search = input.toString();
            if (!map.containsKey(search)) {
                insert(search);
            }
            map.put(search, map.getOrDefault(search, 0) + 1);
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
        String prefix = input.toString();
        List<String> startsWith = search(prefix);
        for (String str : startsWith) {
            pq.add(str);
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

