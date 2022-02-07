class AutocompleteSystem {

    class TrieNode {
        //HashMap<String, Integer> map;
        List<String> pq;
        HashMap<Character, TrieNode> children;
        public TrieNode() {
            // map = new HashMap<>();
            pq = new ArrayList<>();
            children = new HashMap<>();
        }
    }
    HashMap<String, Integer> map;
    TrieNode root;
    StringBuilder input;

    private void insert(String sentence, int times) {
        TrieNode curr = root;
        for(int i = 0; i < sentence.length(); i++) {
            char c =  sentence.charAt(i);
            if(!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
            // curr.map.put(sentence, curr.map.getOrDefault(sentence, 0) + times);
            List<String> list = curr.pq;
            if(!list.contains(sentence)) {
                list.add(sentence);
            }
            Collections.sort(list, (a, b) -> {
                if(map.get(a) == map.get(b)) {
                    return a.compareTo(b);
                }
                return map.get(b) - map.get(a);
            });
            if(list.size() > 3) {
                list.remove(list.size() - 1);
            }
            curr.pq = list;
        }
    }

    private List<String> search(String prefix) {
        TrieNode curr = root;
        for(int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(!curr.children.containsKey(c)) {
                return new ArrayList<>();
            }
            curr = curr.children.get(c);
        }
        return curr.pq;
    }

    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        root = new TrieNode();
        input = new StringBuilder();
        for(int i = 0; i < sentences.length; i++) {
            map.put(sentences[i], map.getOrDefault(sentences[i], 0) + times[i]);
            insert(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        if(c == '#') {
            String in = input.toString();
            map.put(in, map.getOrDefault(in, 0) + 1);
            insert(in, 1);
            input = new StringBuilder();
            return new ArrayList<>();
        }
        input.append(c);
        return search(input.toString());
    }
}

//tc and sc - O(n log n) and O(n)